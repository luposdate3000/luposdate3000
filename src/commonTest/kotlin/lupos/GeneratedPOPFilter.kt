package lupos

import lupos.s10physicalOptimisation.PhysicalOptimizer
import lupos.s13keyDistributionOptimizer.KeyDistributionOptimizer
import lupos.s12p2p.P2P
import lupos.s14endpoint.EndpointImpl
import lupos.s00misc.*
import lupos.s15tripleStoreDistributed.*
import lupos.s02buildSyntaxTree.sparql1_1.*
import lupos.s03resultRepresentation.*
import lupos.s04arithmetikOperators.*
import lupos.s04arithmetikOperators.multiinput.*
import lupos.s04arithmetikOperators.noinput.*
import lupos.s04arithmetikOperators.singleinput.*
import lupos.s04logicalOperators.*
import lupos.s04logicalOperators.noinput.*
import lupos.s04logicalOperators.multiinput.*
import lupos.s04logicalOperators.singleinput.*
import lupos.s04logicalOperators.singleinput.modifiers.*
import lupos.s08logicalOptimisation.*
import lupos.s09physicalOperators.*
import lupos.s09physicalOperators.multiinput.*
import lupos.s09physicalOperators.noinput.*
import lupos.s09physicalOperators.singleinput.*
import lupos.s09physicalOperators.singleinput.modifiers.*
import lupos.s11outputResult.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*


