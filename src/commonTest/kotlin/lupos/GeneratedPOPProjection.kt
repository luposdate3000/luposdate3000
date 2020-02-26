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
                                        GeneratedMutableMap.map988map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map988map
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
                                        GeneratedMutableMap.map1140map,
                                        GeneratedMutableMap.map1141map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1140map,
                                GeneratedMutableMap.map1141map
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
                                        "#f1185",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1419map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1140map
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
                                        "#f1745",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1966map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map988map
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
                                        "#f2006",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2238map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1140map
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
                                        GeneratedMutableMap.map2794map,
                                        GeneratedMutableMap.map2795map,
                                        GeneratedMutableMap.map2796map,
                                        GeneratedMutableMap.map2797map,
                                        GeneratedMutableMap.map2798map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O12",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2794map,
                                GeneratedMutableMap.map2795map,
                                GeneratedMutableMap.map2796map,
                                GeneratedMutableMap.map2797map,
                                GeneratedMutableMap.map2798map
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
                                        GeneratedMutableMap.map3300map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3300map
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
                                        GeneratedMutableMap.map3486map,
                                        GeneratedMutableMap.map3487map,
                                        GeneratedMutableMap.map3488map,
                                        GeneratedMutableMap.map3489map,
                                        GeneratedMutableMap.map3490map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map3486map,
                                GeneratedMutableMap.map3487map,
                                GeneratedMutableMap.map3488map,
                                GeneratedMutableMap.map3489map,
                                GeneratedMutableMap.map3490map
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
                                        GeneratedMutableMap.map3622map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3622map
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
                                        "#f3666",
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3938map,
                                        GeneratedMutableMap.map3939map,
                                        GeneratedMutableMap.map3940map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "avg"
                        ), listOf(
                                GeneratedMutableMap.map3941map,
                                GeneratedMutableMap.map3942map,
                                GeneratedMutableMap.map3943map
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
                                        GeneratedMutableMap.map4065map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4065map
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
                                        GeneratedMutableMap.map4219map,
                                        GeneratedMutableMap.map4220map,
                                        GeneratedMutableMap.map4221map,
                                        GeneratedMutableMap.map4222map,
                                        GeneratedMutableMap.map4223map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "min"
                        ), listOf(
                                GeneratedMutableMap.map4219map,
                                GeneratedMutableMap.map4220map,
                                GeneratedMutableMap.map4221map,
                                GeneratedMutableMap.map4222map,
                                GeneratedMutableMap.map4223map
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
                                        GeneratedMutableMap.map4371map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4371map
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
                                        GeneratedMutableMap.map4523map,
                                        GeneratedMutableMap.map4524map,
                                        GeneratedMutableMap.map4525map,
                                        GeneratedMutableMap.map4526map,
                                        GeneratedMutableMap.map4527map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "max"
                        ), listOf(
                                GeneratedMutableMap.map4523map,
                                GeneratedMutableMap.map4524map,
                                GeneratedMutableMap.map4525map,
                                GeneratedMutableMap.map4526map,
                                GeneratedMutableMap.map4527map
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
                                GeneratedMutableMap.map4675map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            GeneratedMutableMap.map4675map
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
                                GeneratedMutableMap.map4975map,
                                GeneratedMutableMap.map4976map,
                                GeneratedMutableMap.map4977map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "g",
                            "avg",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map4978map,
                            GeneratedMutableMap.map4979map,
                            GeneratedMutableMap.map4980map
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
                                GeneratedMutableMap.map5087map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map5087map
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
                                GeneratedMutableMap.map6547map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "count"
                        ), listOf(
                            GeneratedMutableMap.map6547map
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
                                        GeneratedMutableMap.map6724map,
                                        GeneratedMutableMap.map6725map,
                                        GeneratedMutableMap.map6726map,
                                        GeneratedMutableMap.map6727map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map,
                                GeneratedMutableMap.map6734map,
                                GeneratedMutableMap.map6735map
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
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map,
                                GeneratedMutableMap.map6734map,
                                GeneratedMutableMap.map6735map
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
                                        GeneratedMutableMap.map6728map,
                                        GeneratedMutableMap.map6729map,
                                        GeneratedMutableMap.map6730map,
                                        GeneratedMutableMap.map6731map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map6736map,
                                GeneratedMutableMap.map6737map,
                                GeneratedMutableMap.map6738map,
                                GeneratedMutableMap.map6739map
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
                                GeneratedMutableMap.map6736map,
                                GeneratedMutableMap.map6737map,
                                GeneratedMutableMap.map6738map,
                                GeneratedMutableMap.map6739map
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
                                        GeneratedMutableMap.map6883map,
                                        GeneratedMutableMap.map6884map,
                                        GeneratedMutableMap.map6885map,
                                        GeneratedMutableMap.map6886map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "z",
                                "z2"
                        ), listOf(
                                GeneratedMutableMap.map6891map,
                                GeneratedMutableMap.map6892map,
                                GeneratedMutableMap.map6893map,
                                GeneratedMutableMap.map6894map
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
                                GeneratedMutableMap.map6891map,
                                GeneratedMutableMap.map6892map,
                                GeneratedMutableMap.map6893map,
                                GeneratedMutableMap.map6894map
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
                                        GeneratedMutableMap.map6887map,
                                        GeneratedMutableMap.map6888map,
                                        GeneratedMutableMap.map6889map,
                                        GeneratedMutableMap.map6890map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                        ), listOf(
                                GeneratedMutableMap.map6895map,
                                GeneratedMutableMap.map6896map,
                                GeneratedMutableMap.map6897map,
                                GeneratedMutableMap.map6898map
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
                                GeneratedMutableMap.map6895map,
                                GeneratedMutableMap.map6896map,
                                GeneratedMutableMap.map6897map,
                                GeneratedMutableMap.map6898map
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
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s1"
                        ), listOf(
                            GeneratedMutableMap.map7224map,
                            GeneratedMutableMap.map7225map,
                            GeneratedMutableMap.map7226map,
                            GeneratedMutableMap.map7227map,
                            GeneratedMutableMap.map7228map,
                            GeneratedMutableMap.map7229map,
                            GeneratedMutableMap.map7230map,
                            GeneratedMutableMap.map7231map,
                            GeneratedMutableMap.map7232map,
                            GeneratedMutableMap.map7233map,
                            GeneratedMutableMap.map7234map,
                            GeneratedMutableMap.map7235map,
                            GeneratedMutableMap.map7236map,
                            GeneratedMutableMap.map7237map,
                            GeneratedMutableMap.map7238map,
                            GeneratedMutableMap.map7239map
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
                                        GeneratedMutableMap.map7289map,
                                        GeneratedMutableMap.map7290map,
                                        GeneratedMutableMap.map7291map,
                                        GeneratedMutableMap.map7292map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7289map,
                                GeneratedMutableMap.map7290map,
                                GeneratedMutableMap.map7291map,
                                GeneratedMutableMap.map7292map
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
                                GeneratedMutableMap.map7289map,
                                GeneratedMutableMap.map7290map,
                                GeneratedMutableMap.map7291map,
                                GeneratedMutableMap.map7292map
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
                                        GeneratedMutableMap.map7294map,
                                        GeneratedMutableMap.map7295map,
                                        GeneratedMutableMap.map7296map,
                                        GeneratedMutableMap.map7297map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7294map,
                                GeneratedMutableMap.map7295map,
                                GeneratedMutableMap.map7296map,
                                GeneratedMutableMap.map7297map
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
                                GeneratedMutableMap.map7294map,
                                GeneratedMutableMap.map7295map,
                                GeneratedMutableMap.map7296map,
                                GeneratedMutableMap.map7297map
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
                                        GeneratedMutableMap.map7538map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map7538map
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
                                GeneratedMutableMap.map7538map
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
                                        GeneratedMutableMap.map7542map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map7542map
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
                                GeneratedMutableMap.map7542map
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
                        ),
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
                                        GeneratedMutableMap.map8314map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map8314map
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
                                GeneratedMutableMap.map8314map
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
                                        GeneratedMutableMap.map8318map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map8318map
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
                                GeneratedMutableMap.map8318map
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
                                        GeneratedMutableMap.map8505map,
                                        GeneratedMutableMap.map8506map
                                )
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
                                        GeneratedMutableMap.map8507map,
                                        GeneratedMutableMap.map8508map
                                )
                                )
                        ),
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
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map
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
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map8585map
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
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map8585map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8712map,
                                GeneratedMutableMap.map8713map,
                                GeneratedMutableMap.map8714map,
                                GeneratedMutableMap.map8715map,
                                GeneratedMutableMap.map8716map,
                                GeneratedMutableMap.map8717map,
                                GeneratedMutableMap.map8718map,
                                GeneratedMutableMap.map8719map,
                                GeneratedMutableMap.map8720map,
                                GeneratedMutableMap.map8721map,
                                GeneratedMutableMap.map8722map,
                                GeneratedMutableMap.map8723map,
                                GeneratedMutableMap.map8724map
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
                                GeneratedMutableMap.map8712map,
                                GeneratedMutableMap.map8713map,
                                GeneratedMutableMap.map8714map,
                                GeneratedMutableMap.map8715map,
                                GeneratedMutableMap.map8716map,
                                GeneratedMutableMap.map8717map,
                                GeneratedMutableMap.map8718map,
                                GeneratedMutableMap.map8719map,
                                GeneratedMutableMap.map8720map,
                                GeneratedMutableMap.map8721map,
                                GeneratedMutableMap.map8722map,
                                GeneratedMutableMap.map8723map,
                                GeneratedMutableMap.map8724map
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
                                        GeneratedMutableMap.map8676map,
                                        GeneratedMutableMap.map8677map,
                                        GeneratedMutableMap.map8678map,
                                        GeneratedMutableMap.map8679map,
                                        GeneratedMutableMap.map8680map
                                )
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
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map,
                                GeneratedMutableMap.map8679map,
                                GeneratedMutableMap.map8680map
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
                                        GeneratedMutableMap.map8576map,
                                        GeneratedMutableMap.map8577map,
                                        GeneratedMutableMap.map8578map,
                                        GeneratedMutableMap.map8579map,
                                        GeneratedMutableMap.map8580map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map
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
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map9035map,
                                GeneratedMutableMap.map9036map,
                                GeneratedMutableMap.map9037map,
                                GeneratedMutableMap.map9038map,
                                GeneratedMutableMap.map9039map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o1"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8712map,
                                GeneratedMutableMap.map8713map,
                                GeneratedMutableMap.map8714map,
                                GeneratedMutableMap.map9070map,
                                GeneratedMutableMap.map9071map,
                                GeneratedMutableMap.map9072map,
                                GeneratedMutableMap.map9073map,
                                GeneratedMutableMap.map9074map
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
                                GeneratedMutableMap.map8712map,
                                GeneratedMutableMap.map8713map,
                                GeneratedMutableMap.map8714map,
                                GeneratedMutableMap.map9070map,
                                GeneratedMutableMap.map9071map,
                                GeneratedMutableMap.map9072map,
                                GeneratedMutableMap.map9073map,
                                GeneratedMutableMap.map9074map
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
                                        GeneratedMutableMap.map8505map
                                )
                                )
                        ),
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
                                        GeneratedMutableMap.map9607map,
                                        GeneratedMutableMap.map9608map,
                                        GeneratedMutableMap.map9609map,
                                        GeneratedMutableMap.map9610map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9607map,
                                GeneratedMutableMap.map9608map,
                                GeneratedMutableMap.map9609map,
                                GeneratedMutableMap.map9610map
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
                                GeneratedMutableMap.map9607map,
                                GeneratedMutableMap.map9608map,
                                GeneratedMutableMap.map9609map,
                                GeneratedMutableMap.map9610map
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
                                        GeneratedMutableMap.map9611map,
                                        GeneratedMutableMap.map9612map,
                                        GeneratedMutableMap.map9613map,
                                        GeneratedMutableMap.map9614map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9611map,
                                GeneratedMutableMap.map9612map,
                                GeneratedMutableMap.map9613map,
                                GeneratedMutableMap.map9614map
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
                                GeneratedMutableMap.map9611map,
                                GeneratedMutableMap.map9612map,
                                GeneratedMutableMap.map9613map,
                                GeneratedMutableMap.map9614map
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
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map,
                                GeneratedMutableMap.map10287map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map
                            )
                        )
                    ),
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
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map10286map,
                                        GeneratedMutableMap.map10287map,
                                        GeneratedMutableMap.map10288map,
                                        GeneratedMutableMap.map10289map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map,
                                GeneratedMutableMap.map10287map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map
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
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map10368map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map10370map,
                                        GeneratedMutableMap.map10371map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map10367map,
                                GeneratedMutableMap.map10368map,
                                GeneratedMutableMap.map10369map,
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map
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
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10477map,
                                GeneratedMutableMap.map10478map,
                                GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map,
                                        GeneratedMutableMap.map10476map,
                                        GeneratedMutableMap.map10477map,
                                        GeneratedMutableMap.map10478map,
                                        GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10604map,
                                        GeneratedMutableMap.map10605map,
                                        GeneratedMutableMap.map10606map,
                                        GeneratedMutableMap.map10607map,
                                        GeneratedMutableMap.map10608map,
                                        GeneratedMutableMap.map10609map
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
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map,
                                GeneratedMutableMap.map10673map,
                                GeneratedMutableMap.map10674map,
                                GeneratedMutableMap.map10675map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map,
                            GeneratedMutableMap.map10673map,
                            GeneratedMutableMap.map10674map,
                            GeneratedMutableMap.map10675map
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
                                        GeneratedMutableMap.map10669map,
                                        GeneratedMutableMap.map10670map,
                                        GeneratedMutableMap.map10671map,
                                        GeneratedMutableMap.map10672map,
                                        GeneratedMutableMap.map10673map,
                                        GeneratedMutableMap.map10674map,
                                        GeneratedMutableMap.map10675map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map,
                                GeneratedMutableMap.map10673map,
                                GeneratedMutableMap.map10674map,
                                GeneratedMutableMap.map10675map
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
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map,
                                GeneratedMutableMap.map10673map,
                                GeneratedMutableMap.map10674map,
                                GeneratedMutableMap.map10675map
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
                                        GeneratedMutableMap.map10735map,
                                        GeneratedMutableMap.map10736map,
                                        GeneratedMutableMap.map10737map,
                                        GeneratedMutableMap.map10738map,
                                        GeneratedMutableMap.map10739map,
                                        GeneratedMutableMap.map10740map,
                                        GeneratedMutableMap.map10741map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10735map,
                                GeneratedMutableMap.map10736map,
                                GeneratedMutableMap.map10737map,
                                GeneratedMutableMap.map10738map,
                                GeneratedMutableMap.map10739map,
                                GeneratedMutableMap.map10740map,
                                GeneratedMutableMap.map10741map
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
                                GeneratedMutableMap.map10735map,
                                GeneratedMutableMap.map10736map,
                                GeneratedMutableMap.map10737map,
                                GeneratedMutableMap.map10738map,
                                GeneratedMutableMap.map10739map,
                                GeneratedMutableMap.map10740map,
                                GeneratedMutableMap.map10741map
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
                                        GeneratedMutableMap.map15587map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15587map
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
                                GeneratedMutableMap.map15587map
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
                                        GeneratedMutableMap.map15588map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15588map
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
                                GeneratedMutableMap.map15588map
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
                                        GeneratedMutableMap.map15626map,
                                        GeneratedMutableMap.map15627map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15630map,
                                GeneratedMutableMap.map15630map
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
                                GeneratedMutableMap.map15630map,
                                GeneratedMutableMap.map15630map
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
                                        GeneratedMutableMap.map15628map,
                                        GeneratedMutableMap.map15629map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15631map,
                                GeneratedMutableMap.map15631map
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
                                GeneratedMutableMap.map15631map,
                                GeneratedMutableMap.map15631map
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
                                        GeneratedMutableMap.map15672map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15672map
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
                                GeneratedMutableMap.map15672map
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
                                        GeneratedMutableMap.map15673map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15673map
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
                                GeneratedMutableMap.map15673map
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
                                GeneratedMutableMap.map15711map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15711map
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
                                GeneratedMutableMap.map15892map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map15892map
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
                                        GeneratedMutableMap.map16016map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16016map
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
                                GeneratedMutableMap.map16016map
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
                                        GeneratedMutableMap.map16017map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map16017map
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
                                GeneratedMutableMap.map16017map
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
                                GeneratedMutableMap.map16157map
                            )
                        )
                    ),
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("f")
                                ),
                                POPValues(dictionary, listOf(
                                        "f"
                                ), listOf(
                                        GeneratedMutableMap.map16201map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map16201map
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
                                GeneratedMutableMap.map16201map
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
                                        GeneratedMutableMap.map16202map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("f")
                        ), listOf(
                                GeneratedMutableMap.map16202map
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
                                GeneratedMutableMap.map16202map
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
                                        GeneratedMutableMap.map16429map,
                                        GeneratedMutableMap.map16430map
                                )
                                )
                        ),
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
                        LOPProjection(
                                mutableListOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ),
                                LOPValues(listOf(
                                        AOPVariable("x"),
                                        AOPVariable("y")
                                ), listOf(
                                        GeneratedMutableMap.map16431map,
                                        GeneratedMutableMap.map16432map
                                )
                                )
                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16474map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16474map
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
                                        GeneratedMutableMap.map16997map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map16997map
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
                                        GeneratedMutableMap.map17001map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map17001map
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
                                        GeneratedMutableMap.map17045map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map17045map
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
                                GeneratedMutableMap.map17045map
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
                                        GeneratedMutableMap.map17046map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map17046map
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
                                GeneratedMutableMap.map17046map
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
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map,
                                GeneratedMutableMap.map6734map,
                                GeneratedMutableMap.map6735map
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
                                GeneratedMutableMap.map6736map,
                                GeneratedMutableMap.map6737map,
                                GeneratedMutableMap.map6738map,
                                GeneratedMutableMap.map6739map
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
                                GeneratedMutableMap.map6891map,
                                GeneratedMutableMap.map6892map,
                                GeneratedMutableMap.map6893map,
                                GeneratedMutableMap.map6894map
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
                                GeneratedMutableMap.map6895map,
                                GeneratedMutableMap.map6896map,
                                GeneratedMutableMap.map6897map,
                                GeneratedMutableMap.map6898map
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
                                GeneratedMutableMap.map7289map,
                                GeneratedMutableMap.map7290map,
                                GeneratedMutableMap.map7291map,
                                GeneratedMutableMap.map7292map
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
                                GeneratedMutableMap.map7294map,
                                GeneratedMutableMap.map7295map,
                                GeneratedMutableMap.map7296map,
                                GeneratedMutableMap.map7297map
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
                                GeneratedMutableMap.map7538map
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
                                GeneratedMutableMap.map7542map
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
                                        GeneratedMutableMap.map18715map,
                                        GeneratedMutableMap.map18716map
                                )
                                )
                        ),
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
                                        GeneratedMutableMap.map18717map,
                                        GeneratedMutableMap.map18718map
                                )
                                )
                        ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map18761map,
                                GeneratedMutableMap.map18762map,
                                GeneratedMutableMap.map18763map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5895map,
                            GeneratedMutableMap.map5895map,
                            GeneratedMutableMap.map5895map
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
                                        GeneratedMutableMap.map18946map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18948map
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
                                GeneratedMutableMap.map18948map
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
                                        GeneratedMutableMap.map18947map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map18949map
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
                                GeneratedMutableMap.map18949map
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
                                        GeneratedMutableMap.map19008map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19010map
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
                                GeneratedMutableMap.map19010map
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
                                        GeneratedMutableMap.map19009map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19011map
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
                                GeneratedMutableMap.map19011map
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
                                        GeneratedMutableMap.map19075map,
                                        GeneratedMutableMap.map19076map,
                                        GeneratedMutableMap.map19077map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19085map,
                                GeneratedMutableMap.map19086map,
                                GeneratedMutableMap.map19087map
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
                                GeneratedMutableMap.map19085map,
                                GeneratedMutableMap.map19086map,
                                GeneratedMutableMap.map19087map
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
                                        GeneratedMutableMap.map19082map,
                                        GeneratedMutableMap.map19083map,
                                        GeneratedMutableMap.map19084map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19088map,
                                GeneratedMutableMap.map19089map,
                                GeneratedMutableMap.map19090map
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
                                GeneratedMutableMap.map19088map,
                                GeneratedMutableMap.map19089map,
                                GeneratedMutableMap.map19090map
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
                                GeneratedMutableMap.map19271map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map19271map
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
                                GeneratedMutableMap.map19352map
                            )
                        )
                    ),
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
                                        GeneratedMutableMap.map19440map,
                                        GeneratedMutableMap.map19441map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "parent"
                        ), listOf(
                                GeneratedMutableMap.map19444map,
                                GeneratedMutableMap.map19445map
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
                                GeneratedMutableMap.map19444map,
                                GeneratedMutableMap.map19445map
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
                                        GeneratedMutableMap.map19442map,
                                        GeneratedMutableMap.map19443map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("parent")
                        ), listOf(
                                GeneratedMutableMap.map19446map,
                                GeneratedMutableMap.map19447map
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
                                GeneratedMutableMap.map19446map,
                                GeneratedMutableMap.map19447map
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
                                        GeneratedMutableMap.map22710map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22712map
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
                                GeneratedMutableMap.map22712map
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
                                        GeneratedMutableMap.map22711map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22713map
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
                                GeneratedMutableMap.map22713map
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
                                        GeneratedMutableMap.map22984map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map22986map
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
                                GeneratedMutableMap.map22986map
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
                                        GeneratedMutableMap.map22985map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map22987map
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
                                GeneratedMutableMap.map22987map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23223map,
                                GeneratedMutableMap.map23224map,
                                GeneratedMutableMap.map23225map,
                                GeneratedMutableMap.map23226map,
                                GeneratedMutableMap.map23227map,
                                GeneratedMutableMap.map23228map,
                                GeneratedMutableMap.map23229map,
                                GeneratedMutableMap.map23230map,
                                GeneratedMutableMap.map23231map,
                                GeneratedMutableMap.map23232map,
                                GeneratedMutableMap.map22712map,
                                GeneratedMutableMap.map23233map,
                                GeneratedMutableMap.map23234map,
                                GeneratedMutableMap.map23235map,
                                GeneratedMutableMap.map23236map,
                                GeneratedMutableMap.map23237map
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
                                GeneratedMutableMap.map23223map,
                                GeneratedMutableMap.map23224map,
                                GeneratedMutableMap.map23225map,
                                GeneratedMutableMap.map23226map,
                                GeneratedMutableMap.map23227map,
                                GeneratedMutableMap.map23228map,
                                GeneratedMutableMap.map23229map,
                                GeneratedMutableMap.map23230map,
                                GeneratedMutableMap.map23231map,
                                GeneratedMutableMap.map23232map,
                                GeneratedMutableMap.map22712map,
                                GeneratedMutableMap.map23233map,
                                GeneratedMutableMap.map23234map,
                                GeneratedMutableMap.map23235map,
                                GeneratedMutableMap.map23236map,
                                GeneratedMutableMap.map23237map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23238map,
                                GeneratedMutableMap.map23239map,
                                GeneratedMutableMap.map23240map,
                                GeneratedMutableMap.map23241map,
                                GeneratedMutableMap.map23242map,
                                GeneratedMutableMap.map23243map,
                                GeneratedMutableMap.map23244map,
                                GeneratedMutableMap.map23245map,
                                GeneratedMutableMap.map23246map,
                                GeneratedMutableMap.map23247map,
                                GeneratedMutableMap.map22713map,
                                GeneratedMutableMap.map23248map,
                                GeneratedMutableMap.map23249map,
                                GeneratedMutableMap.map23250map,
                                GeneratedMutableMap.map23251map,
                                GeneratedMutableMap.map23252map
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
                                GeneratedMutableMap.map23238map,
                                GeneratedMutableMap.map23239map,
                                GeneratedMutableMap.map23240map,
                                GeneratedMutableMap.map23241map,
                                GeneratedMutableMap.map23242map,
                                GeneratedMutableMap.map23243map,
                                GeneratedMutableMap.map23244map,
                                GeneratedMutableMap.map23245map,
                                GeneratedMutableMap.map23246map,
                                GeneratedMutableMap.map23247map,
                                GeneratedMutableMap.map22713map,
                                GeneratedMutableMap.map23248map,
                                GeneratedMutableMap.map23249map,
                                GeneratedMutableMap.map23250map,
                                GeneratedMutableMap.map23251map,
                                GeneratedMutableMap.map23252map
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
                                        GeneratedMutableMap.map23505map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23506map
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
                                GeneratedMutableMap.map23506map
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
                                        GeneratedMutableMap.map22711map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map22713map
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
                                GeneratedMutableMap.map22713map
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
                                        GeneratedMutableMap.map23774map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map23776map
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
                                GeneratedMutableMap.map23776map
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
                                        GeneratedMutableMap.map23775map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("s2")
                        ), listOf(
                                GeneratedMutableMap.map23777map
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
                                GeneratedMutableMap.map23777map
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map23223map,
                                GeneratedMutableMap.map23224map,
                                GeneratedMutableMap.map23225map,
                                GeneratedMutableMap.map23226map,
                                GeneratedMutableMap.map23227map,
                                GeneratedMutableMap.map23228map,
                                GeneratedMutableMap.map23229map,
                                GeneratedMutableMap.map23230map,
                                GeneratedMutableMap.map23231map,
                                GeneratedMutableMap.map23954map,
                                GeneratedMutableMap.map22712map,
                                GeneratedMutableMap.map23955map,
                                GeneratedMutableMap.map23956map,
                                GeneratedMutableMap.map23957map,
                                GeneratedMutableMap.map23236map,
                                GeneratedMutableMap.map23237map
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
                                GeneratedMutableMap.map23223map,
                                GeneratedMutableMap.map23224map,
                                GeneratedMutableMap.map23225map,
                                GeneratedMutableMap.map23226map,
                                GeneratedMutableMap.map23227map,
                                GeneratedMutableMap.map23228map,
                                GeneratedMutableMap.map23229map,
                                GeneratedMutableMap.map23230map,
                                GeneratedMutableMap.map23231map,
                                GeneratedMutableMap.map23954map,
                                GeneratedMutableMap.map22712map,
                                GeneratedMutableMap.map23955map,
                                GeneratedMutableMap.map23956map,
                                GeneratedMutableMap.map23957map,
                                GeneratedMutableMap.map23236map,
                                GeneratedMutableMap.map23237map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map23238map,
                                GeneratedMutableMap.map23239map,
                                GeneratedMutableMap.map23240map,
                                GeneratedMutableMap.map23241map,
                                GeneratedMutableMap.map23242map,
                                GeneratedMutableMap.map23243map,
                                GeneratedMutableMap.map23244map,
                                GeneratedMutableMap.map23245map,
                                GeneratedMutableMap.map23246map,
                                GeneratedMutableMap.map23958map,
                                GeneratedMutableMap.map22713map,
                                GeneratedMutableMap.map23959map,
                                GeneratedMutableMap.map23960map,
                                GeneratedMutableMap.map23961map,
                                GeneratedMutableMap.map23251map,
                                GeneratedMutableMap.map23252map
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
                                GeneratedMutableMap.map23238map,
                                GeneratedMutableMap.map23239map,
                                GeneratedMutableMap.map23240map,
                                GeneratedMutableMap.map23241map,
                                GeneratedMutableMap.map23242map,
                                GeneratedMutableMap.map23243map,
                                GeneratedMutableMap.map23244map,
                                GeneratedMutableMap.map23245map,
                                GeneratedMutableMap.map23246map,
                                GeneratedMutableMap.map23958map,
                                GeneratedMutableMap.map22713map,
                                GeneratedMutableMap.map23959map,
                                GeneratedMutableMap.map23960map,
                                GeneratedMutableMap.map23961map,
                                GeneratedMutableMap.map23251map,
                                GeneratedMutableMap.map23252map
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
                                        GeneratedMutableMap.map24144map,
                                        GeneratedMutableMap.map24145map,
                                        GeneratedMutableMap.map24146map,
                                        GeneratedMutableMap.map24147map,
                                        GeneratedMutableMap.map24148map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map24156map,
                                GeneratedMutableMap.map24157map,
                                GeneratedMutableMap.map24158map,
                                GeneratedMutableMap.map24159map,
                                GeneratedMutableMap.map24160map
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
                                        GeneratedMutableMap.map23179map,
                                        GeneratedMutableMap.map23180map,
                                        GeneratedMutableMap.map23181map,
                                        GeneratedMutableMap.map23182map,
                                        GeneratedMutableMap.map23183map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map24161map,
                                GeneratedMutableMap.map24162map,
                                GeneratedMutableMap.map24163map,
                                GeneratedMutableMap.map24164map,
                                GeneratedMutableMap.map24165map
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
                                        GeneratedMutableMap.map24159map,
                                        GeneratedMutableMap.map24160map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                        ), listOf(
                                GeneratedMutableMap.map24159map,
                                GeneratedMutableMap.map24160map
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
                                GeneratedMutableMap.map24159map,
                                GeneratedMutableMap.map24160map
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
                                        GeneratedMutableMap.map24164map,
                                        GeneratedMutableMap.map24165map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num")
                        ), listOf(
                                GeneratedMutableMap.map24164map,
                                GeneratedMutableMap.map24165map
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
                                GeneratedMutableMap.map24164map,
                                GeneratedMutableMap.map24165map
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
                                        GeneratedMutableMap.map24356map,
                                        GeneratedMutableMap.map24357map,
                                        GeneratedMutableMap.map24358map,
                                        GeneratedMutableMap.map24359map,
                                        GeneratedMutableMap.map24360map
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
                                        GeneratedMutableMap.map24361map,
                                        GeneratedMutableMap.map24362map,
                                        GeneratedMutableMap.map24363map,
                                        GeneratedMutableMap.map24364map,
                                        GeneratedMutableMap.map24365map
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
                                        GeneratedMutableMap.map24449map,
                                        GeneratedMutableMap.map24450map,
                                        GeneratedMutableMap.map24451map,
                                        GeneratedMutableMap.map24452map,
                                        GeneratedMutableMap.map24453map
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
                                        GeneratedMutableMap.map24361map,
                                        GeneratedMutableMap.map24454map,
                                        GeneratedMutableMap.map24455map,
                                        GeneratedMutableMap.map24364map,
                                        GeneratedMutableMap.map24456map
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
                                        GeneratedMutableMap.map24540map,
                                        GeneratedMutableMap.map24541map,
                                        GeneratedMutableMap.map24542map,
                                        GeneratedMutableMap.map24543map,
                                        GeneratedMutableMap.map24544map
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
                                        GeneratedMutableMap.map24361map,
                                        GeneratedMutableMap.map24454map,
                                        GeneratedMutableMap.map24455map,
                                        GeneratedMutableMap.map24364map,
                                        GeneratedMutableMap.map24365map
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
                                        GeneratedMutableMap.map24645map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map24647map
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
                                GeneratedMutableMap.map24647map
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
                                        GeneratedMutableMap.map24646map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24648map
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
                                GeneratedMutableMap.map24648map
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
                        ),
                        POPValues(dictionary, listOf(
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map25456map,
                                GeneratedMutableMap.map25457map,
                                GeneratedMutableMap.map25458map,
                                GeneratedMutableMap.map25459map,
                                GeneratedMutableMap.map25460map,
                                GeneratedMutableMap.map25461map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25463map,
                                GeneratedMutableMap.map25464map,
                                GeneratedMutableMap.map25465map,
                                GeneratedMutableMap.map25466map,
                                GeneratedMutableMap.map25467map,
                                GeneratedMutableMap.map25468map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25469map,
                                GeneratedMutableMap.map25470map,
                                GeneratedMutableMap.map25471map,
                                GeneratedMutableMap.map25472map,
                                GeneratedMutableMap.map25473map,
                                GeneratedMutableMap.map25474map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25475map,
                                GeneratedMutableMap.map25476map,
                                GeneratedMutableMap.map25477map,
                                GeneratedMutableMap.map25478map,
                                GeneratedMutableMap.map25479map,
                                GeneratedMutableMap.map25480map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25481map,
                                GeneratedMutableMap.map25482map,
                                GeneratedMutableMap.map25483map,
                                GeneratedMutableMap.map25484map,
                                GeneratedMutableMap.map25485map,
                                GeneratedMutableMap.map25486map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25487map,
                                GeneratedMutableMap.map25488map,
                                GeneratedMutableMap.map25489map,
                                GeneratedMutableMap.map25490map,
                                GeneratedMutableMap.map25491map,
                                GeneratedMutableMap.map25492map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map
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
                                GeneratedMutableMap.map25456map,
                                GeneratedMutableMap.map25457map,
                                GeneratedMutableMap.map25458map,
                                GeneratedMutableMap.map25459map,
                                GeneratedMutableMap.map25460map,
                                GeneratedMutableMap.map25461map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25463map,
                                GeneratedMutableMap.map25464map,
                                GeneratedMutableMap.map25465map,
                                GeneratedMutableMap.map25466map,
                                GeneratedMutableMap.map25467map,
                                GeneratedMutableMap.map25468map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25469map,
                                GeneratedMutableMap.map25470map,
                                GeneratedMutableMap.map25471map,
                                GeneratedMutableMap.map25472map,
                                GeneratedMutableMap.map25473map,
                                GeneratedMutableMap.map25474map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25475map,
                                GeneratedMutableMap.map25476map,
                                GeneratedMutableMap.map25477map,
                                GeneratedMutableMap.map25478map,
                                GeneratedMutableMap.map25479map,
                                GeneratedMutableMap.map25480map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25481map,
                                GeneratedMutableMap.map25482map,
                                GeneratedMutableMap.map25483map,
                                GeneratedMutableMap.map25484map,
                                GeneratedMutableMap.map25485map,
                                GeneratedMutableMap.map25486map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25487map,
                                GeneratedMutableMap.map25488map,
                                GeneratedMutableMap.map25489map,
                                GeneratedMutableMap.map25490map,
                                GeneratedMutableMap.map25491map,
                                GeneratedMutableMap.map25492map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map,
                                GeneratedMutableMap.map25462map
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map25493map,
                                GeneratedMutableMap.map25494map,
                                GeneratedMutableMap.map25495map,
                                GeneratedMutableMap.map25496map,
                                GeneratedMutableMap.map25497map,
                                GeneratedMutableMap.map25498map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25500map,
                                GeneratedMutableMap.map25501map,
                                GeneratedMutableMap.map25502map,
                                GeneratedMutableMap.map25503map,
                                GeneratedMutableMap.map25504map,
                                GeneratedMutableMap.map25505map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25506map,
                                GeneratedMutableMap.map25507map,
                                GeneratedMutableMap.map25508map,
                                GeneratedMutableMap.map25509map,
                                GeneratedMutableMap.map25510map,
                                GeneratedMutableMap.map25511map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25512map,
                                GeneratedMutableMap.map25513map,
                                GeneratedMutableMap.map25514map,
                                GeneratedMutableMap.map25515map,
                                GeneratedMutableMap.map25516map,
                                GeneratedMutableMap.map25517map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25518map,
                                GeneratedMutableMap.map25519map,
                                GeneratedMutableMap.map25520map,
                                GeneratedMutableMap.map25521map,
                                GeneratedMutableMap.map25522map,
                                GeneratedMutableMap.map25523map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25524map,
                                GeneratedMutableMap.map25525map,
                                GeneratedMutableMap.map25526map,
                                GeneratedMutableMap.map25527map,
                                GeneratedMutableMap.map25528map,
                                GeneratedMutableMap.map25529map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map
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
                                GeneratedMutableMap.map25493map,
                                GeneratedMutableMap.map25494map,
                                GeneratedMutableMap.map25495map,
                                GeneratedMutableMap.map25496map,
                                GeneratedMutableMap.map25497map,
                                GeneratedMutableMap.map25498map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25500map,
                                GeneratedMutableMap.map25501map,
                                GeneratedMutableMap.map25502map,
                                GeneratedMutableMap.map25503map,
                                GeneratedMutableMap.map25504map,
                                GeneratedMutableMap.map25505map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25506map,
                                GeneratedMutableMap.map25507map,
                                GeneratedMutableMap.map25508map,
                                GeneratedMutableMap.map25509map,
                                GeneratedMutableMap.map25510map,
                                GeneratedMutableMap.map25511map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25512map,
                                GeneratedMutableMap.map25513map,
                                GeneratedMutableMap.map25514map,
                                GeneratedMutableMap.map25515map,
                                GeneratedMutableMap.map25516map,
                                GeneratedMutableMap.map25517map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25518map,
                                GeneratedMutableMap.map25519map,
                                GeneratedMutableMap.map25520map,
                                GeneratedMutableMap.map25521map,
                                GeneratedMutableMap.map25522map,
                                GeneratedMutableMap.map25523map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25524map,
                                GeneratedMutableMap.map25525map,
                                GeneratedMutableMap.map25526map,
                                GeneratedMutableMap.map25527map,
                                GeneratedMutableMap.map25528map,
                                GeneratedMutableMap.map25529map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25499map
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
                                        GeneratedMutableMap.map25690map,
                                        GeneratedMutableMap.map25691map,
                                        GeneratedMutableMap.map25692map,
                                        GeneratedMutableMap.map25693map,
                                        GeneratedMutableMap.map25694map,
                                        GeneratedMutableMap.map25695map,
                                        GeneratedMutableMap.map25696map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "str",
                                "len"
                        ), listOf(
                                GeneratedMutableMap.map25704map,
                                GeneratedMutableMap.map25705map,
                                GeneratedMutableMap.map25706map,
                                GeneratedMutableMap.map25707map,
                                GeneratedMutableMap.map25708map,
                                GeneratedMutableMap.map25709map,
                                GeneratedMutableMap.map25710map
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
                                GeneratedMutableMap.map25704map,
                                GeneratedMutableMap.map25705map,
                                GeneratedMutableMap.map25706map,
                                GeneratedMutableMap.map25707map,
                                GeneratedMutableMap.map25708map,
                                GeneratedMutableMap.map25709map,
                                GeneratedMutableMap.map25710map
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
                                        GeneratedMutableMap.map25697map,
                                        GeneratedMutableMap.map25698map,
                                        GeneratedMutableMap.map25699map,
                                        GeneratedMutableMap.map25700map,
                                        GeneratedMutableMap.map25701map,
                                        GeneratedMutableMap.map25702map,
                                        GeneratedMutableMap.map25703map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str"),
                                AOPVariable("len")
                        ), listOf(
                                GeneratedMutableMap.map25711map,
                                GeneratedMutableMap.map25712map,
                                GeneratedMutableMap.map25713map,
                                GeneratedMutableMap.map25714map,
                                GeneratedMutableMap.map25715map,
                                GeneratedMutableMap.map25716map,
                                GeneratedMutableMap.map25717map
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
                                GeneratedMutableMap.map25711map,
                                GeneratedMutableMap.map25712map,
                                GeneratedMutableMap.map25713map,
                                GeneratedMutableMap.map25714map,
                                GeneratedMutableMap.map25715map,
                                GeneratedMutableMap.map25716map,
                                GeneratedMutableMap.map25717map
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
                                        GeneratedMutableMap.map25810map,
                                        GeneratedMutableMap.map25811map,
                                        GeneratedMutableMap.map25812map,
                                        GeneratedMutableMap.map25813map,
                                        GeneratedMutableMap.map25814map,
                                        GeneratedMutableMap.map25815map,
                                        GeneratedMutableMap.map25816map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "ustr"
                        ), listOf(
                                GeneratedMutableMap.map25824map,
                                GeneratedMutableMap.map25825map,
                                GeneratedMutableMap.map25826map,
                                GeneratedMutableMap.map25827map,
                                GeneratedMutableMap.map25828map,
                                GeneratedMutableMap.map25829map,
                                GeneratedMutableMap.map25830map
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
                                GeneratedMutableMap.map25824map,
                                GeneratedMutableMap.map25825map,
                                GeneratedMutableMap.map25826map,
                                GeneratedMutableMap.map25827map,
                                GeneratedMutableMap.map25828map,
                                GeneratedMutableMap.map25829map,
                                GeneratedMutableMap.map25830map
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
                                        GeneratedMutableMap.map25817map,
                                        GeneratedMutableMap.map25818map,
                                        GeneratedMutableMap.map25819map,
                                        GeneratedMutableMap.map25820map,
                                        GeneratedMutableMap.map25821map,
                                        GeneratedMutableMap.map25822map,
                                        GeneratedMutableMap.map25823map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("ustr")
                        ), listOf(
                                GeneratedMutableMap.map25831map,
                                GeneratedMutableMap.map25832map,
                                GeneratedMutableMap.map22705map,
                                GeneratedMutableMap.map22706map,
                                GeneratedMutableMap.map22707map,
                                GeneratedMutableMap.map25833map,
                                GeneratedMutableMap.map22709map
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
                                GeneratedMutableMap.map25831map,
                                GeneratedMutableMap.map25832map,
                                GeneratedMutableMap.map22705map,
                                GeneratedMutableMap.map22706map,
                                GeneratedMutableMap.map22707map,
                                GeneratedMutableMap.map25833map,
                                GeneratedMutableMap.map22709map
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
                                        GeneratedMutableMap.map25926map,
                                        GeneratedMutableMap.map25927map,
                                        GeneratedMutableMap.map25928map,
                                        GeneratedMutableMap.map25929map,
                                        GeneratedMutableMap.map25930map,
                                        GeneratedMutableMap.map25931map,
                                        GeneratedMutableMap.map25932map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "lstr"
                        ), listOf(
                                GeneratedMutableMap.map25938map,
                                GeneratedMutableMap.map25939map,
                                GeneratedMutableMap.map25940map,
                                GeneratedMutableMap.map25941map,
                                GeneratedMutableMap.map25942map,
                                GeneratedMutableMap.map25943map,
                                GeneratedMutableMap.map25944map
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
                                GeneratedMutableMap.map25938map,
                                GeneratedMutableMap.map25939map,
                                GeneratedMutableMap.map25940map,
                                GeneratedMutableMap.map25941map,
                                GeneratedMutableMap.map25942map,
                                GeneratedMutableMap.map25943map,
                                GeneratedMutableMap.map25944map
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
                                        GeneratedMutableMap.map25933map,
                                        GeneratedMutableMap.map25934map,
                                        GeneratedMutableMap.map25935map,
                                        GeneratedMutableMap.map25820map,
                                        GeneratedMutableMap.map25821map,
                                        GeneratedMutableMap.map25936map,
                                        GeneratedMutableMap.map25937map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("lstr")
                        ), listOf(
                                GeneratedMutableMap.map22703map,
                                GeneratedMutableMap.map22704map,
                                GeneratedMutableMap.map25945map,
                                GeneratedMutableMap.map22706map,
                                GeneratedMutableMap.map22707map,
                                GeneratedMutableMap.map22708map,
                                GeneratedMutableMap.map25946map
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
                                GeneratedMutableMap.map22703map,
                                GeneratedMutableMap.map22704map,
                                GeneratedMutableMap.map25945map,
                                GeneratedMutableMap.map22706map,
                                GeneratedMutableMap.map22707map,
                                GeneratedMutableMap.map22708map,
                                GeneratedMutableMap.map25946map
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
                                        GeneratedMutableMap.map22697map,
                                        GeneratedMutableMap.map22701map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22697map,
                                GeneratedMutableMap.map22701map
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
                                GeneratedMutableMap.map22697map,
                                GeneratedMutableMap.map22701map
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
                                        GeneratedMutableMap.map22704map,
                                        GeneratedMutableMap.map22708map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22704map,
                                GeneratedMutableMap.map22708map
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
                                GeneratedMutableMap.map22704map,
                                GeneratedMutableMap.map22708map
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
                                        GeneratedMutableMap.map26690map,
                                        GeneratedMutableMap.map26697map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26700map,
                                GeneratedMutableMap.map22700map
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
                                GeneratedMutableMap.map26700map,
                                GeneratedMutableMap.map22700map
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
                                        GeneratedMutableMap.map23181map,
                                        GeneratedMutableMap.map23188map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map24163map,
                                GeneratedMutableMap.map22707map
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
                                GeneratedMutableMap.map24163map,
                                GeneratedMutableMap.map22707map
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
                                        GeneratedMutableMap.map26698map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22701map
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
                                GeneratedMutableMap.map22701map
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
                                        GeneratedMutableMap.map23189map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22708map
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
                                GeneratedMutableMap.map22708map
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
                                        GeneratedMutableMap.map27202map,
                                        GeneratedMutableMap.map27200map,
                                        GeneratedMutableMap.map27203map,
                                        GeneratedMutableMap.map27204map,
                                        GeneratedMutableMap.map27201map,
                                        GeneratedMutableMap.map27197map,
                                        GeneratedMutableMap.map27199map,
                                        GeneratedMutableMap.map27198map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27213map,
                                GeneratedMutableMap.map27214map,
                                GeneratedMutableMap.map27215map,
                                GeneratedMutableMap.map27216map,
                                GeneratedMutableMap.map27217map,
                                GeneratedMutableMap.map27218map,
                                GeneratedMutableMap.map27219map,
                                GeneratedMutableMap.map27220map
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
                                GeneratedMutableMap.map27213map,
                                GeneratedMutableMap.map27214map,
                                GeneratedMutableMap.map27215map,
                                GeneratedMutableMap.map27216map,
                                GeneratedMutableMap.map27217map,
                                GeneratedMutableMap.map27218map,
                                GeneratedMutableMap.map27219map,
                                GeneratedMutableMap.map27220map
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
                                        GeneratedMutableMap.map27210map,
                                        GeneratedMutableMap.map27208map,
                                        GeneratedMutableMap.map27211map,
                                        GeneratedMutableMap.map27212map,
                                        GeneratedMutableMap.map27209map,
                                        GeneratedMutableMap.map27205map,
                                        GeneratedMutableMap.map27207map,
                                        GeneratedMutableMap.map27206map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27221map,
                                GeneratedMutableMap.map27222map,
                                GeneratedMutableMap.map27223map,
                                GeneratedMutableMap.map27224map,
                                GeneratedMutableMap.map27225map,
                                GeneratedMutableMap.map27226map,
                                GeneratedMutableMap.map27227map,
                                GeneratedMutableMap.map27228map
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
                                GeneratedMutableMap.map27221map,
                                GeneratedMutableMap.map27222map,
                                GeneratedMutableMap.map27223map,
                                GeneratedMutableMap.map27224map,
                                GeneratedMutableMap.map27225map,
                                GeneratedMutableMap.map27226map,
                                GeneratedMutableMap.map27227map,
                                GeneratedMutableMap.map27228map
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
                                        GeneratedMutableMap.map27202map,
                                        GeneratedMutableMap.map27477map,
                                        GeneratedMutableMap.map27203map,
                                        GeneratedMutableMap.map27204map,
                                        GeneratedMutableMap.map27478map,
                                        GeneratedMutableMap.map27197map,
                                        GeneratedMutableMap.map27199map,
                                        GeneratedMutableMap.map27198map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                        ), listOf(
                                GeneratedMutableMap.map27213map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27215map,
                                GeneratedMutableMap.map27216map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27218map,
                                GeneratedMutableMap.map27219map,
                                GeneratedMutableMap.map27220map
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
                                GeneratedMutableMap.map27213map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27215map,
                                GeneratedMutableMap.map27216map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27218map,
                                GeneratedMutableMap.map27219map,
                                GeneratedMutableMap.map27220map
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
                                        GeneratedMutableMap.map27210map,
                                        GeneratedMutableMap.map27479map,
                                        GeneratedMutableMap.map27211map,
                                        GeneratedMutableMap.map27212map,
                                        GeneratedMutableMap.map27480map,
                                        GeneratedMutableMap.map27205map,
                                        GeneratedMutableMap.map27207map,
                                        GeneratedMutableMap.map27206map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                        ), listOf(
                                GeneratedMutableMap.map27221map,
                                GeneratedMutableMap.map27483map,
                                GeneratedMutableMap.map27223map,
                                GeneratedMutableMap.map27224map,
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27226map,
                                GeneratedMutableMap.map27227map,
                                GeneratedMutableMap.map27228map
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
                                GeneratedMutableMap.map27221map,
                                GeneratedMutableMap.map27483map,
                                GeneratedMutableMap.map27223map,
                                GeneratedMutableMap.map27224map,
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27226map,
                                GeneratedMutableMap.map27227map,
                                GeneratedMutableMap.map27228map
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
                                        GeneratedMutableMap.map27555map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27557map
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
                                GeneratedMutableMap.map27557map
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
                                        GeneratedMutableMap.map27556map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27558map
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
                                GeneratedMutableMap.map27558map
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
                                        GeneratedMutableMap.map27629map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27631map
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
                                GeneratedMutableMap.map27631map
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
                                        GeneratedMutableMap.map27630map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27632map
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
                                GeneratedMutableMap.map27632map
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
                                        GeneratedMutableMap.map27701map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27703map
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
                                GeneratedMutableMap.map27703map
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
                                        GeneratedMutableMap.map27702map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27704map
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
                                GeneratedMutableMap.map27704map
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
                                        GeneratedMutableMap.map27775map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27777map
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
                                GeneratedMutableMap.map27777map
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
                                        GeneratedMutableMap.map27776map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27778map
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
                                GeneratedMutableMap.map27778map
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
                                        GeneratedMutableMap.map27847map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27849map
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
                                GeneratedMutableMap.map27849map
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
                                        GeneratedMutableMap.map27848map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27850map
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
                                GeneratedMutableMap.map27850map
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
                                        GeneratedMutableMap.map27919map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "hash"
                        ), listOf(
                                GeneratedMutableMap.map27921map
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
                                GeneratedMutableMap.map27921map
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
                                        GeneratedMutableMap.map27920map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("hash")
                        ), listOf(
                                GeneratedMutableMap.map27922map
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
                                GeneratedMutableMap.map27922map
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
                                        GeneratedMutableMap.map28096map,
                                        GeneratedMutableMap.map28097map,
                                        GeneratedMutableMap.map28098map,
                                        GeneratedMutableMap.map28099map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28104map,
                                GeneratedMutableMap.map28105map,
                                GeneratedMutableMap.map28106map,
                                GeneratedMutableMap.map28107map
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
                                GeneratedMutableMap.map28104map,
                                GeneratedMutableMap.map28105map,
                                GeneratedMutableMap.map28106map,
                                GeneratedMutableMap.map28107map
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
                                        GeneratedMutableMap.map28100map,
                                        GeneratedMutableMap.map28101map,
                                        GeneratedMutableMap.map28102map,
                                        GeneratedMutableMap.map28103map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28108map,
                                GeneratedMutableMap.map28109map,
                                GeneratedMutableMap.map28110map,
                                GeneratedMutableMap.map28111map
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
                                GeneratedMutableMap.map28108map,
                                GeneratedMutableMap.map28109map,
                                GeneratedMutableMap.map28110map,
                                GeneratedMutableMap.map28111map
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
                                        GeneratedMutableMap.map28186map,
                                        GeneratedMutableMap.map28187map,
                                        GeneratedMutableMap.map28188map,
                                        GeneratedMutableMap.map28189map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28194map,
                                GeneratedMutableMap.map28195map,
                                GeneratedMutableMap.map28196map,
                                GeneratedMutableMap.map28197map
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
                                GeneratedMutableMap.map28194map,
                                GeneratedMutableMap.map28195map,
                                GeneratedMutableMap.map28196map,
                                GeneratedMutableMap.map28197map
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
                                        GeneratedMutableMap.map28190map,
                                        GeneratedMutableMap.map28191map,
                                        GeneratedMutableMap.map28192map,
                                        GeneratedMutableMap.map28193map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28198map,
                                GeneratedMutableMap.map28199map,
                                GeneratedMutableMap.map28200map,
                                GeneratedMutableMap.map28201map
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
                                GeneratedMutableMap.map28198map,
                                GeneratedMutableMap.map28199map,
                                GeneratedMutableMap.map28200map,
                                GeneratedMutableMap.map28201map
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
                                        GeneratedMutableMap.map28313map,
                                        GeneratedMutableMap.map28314map,
                                        GeneratedMutableMap.map28315map,
                                        GeneratedMutableMap.map28316map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28321map,
                                GeneratedMutableMap.map28322map,
                                GeneratedMutableMap.map28323map,
                                GeneratedMutableMap.map28324map
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
                                GeneratedMutableMap.map28321map,
                                GeneratedMutableMap.map28322map,
                                GeneratedMutableMap.map28323map,
                                GeneratedMutableMap.map28324map
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
                                        GeneratedMutableMap.map28317map,
                                        GeneratedMutableMap.map28318map,
                                        GeneratedMutableMap.map28319map,
                                        GeneratedMutableMap.map28320map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28325map,
                                GeneratedMutableMap.map28326map,
                                GeneratedMutableMap.map28327map,
                                GeneratedMutableMap.map28328map
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
                                GeneratedMutableMap.map28325map,
                                GeneratedMutableMap.map28326map,
                                GeneratedMutableMap.map28327map,
                                GeneratedMutableMap.map28328map
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
                                        GeneratedMutableMap.map28440map,
                                        GeneratedMutableMap.map28441map,
                                        GeneratedMutableMap.map28442map,
                                        GeneratedMutableMap.map28099map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28446map,
                                GeneratedMutableMap.map28447map,
                                GeneratedMutableMap.map28448map,
                                GeneratedMutableMap.map28107map
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
                                GeneratedMutableMap.map28446map,
                                GeneratedMutableMap.map28447map,
                                GeneratedMutableMap.map28448map,
                                GeneratedMutableMap.map28107map
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
                                        GeneratedMutableMap.map28443map,
                                        GeneratedMutableMap.map28444map,
                                        GeneratedMutableMap.map28445map,
                                        GeneratedMutableMap.map28103map
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
                                GeneratedMutableMap.map28111map
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
                                GeneratedMutableMap.map28449map,
                                GeneratedMutableMap.map28450map,
                                GeneratedMutableMap.map28451map,
                                GeneratedMutableMap.map28111map
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
                                        GeneratedMutableMap.map28563map,
                                        GeneratedMutableMap.map28564map,
                                        GeneratedMutableMap.map28565map,
                                        GeneratedMutableMap.map28566map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28571map,
                                GeneratedMutableMap.map28572map,
                                GeneratedMutableMap.map28573map,
                                GeneratedMutableMap.map28574map
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
                                GeneratedMutableMap.map28571map,
                                GeneratedMutableMap.map28572map,
                                GeneratedMutableMap.map28573map,
                                GeneratedMutableMap.map28574map
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
                                        GeneratedMutableMap.map28567map,
                                        GeneratedMutableMap.map28568map,
                                        GeneratedMutableMap.map28569map,
                                        GeneratedMutableMap.map28570map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28575map,
                                GeneratedMutableMap.map28576map,
                                GeneratedMutableMap.map28577map,
                                GeneratedMutableMap.map28578map
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
                                GeneratedMutableMap.map28575map,
                                GeneratedMutableMap.map28576map,
                                GeneratedMutableMap.map28577map,
                                GeneratedMutableMap.map28578map
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
                                        GeneratedMutableMap.map28690map,
                                        GeneratedMutableMap.map28691map,
                                        GeneratedMutableMap.map28692map,
                                        GeneratedMutableMap.map28316map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28696map,
                                GeneratedMutableMap.map28697map,
                                GeneratedMutableMap.map28698map,
                                GeneratedMutableMap.map28324map
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
                                GeneratedMutableMap.map28696map,
                                GeneratedMutableMap.map28697map,
                                GeneratedMutableMap.map28698map,
                                GeneratedMutableMap.map28324map
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
                                        GeneratedMutableMap.map28693map,
                                        GeneratedMutableMap.map28694map,
                                        GeneratedMutableMap.map28695map,
                                        GeneratedMutableMap.map28320map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28699map,
                                GeneratedMutableMap.map28700map,
                                GeneratedMutableMap.map28701map,
                                GeneratedMutableMap.map28328map
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
                                GeneratedMutableMap.map28699map,
                                GeneratedMutableMap.map28700map,
                                GeneratedMutableMap.map28701map,
                                GeneratedMutableMap.map28328map
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
                                GeneratedMutableMap.map28776map,
                                GeneratedMutableMap.map28777map,
                                GeneratedMutableMap.map28778map,
                                GeneratedMutableMap.map28779map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28780map,
                            GeneratedMutableMap.map28781map,
                            GeneratedMutableMap.map28782map,
                            GeneratedMutableMap.map28783map
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
                                        GeneratedMutableMap.map28895map,
                                        GeneratedMutableMap.map28896map,
                                        GeneratedMutableMap.map28897map,
                                        GeneratedMutableMap.map28779map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map28902map,
                                GeneratedMutableMap.map28903map,
                                GeneratedMutableMap.map28904map,
                                GeneratedMutableMap.map28783map
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
                                GeneratedMutableMap.map28902map,
                                GeneratedMutableMap.map28903map,
                                GeneratedMutableMap.map28904map,
                                GeneratedMutableMap.map28783map
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
                                        GeneratedMutableMap.map28898map,
                                        GeneratedMutableMap.map28899map,
                                        GeneratedMutableMap.map28900map,
                                        GeneratedMutableMap.map28901map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map28905map,
                                GeneratedMutableMap.map28906map,
                                GeneratedMutableMap.map28907map,
                                GeneratedMutableMap.map28908map
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
                                GeneratedMutableMap.map28905map,
                                GeneratedMutableMap.map28906map,
                                GeneratedMutableMap.map28907map,
                                GeneratedMutableMap.map28908map
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
                                        GeneratedMutableMap.map31068map,
                                        GeneratedMutableMap.map31069map,
                                        GeneratedMutableMap.map31070map,
                                        GeneratedMutableMap.map31071map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "s2",
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31076map,
                                GeneratedMutableMap.map31077map,
                                GeneratedMutableMap.map31078map,
                                GeneratedMutableMap.map31079map
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
                                GeneratedMutableMap.map31076map,
                                GeneratedMutableMap.map31077map,
                                GeneratedMutableMap.map31078map,
                                GeneratedMutableMap.map31079map
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
                                        GeneratedMutableMap.map31072map,
                                        GeneratedMutableMap.map31073map,
                                        GeneratedMutableMap.map31074map,
                                        GeneratedMutableMap.map31075map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("s2"),
                                AOPVariable("b1"),
                                AOPVariable("b2")
                        ), listOf(
                                GeneratedMutableMap.map31080map,
                                GeneratedMutableMap.map31081map,
                                GeneratedMutableMap.map31082map,
                                GeneratedMutableMap.map31083map
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
                                GeneratedMutableMap.map31080map,
                                GeneratedMutableMap.map31081map,
                                GeneratedMutableMap.map31082map,
                                GeneratedMutableMap.map31083map
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
                                        GeneratedMutableMap.map31160map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31161map
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
                                        GeneratedMutableMap.map31163map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "b1",
                                "b2"
                        ), listOf(
                                GeneratedMutableMap.map31164map
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
                                        GeneratedMutableMap.map31436map
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
                                        GeneratedMutableMap.map31527map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "uri",
                                "iri"
                        ), listOf(
                                GeneratedMutableMap.map31528map
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
                                        GeneratedMutableMap.map31753map,
                                        GeneratedMutableMap.map31754map,
                                        GeneratedMutableMap.map31755map,
                                        GeneratedMutableMap.map31756map,
                                        GeneratedMutableMap.map31757map,
                                        GeneratedMutableMap.map31758map,
                                        GeneratedMutableMap.map31759map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "integer"
                        ), listOf(
                                GeneratedMutableMap.map31767map,
                                GeneratedMutableMap.map31768map,
                                GeneratedMutableMap.map31769map,
                                GeneratedMutableMap.map31770map,
                                GeneratedMutableMap.map31771map,
                                GeneratedMutableMap.map31772map,
                                GeneratedMutableMap.map31773map
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
                                GeneratedMutableMap.map31767map,
                                GeneratedMutableMap.map31768map,
                                GeneratedMutableMap.map31769map,
                                GeneratedMutableMap.map31770map,
                                GeneratedMutableMap.map31771map,
                                GeneratedMutableMap.map31772map,
                                GeneratedMutableMap.map31773map
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
                                        GeneratedMutableMap.map31760map,
                                        GeneratedMutableMap.map31761map,
                                        GeneratedMutableMap.map31762map,
                                        GeneratedMutableMap.map31763map,
                                        GeneratedMutableMap.map31764map,
                                        GeneratedMutableMap.map31765map,
                                        GeneratedMutableMap.map31766map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("integer")
                        ), listOf(
                                GeneratedMutableMap.map31774map,
                                GeneratedMutableMap.map31775map,
                                GeneratedMutableMap.map31776map,
                                GeneratedMutableMap.map31777map,
                                GeneratedMutableMap.map31778map,
                                GeneratedMutableMap.map31779map,
                                GeneratedMutableMap.map31780map
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
                                GeneratedMutableMap.map31774map,
                                GeneratedMutableMap.map31775map,
                                GeneratedMutableMap.map31776map,
                                GeneratedMutableMap.map31777map,
                                GeneratedMutableMap.map31778map,
                                GeneratedMutableMap.map31779map,
                                GeneratedMutableMap.map31780map
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
                                        GeneratedMutableMap.map31842map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                        AOPVariable("s")
                                ),
                                POPValues(dictionary, listOf(
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map32428map,
                                        GeneratedMutableMap.map32429map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map32428map,
                                GeneratedMutableMap.map32429map
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
                                        GeneratedMutableMap.map32565map,
                                        GeneratedMutableMap.map32566map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "w",
                                "S"
                        ), listOf(
                                GeneratedMutableMap.map32565map,
                                GeneratedMutableMap.map32566map
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
                                        GeneratedMutableMap.map32559map,
                                        GeneratedMutableMap.map32710map,
                                        GeneratedMutableMap.map32711map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32559map,
                                GeneratedMutableMap.map32710map,
                                GeneratedMutableMap.map32711map
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
                                        GeneratedMutableMap.map10284map,
                                        GeneratedMutableMap.map10285map,
                                        GeneratedMutableMap.map32984map,
                                        GeneratedMutableMap.map10287map,
                                        GeneratedMutableMap.map32985map,
                                        GeneratedMutableMap.map10289map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map32984map,
                                GeneratedMutableMap.map10287map,
                                GeneratedMutableMap.map32985map,
                                GeneratedMutableMap.map10289map
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
                                        GeneratedMutableMap.map10366map,
                                        GeneratedMutableMap.map10367map,
                                        GeneratedMutableMap.map32986map,
                                        GeneratedMutableMap.map10369map,
                                        GeneratedMutableMap.map32987map,
                                        GeneratedMutableMap.map10371map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map10367map,
                                GeneratedMutableMap.map32986map,
                                GeneratedMutableMap.map10369map,
                                GeneratedMutableMap.map32987map,
                                GeneratedMutableMap.map10371map
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
                                        GeneratedMutableMap.map10474map,
                                        GeneratedMutableMap.map10475map,
                                        GeneratedMutableMap.map33114map,
                                        GeneratedMutableMap.map10477map,
                                        GeneratedMutableMap.map33115map,
                                        GeneratedMutableMap.map10479map
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
                                        GeneratedMutableMap.map10604map,
                                        GeneratedMutableMap.map10605map,
                                        GeneratedMutableMap.map33116map,
                                        GeneratedMutableMap.map10607map,
                                        GeneratedMutableMap.map33117map,
                                        GeneratedMutableMap.map10609map
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
                        POPProjection(
                                dictionary,
                                mutableListOf(
                                ),
                                POPValues(dictionary, listOf(
                                ), listOf(
                                        GeneratedMutableMap.map5895map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5895map
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
                                GeneratedMutableMap.map5895map
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
                                        GeneratedMutableMap.map33164map
                                )
                                )
                        ),
                        LOPValues(listOf(
                        ), listOf(
                                GeneratedMutableMap.map33164map
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
                                GeneratedMutableMap.map33164map
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
                                        GeneratedMutableMap.map34849map,
                                        GeneratedMutableMap.map34850map
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
                                        GeneratedMutableMap.map34851map,
                                        GeneratedMutableMap.map34852map
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
                                        GeneratedMutableMap.map34971map,
                                        GeneratedMutableMap.map34972map
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
                                        GeneratedMutableMap.map34973map,
                                        GeneratedMutableMap.map34974map
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
                                GeneratedMutableMap.map35076map
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
                            GeneratedMutableMap.map35077map
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
                                        GeneratedMutableMap.map35189map,
                                        GeneratedMutableMap.map35190map
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
                                        GeneratedMutableMap.map34848map,
                                        GeneratedMutableMap.map35191map
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
                                        GeneratedMutableMap.map35286map,
                                        GeneratedMutableMap.map35287map
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
                                        GeneratedMutableMap.map35288map,
                                        GeneratedMutableMap.map35289map
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
                                        GeneratedMutableMap.map35369map
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
                                GeneratedMutableMap.map35369map
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
                                        GeneratedMutableMap.map35370map
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
                                GeneratedMutableMap.map35370map
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
                                        GeneratedMutableMap.map35466map,
                                        GeneratedMutableMap.map35467map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "dt"
                        ), listOf(
                                GeneratedMutableMap.map35470map,
                                GeneratedMutableMap.map35471map
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
                                GeneratedMutableMap.map35470map,
                                GeneratedMutableMap.map35471map
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
                                        GeneratedMutableMap.map35468map,
                                        GeneratedMutableMap.map35469map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("dt")
                        ), listOf(
                                GeneratedMutableMap.map35472map,
                                GeneratedMutableMap.map35473map
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
                                GeneratedMutableMap.map35472map,
                                GeneratedMutableMap.map35473map
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
                                        GeneratedMutableMap.map36952map,
                                        GeneratedMutableMap.map36953map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36952map,
                                GeneratedMutableMap.map36953map
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
                                GeneratedMutableMap.map36952map,
                                GeneratedMutableMap.map36953map
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
                                        GeneratedMutableMap.map36956map,
                                        GeneratedMutableMap.map36957map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map36956map,
                                GeneratedMutableMap.map36957map
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
                                GeneratedMutableMap.map36956map,
                                GeneratedMutableMap.map36957map
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
                                        "#_36776",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map36922map,
                                        GeneratedMutableMap.map36925map,
                                        GeneratedMutableMap.map36926map,
                                        GeneratedMutableMap.map36923map,
                                        GeneratedMutableMap.map36927map,
                                        GeneratedMutableMap.map36924map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36961map,
                                GeneratedMutableMap.map36962map,
                                GeneratedMutableMap.map36963map,
                                GeneratedMutableMap.map36964map
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
                                                false)

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36961map,
                                GeneratedMutableMap.map36962map,
                                GeneratedMutableMap.map36963map,
                                GeneratedMutableMap.map36964map
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
                                        AOPVariable("#_36776"),
                                        AOPVariable("L"),
                                        AOPVariable("O")
                                ), listOf(
                                        GeneratedMutableMap.map36942map,
                                        GeneratedMutableMap.map36945map,
                                        GeneratedMutableMap.map36946map,
                                        GeneratedMutableMap.map36943map,
                                        GeneratedMutableMap.map36947map,
                                        GeneratedMutableMap.map36944map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36966map,
                                GeneratedMutableMap.map36967map,
                                GeneratedMutableMap.map36968map,
                                GeneratedMutableMap.map36969map
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
                                                false)

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36966map,
                                GeneratedMutableMap.map36967map,
                                GeneratedMutableMap.map36968map,
                                GeneratedMutableMap.map36969map
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
                                        GeneratedMutableMap.map37158map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "FullName"
                        ), listOf(
                                GeneratedMutableMap.map37160map
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
                                GeneratedMutableMap.map37160map
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
                                        GeneratedMutableMap.map37159map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("FullName")
                        ), listOf(
                                GeneratedMutableMap.map37161map
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
                                GeneratedMutableMap.map37161map
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
                                        GeneratedMutableMap.map37166map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map37166map
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
                                GeneratedMutableMap.map37166map
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
                                        GeneratedMutableMap.map37167map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map37167map
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
                                GeneratedMutableMap.map37167map
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
                                        "#_37191",
                                        "L",
                                        "O"
                                ), listOf(
                                        GeneratedMutableMap.map37347map,
                                        GeneratedMutableMap.map37350map,
                                        GeneratedMutableMap.map37351map,
                                        GeneratedMutableMap.map37348map,
                                        GeneratedMutableMap.map37352map,
                                        GeneratedMutableMap.map37349map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36961map,
                                GeneratedMutableMap.map36962map,
                                GeneratedMutableMap.map36963map,
                                GeneratedMutableMap.map36964map
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
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36960map,
                                GeneratedMutableMap.map36961map,
                                GeneratedMutableMap.map36962map,
                                GeneratedMutableMap.map36963map,
                                GeneratedMutableMap.map36964map
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
                                        GeneratedMutableMap.map37357map,
                                        GeneratedMutableMap.map37360map,
                                        GeneratedMutableMap.map37361map,
                                        GeneratedMutableMap.map37358map,
                                        GeneratedMutableMap.map37362map,
                                        GeneratedMutableMap.map37359map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36966map,
                                GeneratedMutableMap.map36967map,
                                GeneratedMutableMap.map36968map,
                                GeneratedMutableMap.map36969map
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
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36965map,
                                GeneratedMutableMap.map36966map,
                                GeneratedMutableMap.map36967map,
                                GeneratedMutableMap.map36968map,
                                GeneratedMutableMap.map36969map
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
