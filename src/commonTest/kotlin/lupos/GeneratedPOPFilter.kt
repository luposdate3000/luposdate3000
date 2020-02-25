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


class GeneratedPOPFilterTest {
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
                        POPFilter(
                                dictionary,
                                AOPVariable("#f1170"),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f1170",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1404map,
                                        GeneratedMutableMap.map1405map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1170",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1404map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPVariable("#f1730"),
                                POPValues(dictionary, listOf(
                                        "#f1730",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1951map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#f1730",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1951map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPVariable("#f1991"),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f1991",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2223map,
                                        GeneratedMutableMap.map2224map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1991",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPVariable("#f3612"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#f3612",
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3882map,
                                        GeneratedMutableMap.map3883map,
                                        GeneratedMutableMap.map3884map,
                                        GeneratedMutableMap.map3885map,
                                        GeneratedMutableMap.map3886map
                                )
                                )
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPOr(AOPEQ(AOPVariable("sample"), AOPDecimal(3.5)), AOPOr(AOPEQ(AOPVariable("sample"), AOPDecimal(2.2)), AOPEQ(AOPVariable("sample"), AOPDecimal(1.0)))),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPVariable("z"), AOPInteger(3)),
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
                        LOPFilter(
                                AOPEQ(AOPVariable("z"), AOPInteger(3)),
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
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                        ), listOf(
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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
                        LOPFilter(
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22407map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPBuildInCallIsNUMERIC(AOPVariable("num")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23782map,
                                        GeneratedMutableMap.map23783map,
                                        GeneratedMutableMap.map23784map,
                                        GeneratedMutableMap.map23785map,
                                        GeneratedMutableMap.map23786map,
                                        GeneratedMutableMap.map23787map,
                                        GeneratedMutableMap.map23788map,
                                        GeneratedMutableMap.map23789map,
                                        GeneratedMutableMap.map23790map,
                                        GeneratedMutableMap.map23791map,
                                        GeneratedMutableMap.map23792map,
                                        GeneratedMutableMap.map23793map,
                                        GeneratedMutableMap.map23794map,
                                        GeneratedMutableMap.map23795map,
                                        GeneratedMutableMap.map23796map,
                                        GeneratedMutableMap.map23797map
                                )
                                )
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPBuildInCallIsNUMERIC(AOPVariable("num")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "num"
                                ), listOf(
                                        GeneratedMutableMap.map23782map,
                                        GeneratedMutableMap.map23783map,
                                        GeneratedMutableMap.map23784map,
                                        GeneratedMutableMap.map23785map,
                                        GeneratedMutableMap.map23786map,
                                        GeneratedMutableMap.map23787map,
                                        GeneratedMutableMap.map23788map,
                                        GeneratedMutableMap.map23789map,
                                        GeneratedMutableMap.map23790map,
                                        GeneratedMutableMap.map23791map,
                                        GeneratedMutableMap.map23792map,
                                        GeneratedMutableMap.map23793map,
                                        GeneratedMutableMap.map23794map,
                                        GeneratedMutableMap.map23795map,
                                        GeneratedMutableMap.map23796map,
                                        GeneratedMutableMap.map23797map
                                )
                                )
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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
                        LOPFilter(
                                AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                        LOPFilter(
                                AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26135map,
                                        GeneratedMutableMap.map26136map,
                                        GeneratedMutableMap.map26137map,
                                        GeneratedMutableMap.map26138map,
                                        GeneratedMutableMap.map26139map,
                                        GeneratedMutableMap.map26140map,
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26142map,
                                        GeneratedMutableMap.map26143map,
                                        GeneratedMutableMap.map26144map,
                                        GeneratedMutableMap.map26145map,
                                        GeneratedMutableMap.map26146map,
                                        GeneratedMutableMap.map26147map,
                                        GeneratedMutableMap.map26148map,
                                        GeneratedMutableMap.map26149map,
                                        GeneratedMutableMap.map26150map
                                )
                                )
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
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26135map,
                                        GeneratedMutableMap.map26136map,
                                        GeneratedMutableMap.map26137map,
                                        GeneratedMutableMap.map26138map,
                                        GeneratedMutableMap.map26139map,
                                        GeneratedMutableMap.map26140map,
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26142map,
                                        GeneratedMutableMap.map26143map,
                                        GeneratedMutableMap.map26144map,
                                        GeneratedMutableMap.map26145map,
                                        GeneratedMutableMap.map26146map,
                                        GeneratedMutableMap.map26147map,
                                        GeneratedMutableMap.map26148map,
                                        GeneratedMutableMap.map26149map,
                                        GeneratedMutableMap.map26150map
                                )
                                )
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
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26135map,
                                        GeneratedMutableMap.map26136map,
                                        GeneratedMutableMap.map26137map,
                                        GeneratedMutableMap.map26138map,
                                        GeneratedMutableMap.map26139map,
                                        GeneratedMutableMap.map26140map,
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26142map,
                                        GeneratedMutableMap.map26143map,
                                        GeneratedMutableMap.map26144map,
                                        GeneratedMutableMap.map26145map,
                                        GeneratedMutableMap.map26146map,
                                        GeneratedMutableMap.map26147map,
                                        GeneratedMutableMap.map26148map,
                                        GeneratedMutableMap.map26149map,
                                        GeneratedMutableMap.map26150map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26149map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26149map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map26135map,
                                        GeneratedMutableMap.map26136map,
                                        GeneratedMutableMap.map26137map,
                                        GeneratedMutableMap.map26138map,
                                        GeneratedMutableMap.map26139map,
                                        GeneratedMutableMap.map26140map,
                                        GeneratedMutableMap.map26141map,
                                        GeneratedMutableMap.map26142map,
                                        GeneratedMutableMap.map26143map,
                                        GeneratedMutableMap.map26144map,
                                        GeneratedMutableMap.map26145map,
                                        GeneratedMutableMap.map26146map,
                                        GeneratedMutableMap.map26147map,
                                        GeneratedMutableMap.map26148map,
                                        GeneratedMutableMap.map26149map,
                                        GeneratedMutableMap.map26150map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26149map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map26149map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                        ),
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
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
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
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                        ),
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
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
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
                                GeneratedMutableMap.map30310map,
                                GeneratedMutableMap.map30311map,
                                GeneratedMutableMap.map30312map,
                                GeneratedMutableMap.map30313map,
                                GeneratedMutableMap.map30314map,
                                GeneratedMutableMap.map30315map,
                                GeneratedMutableMap.map30316map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
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
                                        GeneratedMutableMap.map30310map,
                                        GeneratedMutableMap.map30311map,
                                        GeneratedMutableMap.map30312map,
                                        GeneratedMutableMap.map30313map,
                                        GeneratedMutableMap.map30314map,
                                        GeneratedMutableMap.map30315map,
                                        GeneratedMutableMap.map30316map
                                )
                                )
                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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

                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPFilter(
                                AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
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
                                        GeneratedMutableMap.map30310map,
                                        GeneratedMutableMap.map30311map,
                                        GeneratedMutableMap.map30312map,
                                        GeneratedMutableMap.map30313map,
                                        GeneratedMutableMap.map30314map,
                                        GeneratedMutableMap.map30315map,
                                        GeneratedMutableMap.map30316map
                                )
                                )
                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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

                        ),
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPIn(AOPInteger(2), AOPSet(AOPInteger(1), AOPInteger(2), AOPInteger(3))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/in01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPIn(AOPInteger(2), AOPSet(AOPInteger(1), AOPInteger(3))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/in02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPNotIn(AOPInteger(2), AOPSet()),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/notin01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPNotIn(AOPInteger(2), AOPSet(AOPDivision(AOPInteger(0), AOPInteger(1)), AOPInteger(2))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5828map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/notin02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPBuildInCallDATATYPE(AOPVariable("n")), AOPIri("http://www.w3.org/2001/XMLSchema#dateTime")),
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