class GeneratedPOPFilterTest {
    constructor() {
        P2P.knownClients.clear()
        P2P.knownClients.add(EndpointImpl.fullname)
    }
    fun setAggregationMode(node: OPBase, mode: Boolean, count: Int) {
        for (n in  node.children)
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
                        AOPVariable("#f1185"),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1185",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map1419map,
                                GeneratedMutableMap.map1420map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "#f1185",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map1419map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPVariable("#f1745"),
                        POPValues(dictionary, listOf(
                                "#f1745",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map1966map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#f1745",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map1966map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPVariable("#f2006"),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f2006",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map2238map,
                                GeneratedMutableMap.map2239map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "#f2006",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map2238map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPVariable("#f3674"),
                        POPValues(dictionary, listOf(
                                "s",
                                "#f3674",
                                "avg"
                            ), listOf(
                                GeneratedMutableMap.map3944map,
                                GeneratedMutableMap.map3945map,
                                GeneratedMutableMap.map3946map,
                                GeneratedMutableMap.map3947map,
                                GeneratedMutableMap.map3948map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "#f3674",
                            "avg"
                        ), listOf(
                            GeneratedMutableMap.map3946map,
                            GeneratedMutableMap.map3947map,
                            GeneratedMutableMap.map3948map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
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
                                GeneratedMutableMap.map4683map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            GeneratedMutableMap.map4683map
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
                                GeneratedMutableMap.map7541map,
                                GeneratedMutableMap.map7542map,
                                GeneratedMutableMap.map7543map,
                                GeneratedMutableMap.map7544map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map7545map,
                                GeneratedMutableMap.map7546map,
                                GeneratedMutableMap.map7547map,
                                GeneratedMutableMap.map7548map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
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
                                GeneratedMutableMap.map8151map,
                                GeneratedMutableMap.map8152map,
                                GeneratedMutableMap.map8153map,
                                GeneratedMutableMap.map8154map
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
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
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
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map8155map,
                                GeneratedMutableMap.map8156map,
                                GeneratedMutableMap.map8157map,
                                GeneratedMutableMap.map8158map
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
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPUndef(),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                                                    }()

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
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
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
                                GeneratedMutableMap.map8315map,
                                GeneratedMutableMap.map8316map,
                                GeneratedMutableMap.map8317map,
                                GeneratedMutableMap.map8318map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map8318map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map8318map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map8319map,
                                GeneratedMutableMap.map8320map,
                                GeneratedMutableMap.map8321map,
                                GeneratedMutableMap.map8322map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map8322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPInteger(4),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map8322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
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
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("_:1","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("_:1","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
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
                                GeneratedMutableMap.map22835map,
                                GeneratedMutableMap.map22836map,
                                GeneratedMutableMap.map22837map,
                                GeneratedMutableMap.map22838map,
                                GeneratedMutableMap.map22839map,
                                GeneratedMutableMap.map22840map,
                                GeneratedMutableMap.map22841map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/str>","str",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22842map,
                                GeneratedMutableMap.map22843map,
                                GeneratedMutableMap.map22844map,
                                GeneratedMutableMap.map22845map,
                                GeneratedMutableMap.map22846map,
                                GeneratedMutableMap.map22847map,
                                GeneratedMutableMap.map22848map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
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
                                GeneratedMutableMap.map24279map,
                                GeneratedMutableMap.map24280map,
                                GeneratedMutableMap.map24281map,
                                GeneratedMutableMap.map24282map,
                                GeneratedMutableMap.map24283map,
                                GeneratedMutableMap.map24284map,
                                GeneratedMutableMap.map24285map,
                                GeneratedMutableMap.map24286map,
                                GeneratedMutableMap.map24287map,
                                GeneratedMutableMap.map24288map,
                                GeneratedMutableMap.map24289map,
                                GeneratedMutableMap.map24290map,
                                GeneratedMutableMap.map24291map,
                                GeneratedMutableMap.map24292map,
                                GeneratedMutableMap.map24293map,
                                GeneratedMutableMap.map24294map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24283map,
                            GeneratedMutableMap.map24284map,
                            GeneratedMutableMap.map24285map,
                            GeneratedMutableMap.map24286map,
                            GeneratedMutableMap.map24287map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","num",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24283map,
                            GeneratedMutableMap.map24284map,
                            GeneratedMutableMap.map24285map,
                            GeneratedMutableMap.map24286map,
                            GeneratedMutableMap.map24287map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("num")
                            ), listOf(
                                GeneratedMutableMap.map23314map,
                                GeneratedMutableMap.map23315map,
                                GeneratedMutableMap.map23316map,
                                GeneratedMutableMap.map23317map,
                                GeneratedMutableMap.map23318map,
                                GeneratedMutableMap.map23319map,
                                GeneratedMutableMap.map23320map,
                                GeneratedMutableMap.map23321map,
                                GeneratedMutableMap.map23322map,
                                GeneratedMutableMap.map23323map,
                                GeneratedMutableMap.map23324map,
                                GeneratedMutableMap.map23325map,
                                GeneratedMutableMap.map23326map,
                                GeneratedMutableMap.map23327map,
                                GeneratedMutableMap.map23328map,
                                GeneratedMutableMap.map23329map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map23318map,
                            GeneratedMutableMap.map23319map,
                            GeneratedMutableMap.map23320map,
                            GeneratedMutableMap.map23321map,
                            GeneratedMutableMap.map23322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("num"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map23318map,
                            GeneratedMutableMap.map23319map,
                            GeneratedMutableMap.map23320map,
                            GeneratedMutableMap.map23321map,
                            GeneratedMutableMap.map23322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
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
                                GeneratedMutableMap.map24295map,
                                GeneratedMutableMap.map24296map,
                                GeneratedMutableMap.map24297map,
                                GeneratedMutableMap.map24298map,
                                GeneratedMutableMap.map24299map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24298map,
                            GeneratedMutableMap.map24299map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/num>","num",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24298map,
                            GeneratedMutableMap.map24299map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num")
                            ), listOf(
                                GeneratedMutableMap.map24300map,
                                GeneratedMutableMap.map24301map,
                                GeneratedMutableMap.map24302map,
                                GeneratedMutableMap.map24303map,
                                GeneratedMutableMap.map24304map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map24303map,
                            GeneratedMutableMap.map24304map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map24303map,
                            GeneratedMutableMap.map24304map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
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
                                GeneratedMutableMap.map22835map,
                                GeneratedMutableMap.map22836map,
                                GeneratedMutableMap.map22837map,
                                GeneratedMutableMap.map22838map,
                                GeneratedMutableMap.map22839map,
                                GeneratedMutableMap.map22840map,
                                GeneratedMutableMap.map22841map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map,
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/str>","str",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map,
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22842map,
                                GeneratedMutableMap.map22843map,
                                GeneratedMutableMap.map22844map,
                                GeneratedMutableMap.map22845map,
                                GeneratedMutableMap.map22846map,
                                GeneratedMutableMap.map22847map,
                                GeneratedMutableMap.map22848map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
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
                                GeneratedMutableMap.map26823map,
                                GeneratedMutableMap.map26824map,
                                GeneratedMutableMap.map26825map,
                                GeneratedMutableMap.map26826map,
                                GeneratedMutableMap.map26827map,
                                GeneratedMutableMap.map26828map,
                                GeneratedMutableMap.map26829map,
                                GeneratedMutableMap.map26830map,
                                GeneratedMutableMap.map26831map,
                                GeneratedMutableMap.map26832map,
                                GeneratedMutableMap.map26833map,
                                GeneratedMutableMap.map26834map,
                                GeneratedMutableMap.map26835map,
                                GeneratedMutableMap.map26836map,
                                GeneratedMutableMap.map26837map,
                                GeneratedMutableMap.map26838map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26829map,
                            GeneratedMutableMap.map26836map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","str",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26829map,
                            GeneratedMutableMap.map26836map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map23314map,
                                GeneratedMutableMap.map23315map,
                                GeneratedMutableMap.map23316map,
                                GeneratedMutableMap.map23317map,
                                GeneratedMutableMap.map23318map,
                                GeneratedMutableMap.map23319map,
                                GeneratedMutableMap.map23320map,
                                GeneratedMutableMap.map23321map,
                                GeneratedMutableMap.map23322map,
                                GeneratedMutableMap.map23323map,
                                GeneratedMutableMap.map23324map,
                                GeneratedMutableMap.map23325map,
                                GeneratedMutableMap.map23326map,
                                GeneratedMutableMap.map23327map,
                                GeneratedMutableMap.map23328map,
                                GeneratedMutableMap.map23329map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map23320map,
                            GeneratedMutableMap.map23327map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("str"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map23320map,
                            GeneratedMutableMap.map23327map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
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
                                GeneratedMutableMap.map26823map,
                                GeneratedMutableMap.map26824map,
                                GeneratedMutableMap.map26825map,
                                GeneratedMutableMap.map26826map,
                                GeneratedMutableMap.map26827map,
                                GeneratedMutableMap.map26828map,
                                GeneratedMutableMap.map26829map,
                                GeneratedMutableMap.map26830map,
                                GeneratedMutableMap.map26831map,
                                GeneratedMutableMap.map26832map,
                                GeneratedMutableMap.map26833map,
                                GeneratedMutableMap.map26834map,
                                GeneratedMutableMap.map26835map,
                                GeneratedMutableMap.map26836map,
                                GeneratedMutableMap.map26837map,
                                GeneratedMutableMap.map26838map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26837map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","str",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26837map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map23314map,
                                GeneratedMutableMap.map23315map,
                                GeneratedMutableMap.map23316map,
                                GeneratedMutableMap.map23317map,
                                GeneratedMutableMap.map23318map,
                                GeneratedMutableMap.map23319map,
                                GeneratedMutableMap.map23320map,
                                GeneratedMutableMap.map23321map,
                                GeneratedMutableMap.map23322map,
                                GeneratedMutableMap.map23323map,
                                GeneratedMutableMap.map23324map,
                                GeneratedMutableMap.map23325map,
                                GeneratedMutableMap.map23326map,
                                GeneratedMutableMap.map23327map,
                                GeneratedMutableMap.map23328map,
                                GeneratedMutableMap.map23329map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map23328map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("str"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map23328map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
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
                                GeneratedMutableMap.map31137map,
                                GeneratedMutableMap.map31138map,
                                GeneratedMutableMap.map31139map,
                                GeneratedMutableMap.map31140map,
                                GeneratedMutableMap.map31141map,
                                GeneratedMutableMap.map31142map,
                                GeneratedMutableMap.map31143map,
                                GeneratedMutableMap.map31144map,
                                GeneratedMutableMap.map31145map,
                                GeneratedMutableMap.map31146map,
                                GeneratedMutableMap.map31147map,
                                GeneratedMutableMap.map31148map,
                                GeneratedMutableMap.map31149map,
                                GeneratedMutableMap.map31150map,
                                GeneratedMutableMap.map31151map,
                                GeneratedMutableMap.map31152map,
                                GeneratedMutableMap.map31153map,
                                GeneratedMutableMap.map31154map,
                                GeneratedMutableMap.map31155map,
                                GeneratedMutableMap.map31156map,
                                GeneratedMutableMap.map31157map,
                                GeneratedMutableMap.map31158map,
                                GeneratedMutableMap.map31159map,
                                GeneratedMutableMap.map31160map,
                                GeneratedMutableMap.map31161map,
                                GeneratedMutableMap.map31162map,
                                GeneratedMutableMap.map31163map,
                                GeneratedMutableMap.map31164map,
                                GeneratedMutableMap.map31165map,
                                GeneratedMutableMap.map31166map,
                                GeneratedMutableMap.map31167map,
                                GeneratedMutableMap.map31168map,
                                GeneratedMutableMap.map31169map,
                                GeneratedMutableMap.map31170map,
                                GeneratedMutableMap.map31171map,
                                GeneratedMutableMap.map31172map,
                                GeneratedMutableMap.map31173map,
                                GeneratedMutableMap.map31174map,
                                GeneratedMutableMap.map31175map,
                                GeneratedMutableMap.map31176map,
                                GeneratedMutableMap.map31177map,
                                GeneratedMutableMap.map31178map,
                                GeneratedMutableMap.map31179map,
                                GeneratedMutableMap.map31180map,
                                GeneratedMutableMap.map31181map,
                                GeneratedMutableMap.map31182map,
                                GeneratedMutableMap.map31183map,
                                GeneratedMutableMap.map31184map,
                                GeneratedMutableMap.map31185map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map31137map,
                            GeneratedMutableMap.map31138map,
                            GeneratedMutableMap.map31139map,
                            GeneratedMutableMap.map31140map,
                            GeneratedMutableMap.map31141map,
                            GeneratedMutableMap.map31142map,
                            GeneratedMutableMap.map31143map,
                            GeneratedMutableMap.map31151map,
                            GeneratedMutableMap.map31152map,
                            GeneratedMutableMap.map31153map,
                            GeneratedMutableMap.map31154map,
                            GeneratedMutableMap.map31155map,
                            GeneratedMutableMap.map31156map,
                            GeneratedMutableMap.map31157map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://example.org/str>","s1",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://example.org/str>","s2",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map31137map,
                            GeneratedMutableMap.map31138map,
                            GeneratedMutableMap.map31139map,
                            GeneratedMutableMap.map31140map,
                            GeneratedMutableMap.map31141map,
                            GeneratedMutableMap.map31142map,
                            GeneratedMutableMap.map31143map,
                            GeneratedMutableMap.map31151map,
                            GeneratedMutableMap.map31152map,
                            GeneratedMutableMap.map31153map,
                            GeneratedMutableMap.map31154map,
                            GeneratedMutableMap.map31155map,
                            GeneratedMutableMap.map31156map,
                            GeneratedMutableMap.map31157map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map31186map,
                                GeneratedMutableMap.map31187map,
                                GeneratedMutableMap.map31188map,
                                GeneratedMutableMap.map31189map,
                                GeneratedMutableMap.map31190map,
                                GeneratedMutableMap.map31191map,
                                GeneratedMutableMap.map31192map,
                                GeneratedMutableMap.map31193map,
                                GeneratedMutableMap.map31194map,
                                GeneratedMutableMap.map31195map,
                                GeneratedMutableMap.map31196map,
                                GeneratedMutableMap.map31197map,
                                GeneratedMutableMap.map31198map,
                                GeneratedMutableMap.map31199map,
                                GeneratedMutableMap.map31200map,
                                GeneratedMutableMap.map31201map,
                                GeneratedMutableMap.map31202map,
                                GeneratedMutableMap.map31203map,
                                GeneratedMutableMap.map31204map,
                                GeneratedMutableMap.map31205map,
                                GeneratedMutableMap.map31206map,
                                GeneratedMutableMap.map31207map,
                                GeneratedMutableMap.map31208map,
                                GeneratedMutableMap.map31209map,
                                GeneratedMutableMap.map31210map,
                                GeneratedMutableMap.map31211map,
                                GeneratedMutableMap.map31212map,
                                GeneratedMutableMap.map31213map,
                                GeneratedMutableMap.map31214map,
                                GeneratedMutableMap.map31215map,
                                GeneratedMutableMap.map31216map,
                                GeneratedMutableMap.map31217map,
                                GeneratedMutableMap.map31218map,
                                GeneratedMutableMap.map31219map,
                                GeneratedMutableMap.map31220map,
                                GeneratedMutableMap.map31221map,
                                GeneratedMutableMap.map31222map,
                                GeneratedMutableMap.map31223map,
                                GeneratedMutableMap.map31224map,
                                GeneratedMutableMap.map31225map,
                                GeneratedMutableMap.map31226map,
                                GeneratedMutableMap.map31227map,
                                GeneratedMutableMap.map31228map,
                                GeneratedMutableMap.map31229map,
                                GeneratedMutableMap.map31230map,
                                GeneratedMutableMap.map31231map,
                                GeneratedMutableMap.map31232map,
                                GeneratedMutableMap.map31233map,
                                GeneratedMutableMap.map31234map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map31186map,
                            GeneratedMutableMap.map31187map,
                            GeneratedMutableMap.map31188map,
                            GeneratedMutableMap.map31189map,
                            GeneratedMutableMap.map31190map,
                            GeneratedMutableMap.map31191map,
                            GeneratedMutableMap.map31192map,
                            GeneratedMutableMap.map31200map,
                            GeneratedMutableMap.map31201map,
                            GeneratedMutableMap.map31202map,
                            GeneratedMutableMap.map31203map,
                            GeneratedMutableMap.map31204map,
                            GeneratedMutableMap.map31205map,
                            GeneratedMutableMap.map31206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map31186map,
                            GeneratedMutableMap.map31187map,
                            GeneratedMutableMap.map31188map,
                            GeneratedMutableMap.map31189map,
                            GeneratedMutableMap.map31190map,
                            GeneratedMutableMap.map31191map,
                            GeneratedMutableMap.map31192map,
                            GeneratedMutableMap.map31200map,
                            GeneratedMutableMap.map31201map,
                            GeneratedMutableMap.map31202map,
                            GeneratedMutableMap.map31203map,
                            GeneratedMutableMap.map31204map,
                            GeneratedMutableMap.map31205map,
                            GeneratedMutableMap.map31206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                GeneratedMutableMap.map31137map,
                                GeneratedMutableMap.map31138map,
                                GeneratedMutableMap.map31139map,
                                GeneratedMutableMap.map31140map,
                                GeneratedMutableMap.map31141map,
                                GeneratedMutableMap.map31142map,
                                GeneratedMutableMap.map31143map,
                                GeneratedMutableMap.map31151map,
                                GeneratedMutableMap.map31152map,
                                GeneratedMutableMap.map31153map,
                                GeneratedMutableMap.map31154map,
                                GeneratedMutableMap.map31155map,
                                GeneratedMutableMap.map31156map,
                                GeneratedMutableMap.map31157map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map31137map,
                            GeneratedMutableMap.map31139map,
                            GeneratedMutableMap.map31151map,
                            GeneratedMutableMap.map31153map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://example.org/str>","s1",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://example.org/str>","s2",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map31137map,
                            GeneratedMutableMap.map31139map,
                            GeneratedMutableMap.map31151map,
                            GeneratedMutableMap.map31153map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map31186map,
                                GeneratedMutableMap.map31187map,
                                GeneratedMutableMap.map31188map,
                                GeneratedMutableMap.map31189map,
                                GeneratedMutableMap.map31190map,
                                GeneratedMutableMap.map31191map,
                                GeneratedMutableMap.map31192map,
                                GeneratedMutableMap.map31200map,
                                GeneratedMutableMap.map31201map,
                                GeneratedMutableMap.map31202map,
                                GeneratedMutableMap.map31203map,
                                GeneratedMutableMap.map31204map,
                                GeneratedMutableMap.map31205map,
                                GeneratedMutableMap.map31206map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map31186map,
                            GeneratedMutableMap.map31188map,
                            GeneratedMutableMap.map31200map,
                            GeneratedMutableMap.map31202map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPFilter(
                        AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                    LOPFilter(
                                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map31186map,
                            GeneratedMutableMap.map31188map,
                            GeneratedMutableMap.map31200map,
                            GeneratedMutableMap.map31202map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPFilter(
                        dictionary,
                        AOPIn(AOPInteger(2), AOPSet(AOPInteger(1), AOPInteger(2), AOPInteger(3))),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map31611map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            GeneratedMutableMap.map31611map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */ ,
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
                    if (!expected.myEquals(output)){
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                } else if (data.input is LOPBase && data is MicroTestLN) {
                    val lop_node = data.input as LOPBase
                    val dictionary = data.dictionary
                    ExecuteOptimizer.enabledOptimizers.clear()
                    val lOptimizer=LogicalOptimizer(1L, dictionary)
                    val pOptimizer=PhysicalOptimizer(1L, dictionary)
                    val dOptimizer=KeyDistributionOptimizer(1L, dictionary)
                    val lop_node2 =lOptimizer.optimizeCall(lop_node)
                    val pop_node = pOptimizer.optimizeCall(lop_node2)
                    val input = dOptimizer.optimizeCall(pop_node) as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)){
                        println(output.toPrettyString())
                        println(expected.toPrettyString())
                    }
                    assertTrue(expected.myEquals(output))
                    for(k in ExecuteOptimizer.enabledOptimizers.keys){
                        ExecuteOptimizer.enabledOptimizers[k]=true
                        val lop_node2 =lOptimizer.optimizeCall(lop_node)
                        val pop_node = pOptimizer.optimizeCall(lop_node2)
                        val input = dOptimizer.optimizeCall(pop_node) as POPBase
                        assertTrue(data.expected is POPValues)
                        val output = QueryResultToXML.toXML(input).first()
                        val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                        if (!expected.myEquals(output)){
                            println(ExecuteOptimizer.enabledOptimizers.keys.map{it to ExecuteOptimizer.enabledOptimizers[it]})
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
