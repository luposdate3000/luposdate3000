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
                                AOPVariable("#f1214"),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f1214",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map1449map,
                                        GeneratedMutableMap.map1450map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1214",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map1449map
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
                                AOPVariable("#f1780"),
                                POPValues(dictionary, listOf(
                                        "#f1780",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2002map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "#f1780",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2002map
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
                                AOPVariable("#f2043"),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "#f2043",
                                        "C"
                                ), listOf(
                                        GeneratedMutableMap.map2276map,
                                        GeneratedMutableMap.map2277map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f2043",
                                "C"
                        ), listOf(
                                GeneratedMutableMap.map2276map
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
                                AOPVariable("#f3715"),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "#f3715",
                                        "avg"
                                ), listOf(
                                        GeneratedMutableMap.map3986map,
                                        GeneratedMutableMap.map3987map,
                                        GeneratedMutableMap.map3988map,
                                        GeneratedMutableMap.map3989map,
                                        GeneratedMutableMap.map3990map
                                )
                                )
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
                                        GeneratedMutableMap.map7608map,
                                        GeneratedMutableMap.map7609map,
                                        GeneratedMutableMap.map7610map,
                                        GeneratedMutableMap.map7611map
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
                                GeneratedMutableMap.map7609map
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
                        POPFilter(
                                dictionary,
                                AOPEQ(AOPVariable("v"), AOPVariable("z")),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
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
                                        GeneratedMutableMap.map8390map,
                                        GeneratedMutableMap.map8391map,
                                        GeneratedMutableMap.map8392map,
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
                                GeneratedMutableMap.map8393map
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
                                GeneratedMutableMap.map7609map
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map22987map
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
                                GeneratedMutableMap.map22987map
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
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22994map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map22994map
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
                                        GeneratedMutableMap.map24440map,
                                        GeneratedMutableMap.map24441map,
                                        GeneratedMutableMap.map24442map,
                                        GeneratedMutableMap.map24443map,
                                        GeneratedMutableMap.map24444map,
                                        GeneratedMutableMap.map24445map,
                                        GeneratedMutableMap.map24446map,
                                        GeneratedMutableMap.map24447map,
                                        GeneratedMutableMap.map24448map,
                                        GeneratedMutableMap.map24449map,
                                        GeneratedMutableMap.map24450map,
                                        GeneratedMutableMap.map24451map,
                                        GeneratedMutableMap.map24452map,
                                        GeneratedMutableMap.map24453map,
                                        GeneratedMutableMap.map24454map,
                                        GeneratedMutableMap.map24455map
                                )
                                )
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
                                GeneratedMutableMap.map24444map,
                                GeneratedMutableMap.map24445map,
                                GeneratedMutableMap.map24446map,
                                GeneratedMutableMap.map24447map,
                                GeneratedMutableMap.map24448map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("num")
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
                                AOPVariable("num")
                        ), listOf(
                                GeneratedMutableMap.map23473map,
                                GeneratedMutableMap.map23474map,
                                GeneratedMutableMap.map23475map,
                                GeneratedMutableMap.map23476map,
                                GeneratedMutableMap.map23477map
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
                        LOPFilter(
                                AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                        LOPFilter(
                                AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                        POPFilter(
                                dictionary,
                                AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                                POPValues(dictionary, listOf(
                                        "s",
                                        "p",
                                        "str"
                                ), listOf(
                                        GeneratedMutableMap.map27003map,
                                        GeneratedMutableMap.map27004map,
                                        GeneratedMutableMap.map27005map,
                                        GeneratedMutableMap.map27006map,
                                        GeneratedMutableMap.map27007map,
                                        GeneratedMutableMap.map27008map,
                                        GeneratedMutableMap.map27009map,
                                        GeneratedMutableMap.map27010map,
                                        GeneratedMutableMap.map27011map,
                                        GeneratedMutableMap.map27012map,
                                        GeneratedMutableMap.map27013map,
                                        GeneratedMutableMap.map27014map,
                                        GeneratedMutableMap.map27015map,
                                        GeneratedMutableMap.map27016map,
                                        GeneratedMutableMap.map27017map,
                                        GeneratedMutableMap.map27018map
                                )
                                )
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
                                GeneratedMutableMap.map27009map,
                                GeneratedMutableMap.map27016map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("str")
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
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map23475map,
                                GeneratedMutableMap.map23482map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map23475map,
                                GeneratedMutableMap.map23482map
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
                                        GeneratedMutableMap.map27003map,
                                        GeneratedMutableMap.map27004map,
                                        GeneratedMutableMap.map27005map,
                                        GeneratedMutableMap.map27006map,
                                        GeneratedMutableMap.map27007map,
                                        GeneratedMutableMap.map27008map,
                                        GeneratedMutableMap.map27009map,
                                        GeneratedMutableMap.map27010map,
                                        GeneratedMutableMap.map27011map,
                                        GeneratedMutableMap.map27012map,
                                        GeneratedMutableMap.map27013map,
                                        GeneratedMutableMap.map27014map,
                                        GeneratedMutableMap.map27015map,
                                        GeneratedMutableMap.map27016map,
                                        GeneratedMutableMap.map27017map,
                                        GeneratedMutableMap.map27018map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                        ), listOf(
                                GeneratedMutableMap.map27017map
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
                                GeneratedMutableMap.map27017map
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
                                LOPValues(listOf(
                                        AOPVariable("s"),
                                        AOPVariable("p"),
                                        AOPVariable("str")
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
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map23483map
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
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                        ), listOf(
                                GeneratedMutableMap.map23483map
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
                        ),
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
                                GeneratedMutableMap.map31331map,
                                GeneratedMutableMap.map31332map,
                                GeneratedMutableMap.map31333map,
                                GeneratedMutableMap.map31334map,
                                GeneratedMutableMap.map31335map,
                                GeneratedMutableMap.map31336map,
                                GeneratedMutableMap.map31337map
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
                                GeneratedMutableMap.map31317map,
                                GeneratedMutableMap.map31318map,
                                GeneratedMutableMap.map31319map,
                                GeneratedMutableMap.map31320map,
                                GeneratedMutableMap.map31321map,
                                GeneratedMutableMap.map31322map,
                                GeneratedMutableMap.map31323map,
                                GeneratedMutableMap.map31331map,
                                GeneratedMutableMap.map31332map,
                                GeneratedMutableMap.map31333map,
                                GeneratedMutableMap.map31334map,
                                GeneratedMutableMap.map31335map,
                                GeneratedMutableMap.map31336map,
                                GeneratedMutableMap.map31337map
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
                        ),
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
                                GeneratedMutableMap.map31380map,
                                GeneratedMutableMap.map31381map,
                                GeneratedMutableMap.map31382map,
                                GeneratedMutableMap.map31383map,
                                GeneratedMutableMap.map31384map,
                                GeneratedMutableMap.map31385map,
                                GeneratedMutableMap.map31386map
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
                                GeneratedMutableMap.map31380map,
                                GeneratedMutableMap.map31381map,
                                GeneratedMutableMap.map31382map,
                                GeneratedMutableMap.map31383map,
                                GeneratedMutableMap.map31384map,
                                GeneratedMutableMap.map31385map,
                                GeneratedMutableMap.map31386map
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
                                        GeneratedMutableMap.map31317map,
                                        GeneratedMutableMap.map31318map,
                                        GeneratedMutableMap.map31319map,
                                        GeneratedMutableMap.map31320map,
                                        GeneratedMutableMap.map31321map,
                                        GeneratedMutableMap.map31322map,
                                        GeneratedMutableMap.map31323map,
                                        GeneratedMutableMap.map31331map,
                                        GeneratedMutableMap.map31332map,
                                        GeneratedMutableMap.map31333map,
                                        GeneratedMutableMap.map31334map,
                                        GeneratedMutableMap.map31335map,
                                        GeneratedMutableMap.map31336map,
                                        GeneratedMutableMap.map31337map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map31317map,
                                GeneratedMutableMap.map31319map,
                                GeneratedMutableMap.map31331map,
                                GeneratedMutableMap.map31333map
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
                                        GeneratedMutableMap.map31380map,
                                        GeneratedMutableMap.map31381map,
                                        GeneratedMutableMap.map31382map,
                                        GeneratedMutableMap.map31383map,
                                        GeneratedMutableMap.map31384map,
                                        GeneratedMutableMap.map31385map,
                                        GeneratedMutableMap.map31386map
                                )
                                )
                        ),
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
                                GeneratedMutableMap.map5965map
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
                                GeneratedMutableMap.map5965map
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
                                GeneratedMutableMap.map5965map
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
                                GeneratedMutableMap.map5965map
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
