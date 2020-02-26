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


class GeneratedPOPBindTest {
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
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/p>","O1",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/q>","O2",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

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
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("O12"),
                        AOPAddition(AOPVariable("O2"), AOPVariable("O1")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/p>"),AOPVariable("O1"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/q>"),AOPVariable("O2"),graphName,false)                                                    }()
,
                                        false                                    )

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
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
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
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map6722map,
                                GeneratedMutableMap.map6723map,
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("z"),
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */ ,
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
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map,
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map
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
                            GeneratedMutableMap.map6885map,
                            GeneratedMutableMap.map6886map,
                            GeneratedMutableMap.map6887map,
                            GeneratedMutableMap.map6888map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
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
                            "z",
                            "z2"
                        ), listOf(
                            GeneratedMutableMap.map6885map,
                            GeneratedMutableMap.map6886map,
                            GeneratedMutableMap.map6887map,
                            GeneratedMutableMap.map6888map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map,
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map
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
                            GeneratedMutableMap.map6889map,
                            GeneratedMutableMap.map6890map,
                            GeneratedMutableMap.map6891map,
                            GeneratedMutableMap.map6892map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ), listOf(
                            GeneratedMutableMap.map6889map,
                            GeneratedMutableMap.map6890map,
                            GeneratedMutableMap.map6891map,
                            GeneratedMutableMap.map6892map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
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
                                GeneratedMutableMap.map7211map,
                                GeneratedMutableMap.map7212map
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
                            GeneratedMutableMap.map7213map,
                            GeneratedMutableMap.map7214map,
                            GeneratedMutableMap.map7215map,
                            GeneratedMutableMap.map7216map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7217map,
                            GeneratedMutableMap.map7218map,
                            GeneratedMutableMap.map7219map,
                            GeneratedMutableMap.map7220map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7221map,
                            GeneratedMutableMap.map7222map,
                            GeneratedMutableMap.map7223map,
                            GeneratedMutableMap.map7224map,
                            GeneratedMutableMap.map7211map,
                            GeneratedMutableMap.map7225map
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
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map6722map,
                                GeneratedMutableMap.map6723map,
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
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
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7723map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
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
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7723map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
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
                                GeneratedMutableMap.map8143map,
                                GeneratedMutableMap.map8144map,
                                GeneratedMutableMap.map8145map,
                                GeneratedMutableMap.map8146map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("z"),
                        AOPUndef(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map8147map,
                                GeneratedMutableMap.map8148map,
                                GeneratedMutableMap.map8149map,
                                GeneratedMutableMap.map8150map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
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
                                GeneratedMutableMap.map8143map,
                                GeneratedMutableMap.map8144map,
                                GeneratedMutableMap.map8145map,
                                GeneratedMutableMap.map8146map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("z"),
                        AOPInteger(4),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map8147map,
                                GeneratedMutableMap.map8148map,
                                GeneratedMutableMap.map8149map,
                                GeneratedMutableMap.map8150map
                            )
                        )
                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                                    }()

                    ),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("z"),
                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
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
                            "z",
                            "z2"
                        ), listOf(
                            GeneratedMutableMap.map6885map,
                            GeneratedMutableMap.map6886map,
                            GeneratedMutableMap.map6887map,
                            GeneratedMutableMap.map6888map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ), listOf(
                            GeneratedMutableMap.map6889map,
                            GeneratedMutableMap.map6890map,
                            GeneratedMutableMap.map6891map,
                            GeneratedMutableMap.map6892map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
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
                                        false                                    )

                    ),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
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
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
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
                                GeneratedMutableMap.map22836map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map22849map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map22849map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22843map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map22850map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map22850map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
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
                                GeneratedMutableMap.map22836map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23123map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23123map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22843map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23124map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23124map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
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
                                GeneratedMutableMap.map23298map,
                                GeneratedMutableMap.map23299map,
                                GeneratedMutableMap.map23300map,
                                GeneratedMutableMap.map23301map,
                                GeneratedMutableMap.map23302map,
                                GeneratedMutableMap.map23303map,
                                GeneratedMutableMap.map23304map,
                                GeneratedMutableMap.map23305map,
                                GeneratedMutableMap.map23306map,
                                GeneratedMutableMap.map23307map,
                                GeneratedMutableMap.map23308map,
                                GeneratedMutableMap.map23309map,
                                GeneratedMutableMap.map23310map,
                                GeneratedMutableMap.map23311map,
                                GeneratedMutableMap.map23312map,
                                GeneratedMutableMap.map23313map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23330map,
                            GeneratedMutableMap.map23331map,
                            GeneratedMutableMap.map23332map,
                            GeneratedMutableMap.map23333map,
                            GeneratedMutableMap.map23334map,
                            GeneratedMutableMap.map23335map,
                            GeneratedMutableMap.map23336map,
                            GeneratedMutableMap.map23337map,
                            GeneratedMutableMap.map23338map,
                            GeneratedMutableMap.map23339map,
                            GeneratedMutableMap.map23340map,
                            GeneratedMutableMap.map23341map,
                            GeneratedMutableMap.map23342map,
                            GeneratedMutableMap.map23343map,
                            GeneratedMutableMap.map23344map,
                            GeneratedMutableMap.map23345map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
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
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23330map,
                            GeneratedMutableMap.map23331map,
                            GeneratedMutableMap.map23332map,
                            GeneratedMutableMap.map23333map,
                            GeneratedMutableMap.map23334map,
                            GeneratedMutableMap.map23335map,
                            GeneratedMutableMap.map23336map,
                            GeneratedMutableMap.map23337map,
                            GeneratedMutableMap.map23338map,
                            GeneratedMutableMap.map23339map,
                            GeneratedMutableMap.map23340map,
                            GeneratedMutableMap.map23341map,
                            GeneratedMutableMap.map23342map,
                            GeneratedMutableMap.map23343map,
                            GeneratedMutableMap.map23344map,
                            GeneratedMutableMap.map23345map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                            AOPVariable("o"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23346map,
                            GeneratedMutableMap.map23347map,
                            GeneratedMutableMap.map23348map,
                            GeneratedMutableMap.map23349map,
                            GeneratedMutableMap.map23350map,
                            GeneratedMutableMap.map23351map,
                            GeneratedMutableMap.map23352map,
                            GeneratedMutableMap.map23353map,
                            GeneratedMutableMap.map23354map,
                            GeneratedMutableMap.map23355map,
                            GeneratedMutableMap.map23356map,
                            GeneratedMutableMap.map23357map,
                            GeneratedMutableMap.map23358map,
                            GeneratedMutableMap.map23359map,
                            GeneratedMutableMap.map23360map,
                            GeneratedMutableMap.map23361map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
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
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23346map,
                            GeneratedMutableMap.map23347map,
                            GeneratedMutableMap.map23348map,
                            GeneratedMutableMap.map23349map,
                            GeneratedMutableMap.map23350map,
                            GeneratedMutableMap.map23351map,
                            GeneratedMutableMap.map23352map,
                            GeneratedMutableMap.map23353map,
                            GeneratedMutableMap.map23354map,
                            GeneratedMutableMap.map23355map,
                            GeneratedMutableMap.map23356map,
                            GeneratedMutableMap.map23357map,
                            GeneratedMutableMap.map23358map,
                            GeneratedMutableMap.map23359map,
                            GeneratedMutableMap.map23360map,
                            GeneratedMutableMap.map23361map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
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
                                GeneratedMutableMap.map22836map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23644map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23644map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("s2"),
                        AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22843map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map22850map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("s2"),
                        AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map22850map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
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
                                GeneratedMutableMap.map22836map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23913map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23913map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("s2"),
                        AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22843map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map23914map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("s2"),
                        AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map23914map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
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
                                GeneratedMutableMap.map23298map,
                                GeneratedMutableMap.map23299map,
                                GeneratedMutableMap.map23300map,
                                GeneratedMutableMap.map23301map,
                                GeneratedMutableMap.map23302map,
                                GeneratedMutableMap.map23303map,
                                GeneratedMutableMap.map23304map,
                                GeneratedMutableMap.map23305map,
                                GeneratedMutableMap.map23306map,
                                GeneratedMutableMap.map23307map,
                                GeneratedMutableMap.map23308map,
                                GeneratedMutableMap.map23309map,
                                GeneratedMutableMap.map23310map,
                                GeneratedMutableMap.map23311map,
                                GeneratedMutableMap.map23312map,
                                GeneratedMutableMap.map23313map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23330map,
                            GeneratedMutableMap.map23331map,
                            GeneratedMutableMap.map23332map,
                            GeneratedMutableMap.map23333map,
                            GeneratedMutableMap.map23334map,
                            GeneratedMutableMap.map23335map,
                            GeneratedMutableMap.map23336map,
                            GeneratedMutableMap.map23337map,
                            GeneratedMutableMap.map23338map,
                            GeneratedMutableMap.map24085map,
                            GeneratedMutableMap.map23340map,
                            GeneratedMutableMap.map24086map,
                            GeneratedMutableMap.map24087map,
                            GeneratedMutableMap.map24088map,
                            GeneratedMutableMap.map23344map,
                            GeneratedMutableMap.map23345map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
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
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23330map,
                            GeneratedMutableMap.map23331map,
                            GeneratedMutableMap.map23332map,
                            GeneratedMutableMap.map23333map,
                            GeneratedMutableMap.map23334map,
                            GeneratedMutableMap.map23335map,
                            GeneratedMutableMap.map23336map,
                            GeneratedMutableMap.map23337map,
                            GeneratedMutableMap.map23338map,
                            GeneratedMutableMap.map24085map,
                            GeneratedMutableMap.map23340map,
                            GeneratedMutableMap.map24086map,
                            GeneratedMutableMap.map24087map,
                            GeneratedMutableMap.map24088map,
                            GeneratedMutableMap.map23344map,
                            GeneratedMutableMap.map23345map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                            AOPVariable("o"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23346map,
                            GeneratedMutableMap.map23347map,
                            GeneratedMutableMap.map23348map,
                            GeneratedMutableMap.map23349map,
                            GeneratedMutableMap.map23350map,
                            GeneratedMutableMap.map23351map,
                            GeneratedMutableMap.map23352map,
                            GeneratedMutableMap.map23353map,
                            GeneratedMutableMap.map23354map,
                            GeneratedMutableMap.map24089map,
                            GeneratedMutableMap.map23356map,
                            GeneratedMutableMap.map24090map,
                            GeneratedMutableMap.map24091map,
                            GeneratedMutableMap.map24092map,
                            GeneratedMutableMap.map23360map,
                            GeneratedMutableMap.map23361map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str1"),
                        AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
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
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23346map,
                            GeneratedMutableMap.map23347map,
                            GeneratedMutableMap.map23348map,
                            GeneratedMutableMap.map23349map,
                            GeneratedMutableMap.map23350map,
                            GeneratedMutableMap.map23351map,
                            GeneratedMutableMap.map23352map,
                            GeneratedMutableMap.map23353map,
                            GeneratedMutableMap.map23354map,
                            GeneratedMutableMap.map24089map,
                            GeneratedMutableMap.map23356map,
                            GeneratedMutableMap.map24090map,
                            GeneratedMutableMap.map24091map,
                            GeneratedMutableMap.map24092map,
                            GeneratedMutableMap.map23360map,
                            GeneratedMutableMap.map23361map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
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
                            "num",
                            "ceil"
                        ), listOf(
                            GeneratedMutableMap.map24495map,
                            GeneratedMutableMap.map24496map,
                            GeneratedMutableMap.map24497map,
                            GeneratedMutableMap.map24498map,
                            GeneratedMutableMap.map24499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
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
                            "num",
                            "ceil"
                        ), listOf(
                            GeneratedMutableMap.map24495map,
                            GeneratedMutableMap.map24496map,
                            GeneratedMutableMap.map24497map,
                            GeneratedMutableMap.map24498map,
                            GeneratedMutableMap.map24499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("ceil"),
                        AOPBuildInCallCEIL(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24501map,
                            GeneratedMutableMap.map24502map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("ceil"),
                        AOPBuildInCallCEIL(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24501map,
                            GeneratedMutableMap.map24502map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
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
                            "num",
                            "floor"
                        ), listOf(
                            GeneratedMutableMap.map24588map,
                            GeneratedMutableMap.map24589map,
                            GeneratedMutableMap.map24590map,
                            GeneratedMutableMap.map24591map,
                            GeneratedMutableMap.map24592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
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
                            "num",
                            "floor"
                        ), listOf(
                            GeneratedMutableMap.map24588map,
                            GeneratedMutableMap.map24589map,
                            GeneratedMutableMap.map24590map,
                            GeneratedMutableMap.map24591map,
                            GeneratedMutableMap.map24592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("floor"),
                        AOPBuildInCallFLOOR(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24595map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("floor"),
                        AOPBuildInCallFLOOR(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24595map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
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
                            "num",
                            "round"
                        ), listOf(
                            GeneratedMutableMap.map24679map,
                            GeneratedMutableMap.map24680map,
                            GeneratedMutableMap.map24681map,
                            GeneratedMutableMap.map24682map,
                            GeneratedMutableMap.map24683map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
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
                            "num",
                            "round"
                        ), listOf(
                            GeneratedMutableMap.map24679map,
                            GeneratedMutableMap.map24680map,
                            GeneratedMutableMap.map24681map,
                            GeneratedMutableMap.map24682map,
                            GeneratedMutableMap.map24683map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("round"),
                        AOPBuildInCallROUND(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("round")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("round"),
                        AOPBuildInCallROUND(AOPVariable("num")),
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
                            AOPVariable("num"),
                            AOPVariable("round")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
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
                                GeneratedMutableMap.map24782map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map24784map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s6>","<http://example.org/str>","str1",true,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s7>","<http://example.org/str>","str2",true,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map24784map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str"),
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map24783map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24785map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str"),
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s6>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s7>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24785map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
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
                                GeneratedMutableMap.map25399map,
                                GeneratedMutableMap.map25400map,
                                GeneratedMutableMap.map25401map,
                                GeneratedMutableMap.map25402map,
                                GeneratedMutableMap.map25403map,
                                GeneratedMutableMap.map25404map,
                                GeneratedMutableMap.map25405map,
                                GeneratedMutableMap.map25406map,
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
                                GeneratedMutableMap.map25447map
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
                            GeneratedMutableMap.map25497map,
                            GeneratedMutableMap.map25498map,
                            GeneratedMutableMap.map25499map,
                            GeneratedMutableMap.map25500map,
                            GeneratedMutableMap.map25501map,
                            GeneratedMutableMap.map25502map,
                            GeneratedMutableMap.map25503map,
                            GeneratedMutableMap.map25504map,
                            GeneratedMutableMap.map25505map,
                            GeneratedMutableMap.map25506map,
                            GeneratedMutableMap.map25507map,
                            GeneratedMutableMap.map25508map,
                            GeneratedMutableMap.map25509map,
                            GeneratedMutableMap.map25510map,
                            GeneratedMutableMap.map25511map,
                            GeneratedMutableMap.map25512map,
                            GeneratedMutableMap.map25513map,
                            GeneratedMutableMap.map25514map,
                            GeneratedMutableMap.map25515map,
                            GeneratedMutableMap.map25516map,
                            GeneratedMutableMap.map25517map,
                            GeneratedMutableMap.map25518map,
                            GeneratedMutableMap.map25519map,
                            GeneratedMutableMap.map25520map,
                            GeneratedMutableMap.map25521map,
                            GeneratedMutableMap.map25522map,
                            GeneratedMutableMap.map25523map,
                            GeneratedMutableMap.map25524map,
                            GeneratedMutableMap.map25525map,
                            GeneratedMutableMap.map25526map,
                            GeneratedMutableMap.map25527map,
                            GeneratedMutableMap.map25528map,
                            GeneratedMutableMap.map25529map,
                            GeneratedMutableMap.map25530map,
                            GeneratedMutableMap.map25531map,
                            GeneratedMutableMap.map25532map,
                            GeneratedMutableMap.map25533map,
                            GeneratedMutableMap.map25534map,
                            GeneratedMutableMap.map25535map,
                            GeneratedMutableMap.map25536map,
                            GeneratedMutableMap.map25537map,
                            GeneratedMutableMap.map25538map,
                            GeneratedMutableMap.map25539map,
                            GeneratedMutableMap.map25540map,
                            GeneratedMutableMap.map25541map,
                            GeneratedMutableMap.map25542map,
                            GeneratedMutableMap.map25543map,
                            GeneratedMutableMap.map25544map,
                            GeneratedMutableMap.map25545map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s1","<http://example.org/str>","str1",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s2","<http://example.org/str>","str2",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "str1",
                            "s2",
                            "str2",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map25497map,
                            GeneratedMutableMap.map25498map,
                            GeneratedMutableMap.map25499map,
                            GeneratedMutableMap.map25500map,
                            GeneratedMutableMap.map25501map,
                            GeneratedMutableMap.map25502map,
                            GeneratedMutableMap.map25503map,
                            GeneratedMutableMap.map25504map,
                            GeneratedMutableMap.map25505map,
                            GeneratedMutableMap.map25506map,
                            GeneratedMutableMap.map25507map,
                            GeneratedMutableMap.map25508map,
                            GeneratedMutableMap.map25509map,
                            GeneratedMutableMap.map25510map,
                            GeneratedMutableMap.map25511map,
                            GeneratedMutableMap.map25512map,
                            GeneratedMutableMap.map25513map,
                            GeneratedMutableMap.map25514map,
                            GeneratedMutableMap.map25515map,
                            GeneratedMutableMap.map25516map,
                            GeneratedMutableMap.map25517map,
                            GeneratedMutableMap.map25518map,
                            GeneratedMutableMap.map25519map,
                            GeneratedMutableMap.map25520map,
                            GeneratedMutableMap.map25521map,
                            GeneratedMutableMap.map25522map,
                            GeneratedMutableMap.map25523map,
                            GeneratedMutableMap.map25524map,
                            GeneratedMutableMap.map25525map,
                            GeneratedMutableMap.map25526map,
                            GeneratedMutableMap.map25527map,
                            GeneratedMutableMap.map25528map,
                            GeneratedMutableMap.map25529map,
                            GeneratedMutableMap.map25530map,
                            GeneratedMutableMap.map25531map,
                            GeneratedMutableMap.map25532map,
                            GeneratedMutableMap.map25533map,
                            GeneratedMutableMap.map25534map,
                            GeneratedMutableMap.map25535map,
                            GeneratedMutableMap.map25536map,
                            GeneratedMutableMap.map25537map,
                            GeneratedMutableMap.map25538map,
                            GeneratedMutableMap.map25539map,
                            GeneratedMutableMap.map25540map,
                            GeneratedMutableMap.map25541map,
                            GeneratedMutableMap.map25542map,
                            GeneratedMutableMap.map25543map,
                            GeneratedMutableMap.map25544map,
                            GeneratedMutableMap.map25545map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map25448map,
                                GeneratedMutableMap.map25449map,
                                GeneratedMutableMap.map25450map,
                                GeneratedMutableMap.map25451map,
                                GeneratedMutableMap.map25452map,
                                GeneratedMutableMap.map25453map,
                                GeneratedMutableMap.map25454map,
                                GeneratedMutableMap.map25455map,
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
                                GeneratedMutableMap.map25469map,
                                GeneratedMutableMap.map25470map,
                                GeneratedMutableMap.map25471map,
                                GeneratedMutableMap.map25472map,
                                GeneratedMutableMap.map25473map,
                                GeneratedMutableMap.map25474map,
                                GeneratedMutableMap.map25475map,
                                GeneratedMutableMap.map25476map,
                                GeneratedMutableMap.map25477map,
                                GeneratedMutableMap.map25478map,
                                GeneratedMutableMap.map25479map,
                                GeneratedMutableMap.map25480map,
                                GeneratedMutableMap.map25481map,
                                GeneratedMutableMap.map25482map,
                                GeneratedMutableMap.map25483map,
                                GeneratedMutableMap.map25484map,
                                GeneratedMutableMap.map25485map,
                                GeneratedMutableMap.map25486map,
                                GeneratedMutableMap.map25487map,
                                GeneratedMutableMap.map25488map,
                                GeneratedMutableMap.map25489map,
                                GeneratedMutableMap.map25490map,
                                GeneratedMutableMap.map25491map,
                                GeneratedMutableMap.map25492map,
                                GeneratedMutableMap.map25493map,
                                GeneratedMutableMap.map25494map,
                                GeneratedMutableMap.map25495map,
                                GeneratedMutableMap.map25496map
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
                            GeneratedMutableMap.map25546map,
                            GeneratedMutableMap.map25547map,
                            GeneratedMutableMap.map25548map,
                            GeneratedMutableMap.map25549map,
                            GeneratedMutableMap.map25550map,
                            GeneratedMutableMap.map25551map,
                            GeneratedMutableMap.map25552map,
                            GeneratedMutableMap.map25553map,
                            GeneratedMutableMap.map25554map,
                            GeneratedMutableMap.map25555map,
                            GeneratedMutableMap.map25556map,
                            GeneratedMutableMap.map25557map,
                            GeneratedMutableMap.map25558map,
                            GeneratedMutableMap.map25559map,
                            GeneratedMutableMap.map25560map,
                            GeneratedMutableMap.map25561map,
                            GeneratedMutableMap.map25562map,
                            GeneratedMutableMap.map25563map,
                            GeneratedMutableMap.map25564map,
                            GeneratedMutableMap.map25565map,
                            GeneratedMutableMap.map25566map,
                            GeneratedMutableMap.map25567map,
                            GeneratedMutableMap.map25568map,
                            GeneratedMutableMap.map25569map,
                            GeneratedMutableMap.map25570map,
                            GeneratedMutableMap.map25571map,
                            GeneratedMutableMap.map25572map,
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
                            GeneratedMutableMap.map25594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("str"),
                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s1"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s2"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("str1"),
                            AOPVariable("s2"),
                            AOPVariable("str2"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map25546map,
                            GeneratedMutableMap.map25547map,
                            GeneratedMutableMap.map25548map,
                            GeneratedMutableMap.map25549map,
                            GeneratedMutableMap.map25550map,
                            GeneratedMutableMap.map25551map,
                            GeneratedMutableMap.map25552map,
                            GeneratedMutableMap.map25553map,
                            GeneratedMutableMap.map25554map,
                            GeneratedMutableMap.map25555map,
                            GeneratedMutableMap.map25556map,
                            GeneratedMutableMap.map25557map,
                            GeneratedMutableMap.map25558map,
                            GeneratedMutableMap.map25559map,
                            GeneratedMutableMap.map25560map,
                            GeneratedMutableMap.map25561map,
                            GeneratedMutableMap.map25562map,
                            GeneratedMutableMap.map25563map,
                            GeneratedMutableMap.map25564map,
                            GeneratedMutableMap.map25565map,
                            GeneratedMutableMap.map25566map,
                            GeneratedMutableMap.map25567map,
                            GeneratedMutableMap.map25568map,
                            GeneratedMutableMap.map25569map,
                            GeneratedMutableMap.map25570map,
                            GeneratedMutableMap.map25571map,
                            GeneratedMutableMap.map25572map,
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
                            GeneratedMutableMap.map25594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
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
                            "str",
                            "len"
                        ), listOf(
                            GeneratedMutableMap.map25829map,
                            GeneratedMutableMap.map25830map,
                            GeneratedMutableMap.map25831map,
                            GeneratedMutableMap.map25832map,
                            GeneratedMutableMap.map25833map,
                            GeneratedMutableMap.map25834map,
                            GeneratedMutableMap.map25835map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
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
                            "str",
                            "len"
                        ), listOf(
                            GeneratedMutableMap.map25829map,
                            GeneratedMutableMap.map25830map,
                            GeneratedMutableMap.map25831map,
                            GeneratedMutableMap.map25832map,
                            GeneratedMutableMap.map25833map,
                            GeneratedMutableMap.map25834map,
                            GeneratedMutableMap.map25835map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("len"),
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("len")
                        ), listOf(
                            GeneratedMutableMap.map25836map,
                            GeneratedMutableMap.map25837map,
                            GeneratedMutableMap.map25838map,
                            GeneratedMutableMap.map25839map,
                            GeneratedMutableMap.map25840map,
                            GeneratedMutableMap.map25841map,
                            GeneratedMutableMap.map25842map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("len"),
                        AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("len")
                        ), listOf(
                            GeneratedMutableMap.map25836map,
                            GeneratedMutableMap.map25837map,
                            GeneratedMutableMap.map25838map,
                            GeneratedMutableMap.map25839map,
                            GeneratedMutableMap.map25840map,
                            GeneratedMutableMap.map25841map,
                            GeneratedMutableMap.map25842map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
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
                            "str",
                            "ustr"
                        ), listOf(
                            GeneratedMutableMap.map25949map,
                            GeneratedMutableMap.map25950map,
                            GeneratedMutableMap.map25951map,
                            GeneratedMutableMap.map25952map,
                            GeneratedMutableMap.map25953map,
                            GeneratedMutableMap.map25954map,
                            GeneratedMutableMap.map25955map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
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
                            "str",
                            "ustr"
                        ), listOf(
                            GeneratedMutableMap.map25949map,
                            GeneratedMutableMap.map25950map,
                            GeneratedMutableMap.map25951map,
                            GeneratedMutableMap.map25952map,
                            GeneratedMutableMap.map25953map,
                            GeneratedMutableMap.map25954map,
                            GeneratedMutableMap.map25955map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("ustr"),
                        AOPBuildInCallUCASE(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("ustr")
                        ), listOf(
                            GeneratedMutableMap.map25956map,
                            GeneratedMutableMap.map25957map,
                            GeneratedMutableMap.map25958map,
                            GeneratedMutableMap.map25959map,
                            GeneratedMutableMap.map25960map,
                            GeneratedMutableMap.map25961map,
                            GeneratedMutableMap.map25962map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("ustr"),
                        AOPBuildInCallUCASE(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("ustr")
                        ), listOf(
                            GeneratedMutableMap.map25956map,
                            GeneratedMutableMap.map25957map,
                            GeneratedMutableMap.map25958map,
                            GeneratedMutableMap.map25959map,
                            GeneratedMutableMap.map25960map,
                            GeneratedMutableMap.map25961map,
                            GeneratedMutableMap.map25962map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
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
                            "str",
                            "lstr"
                        ), listOf(
                            GeneratedMutableMap.map26065map,
                            GeneratedMutableMap.map26066map,
                            GeneratedMutableMap.map26067map,
                            GeneratedMutableMap.map26068map,
                            GeneratedMutableMap.map26069map,
                            GeneratedMutableMap.map26070map,
                            GeneratedMutableMap.map26071map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
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
                            "str",
                            "lstr"
                        ), listOf(
                            GeneratedMutableMap.map26065map,
                            GeneratedMutableMap.map26066map,
                            GeneratedMutableMap.map26067map,
                            GeneratedMutableMap.map26068map,
                            GeneratedMutableMap.map26069map,
                            GeneratedMutableMap.map26070map,
                            GeneratedMutableMap.map26071map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("lstr"),
                        AOPBuildInCallLCASE(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("lstr")
                        ), listOf(
                            GeneratedMutableMap.map26072map,
                            GeneratedMutableMap.map26073map,
                            GeneratedMutableMap.map26074map,
                            GeneratedMutableMap.map25959map,
                            GeneratedMutableMap.map25960map,
                            GeneratedMutableMap.map26075map,
                            GeneratedMutableMap.map26076map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("lstr"),
                        AOPBuildInCallLCASE(AOPVariable("str")),
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
                            AOPVariable("str"),
                            AOPVariable("lstr")
                        ), listOf(
                            GeneratedMutableMap.map26072map,
                            GeneratedMutableMap.map26073map,
                            GeneratedMutableMap.map26074map,
                            GeneratedMutableMap.map25959map,
                            GeneratedMutableMap.map25960map,
                            GeneratedMutableMap.map26075map,
                            GeneratedMutableMap.map26076map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
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
                                GeneratedMutableMap.map27320map,
                                GeneratedMutableMap.map27321map,
                                GeneratedMutableMap.map27322map,
                                GeneratedMutableMap.map27323map,
                                GeneratedMutableMap.map27324map,
                                GeneratedMutableMap.map27325map,
                                GeneratedMutableMap.map27326map,
                                GeneratedMutableMap.map27327map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map27328map,
                                GeneratedMutableMap.map27329map,
                                GeneratedMutableMap.map27330map,
                                GeneratedMutableMap.map27331map,
                                GeneratedMutableMap.map27332map,
                                GeneratedMutableMap.map27333map,
                                GeneratedMutableMap.map27334map,
                                GeneratedMutableMap.map27335map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("sum"),
                        AOPAddition(AOPVariable("y"), AOPVariable("x")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
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
                                GeneratedMutableMap.map27320map,
                                GeneratedMutableMap.map27321map,
                                GeneratedMutableMap.map27322map,
                                GeneratedMutableMap.map27323map,
                                GeneratedMutableMap.map27324map,
                                GeneratedMutableMap.map27325map,
                                GeneratedMutableMap.map27326map,
                                GeneratedMutableMap.map27327map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","x",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map27328map,
                                GeneratedMutableMap.map27329map,
                                GeneratedMutableMap.map27330map,
                                GeneratedMutableMap.map27331map,
                                GeneratedMutableMap.map27332map,
                                GeneratedMutableMap.map27333map,
                                GeneratedMutableMap.map27334map,
                                GeneratedMutableMap.map27335map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("sum"),
                        AOPAddition(AOPBuildInCallSTR(AOPVariable("y")), AOPBuildInCallSTR(AOPVariable("x"))),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/p>","\"a\""))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/p>","_:b"))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/p>","<http://example/a>"))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/p>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example/x1>","<http://example/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x2>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x3>","<http://example/q>","\"1\""))
                                                        graph.addData(1L,listOf("<http://example/x4>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x5>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example/x6>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x7>","<http://example/q>","\"2\""))
                                                        graph.addData(1L,listOf("<http://example/x8>","<http://example/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
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
                                GeneratedMutableMap.map27728map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27730map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27730map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallMD5(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27729map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27731map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallMD5(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27731map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
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
                                GeneratedMutableMap.map27802map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27804map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s4>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27804map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallMD5(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27803map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27805map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallMD5(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s4>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27805map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
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
                                GeneratedMutableMap.map27728map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27876map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27876map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA1(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27729map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27877map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA1(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27877map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
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
                                GeneratedMutableMap.map27948map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27950map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27950map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA1(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27949map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27951map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA1(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s8>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27951map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
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
                                GeneratedMutableMap.map27728map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28022map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28022map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA256(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27729map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28023map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA256(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28023map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
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
                                GeneratedMutableMap.map27948map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28094map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "l",
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28094map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA256(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map27949map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28095map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("hash"),
                        AOPBuildInCallSHA256(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s8>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("l"),
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28095map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28271map,
                            GeneratedMutableMap.map28272map,
                            GeneratedMutableMap.map28273map,
                            GeneratedMutableMap.map28274map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28271map,
                            GeneratedMutableMap.map28272map,
                            GeneratedMutableMap.map28273map,
                            GeneratedMutableMap.map28274map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallMINUTES(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28275map,
                            GeneratedMutableMap.map28276map,
                            GeneratedMutableMap.map28277map,
                            GeneratedMutableMap.map28278map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallMINUTES(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28275map,
                            GeneratedMutableMap.map28276map,
                            GeneratedMutableMap.map28277map,
                            GeneratedMutableMap.map28278map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28361map,
                            GeneratedMutableMap.map28362map,
                            GeneratedMutableMap.map28363map,
                            GeneratedMutableMap.map28364map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28361map,
                            GeneratedMutableMap.map28362map,
                            GeneratedMutableMap.map28363map,
                            GeneratedMutableMap.map28364map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallSECONDS(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28365map,
                            GeneratedMutableMap.map28366map,
                            GeneratedMutableMap.map28367map,
                            GeneratedMutableMap.map28368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallSECONDS(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28365map,
                            GeneratedMutableMap.map28366map,
                            GeneratedMutableMap.map28367map,
                            GeneratedMutableMap.map28368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28488map,
                            GeneratedMutableMap.map28489map,
                            GeneratedMutableMap.map28490map,
                            GeneratedMutableMap.map28491map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28488map,
                            GeneratedMutableMap.map28489map,
                            GeneratedMutableMap.map28490map,
                            GeneratedMutableMap.map28491map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallHOURS(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28492map,
                            GeneratedMutableMap.map28493map,
                            GeneratedMutableMap.map28494map,
                            GeneratedMutableMap.map28495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallHOURS(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28492map,
                            GeneratedMutableMap.map28493map,
                            GeneratedMutableMap.map28494map,
                            GeneratedMutableMap.map28495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28615map,
                            GeneratedMutableMap.map28616map,
                            GeneratedMutableMap.map28617map,
                            GeneratedMutableMap.map28274map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28615map,
                            GeneratedMutableMap.map28616map,
                            GeneratedMutableMap.map28617map,
                            GeneratedMutableMap.map28274map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallMONTH(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28618map,
                            GeneratedMutableMap.map28619map,
                            GeneratedMutableMap.map28620map,
                            GeneratedMutableMap.map28278map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallMONTH(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28618map,
                            GeneratedMutableMap.map28619map,
                            GeneratedMutableMap.map28620map,
                            GeneratedMutableMap.map28278map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28738map,
                            GeneratedMutableMap.map28739map,
                            GeneratedMutableMap.map28740map,
                            GeneratedMutableMap.map28741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28738map,
                            GeneratedMutableMap.map28739map,
                            GeneratedMutableMap.map28740map,
                            GeneratedMutableMap.map28741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallYEAR(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28742map,
                            GeneratedMutableMap.map28743map,
                            GeneratedMutableMap.map28744map,
                            GeneratedMutableMap.map28745map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallYEAR(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28742map,
                            GeneratedMutableMap.map28743map,
                            GeneratedMutableMap.map28744map,
                            GeneratedMutableMap.map28745map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28865map,
                            GeneratedMutableMap.map28866map,
                            GeneratedMutableMap.map28867map,
                            GeneratedMutableMap.map28491map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28865map,
                            GeneratedMutableMap.map28866map,
                            GeneratedMutableMap.map28867map,
                            GeneratedMutableMap.map28491map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallDAY(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28868map,
                            GeneratedMutableMap.map28869map,
                            GeneratedMutableMap.map28870map,
                            GeneratedMutableMap.map28495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallDAY(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28868map,
                            GeneratedMutableMap.map28869map,
                            GeneratedMutableMap.map28870map,
                            GeneratedMutableMap.map28495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28951map,
                            GeneratedMutableMap.map28952map,
                            GeneratedMutableMap.map28953map,
                            GeneratedMutableMap.map28954map
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
                                GeneratedMutableMap.map28263map,
                                GeneratedMutableMap.map28264map,
                                GeneratedMutableMap.map28265map,
                                GeneratedMutableMap.map28266map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29070map,
                            GeneratedMutableMap.map29071map,
                            GeneratedMutableMap.map29072map,
                            GeneratedMutableMap.map28954map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "date",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29070map,
                            GeneratedMutableMap.map29071map,
                            GeneratedMutableMap.map29072map,
                            GeneratedMutableMap.map28954map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallTZ(AOPVariable("date")),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                            ), listOf(
                                GeneratedMutableMap.map28267map,
                                GeneratedMutableMap.map28268map,
                                GeneratedMutableMap.map28269map,
                                GeneratedMutableMap.map28270map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map29073map,
                            GeneratedMutableMap.map29074map,
                            GeneratedMutableMap.map29075map,
                            GeneratedMutableMap.map29076map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("x"),
                        AOPBuildInCallTZ(AOPVariable("date")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("date"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map29073map,
                            GeneratedMutableMap.map29074map,
                            GeneratedMutableMap.map29075map,
                            GeneratedMutableMap.map29076map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
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
                                GeneratedMutableMap.map31137map,
                                GeneratedMutableMap.map31139map,
                                GeneratedMutableMap.map31151map,
                                GeneratedMutableMap.map31153map
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
                            GeneratedMutableMap.map31235map,
                            GeneratedMutableMap.map31236map,
                            GeneratedMutableMap.map31237map,
                            GeneratedMutableMap.map31238map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                                                        false                                                                    )

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
                            GeneratedMutableMap.map31235map,
                            GeneratedMutableMap.map31236map,
                            GeneratedMutableMap.map31237map,
                            GeneratedMutableMap.map31238map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map31186map,
                                GeneratedMutableMap.map31188map,
                                GeneratedMutableMap.map31200map,
                                GeneratedMutableMap.map31202map
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
                            GeneratedMutableMap.map31239map,
                            GeneratedMutableMap.map31240map,
                            GeneratedMutableMap.map31241map,
                            GeneratedMutableMap.map31242map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )

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
                            GeneratedMutableMap.map31239map,
                            GeneratedMutableMap.map31240map,
                            GeneratedMutableMap.map31241map,
                            GeneratedMutableMap.map31242map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                GeneratedMutableMap.map31235map,
                                GeneratedMutableMap.map31236map,
                                GeneratedMutableMap.map31237map,
                                GeneratedMutableMap.map31238map
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
                            GeneratedMutableMap.map31243map,
                            GeneratedMutableMap.map31244map,
                            GeneratedMutableMap.map31245map,
                            GeneratedMutableMap.map31246map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                                                                        false                                                                                    )

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
                            GeneratedMutableMap.map31243map,
                            GeneratedMutableMap.map31244map,
                            GeneratedMutableMap.map31245map,
                            GeneratedMutableMap.map31246map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map31239map,
                                GeneratedMutableMap.map31240map,
                                GeneratedMutableMap.map31241map,
                                GeneratedMutableMap.map31242map
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
                            GeneratedMutableMap.map31247map,
                            GeneratedMutableMap.map31248map,
                            GeneratedMutableMap.map31249map,
                            GeneratedMutableMap.map31250map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"bar\"@en"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"BAZ\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"100%\""))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                                                                                    }()
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
                                                                                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                                                                                    }()
,
                                                                                        false                                                                                    )

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
                            GeneratedMutableMap.map31247map,
                            GeneratedMutableMap.map31248map,
                            GeneratedMutableMap.map31249map,
                            GeneratedMutableMap.map31250map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
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
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31334map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
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
                                GeneratedMutableMap.map31334map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2",
                            "b1"
                        ), listOf(
                            GeneratedMutableMap.map31335map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
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
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31337map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
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
                                GeneratedMutableMap.map31337map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b2",
                            "b1"
                        ), listOf(
                            GeneratedMutableMap.map31338map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("n"),
                        AOPDateTime("\"2020-02-26T13:11:24Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPBind(
                        dictionary,
                        AOPVariable("iri"),
                        AOPBuildInCallIRI(AOPSimpleLiteral("\"", "iri"), "http://example.org/"),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "iri"
                        ), listOf(
                            GeneratedMutableMap.map31701map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */ ,
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
                                GeneratedMutableMap.map31701map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "iri",
                            "uri"
                        ), listOf(
                            GeneratedMutableMap.map31702map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */ ,
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
                                GeneratedMutableMap.map31914map,
                                GeneratedMutableMap.map31915map,
                                GeneratedMutableMap.map31916map,
                                GeneratedMutableMap.map31917map,
                                GeneratedMutableMap.map31918map,
                                GeneratedMutableMap.map31919map,
                                GeneratedMutableMap.map31920map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "integer"
                        ), listOf(
                            GeneratedMutableMap.map31928map,
                            GeneratedMutableMap.map31929map,
                            GeneratedMutableMap.map31930map,
                            GeneratedMutableMap.map31931map,
                            GeneratedMutableMap.map31932map,
                            GeneratedMutableMap.map31933map,
                            GeneratedMutableMap.map31934map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "integer"
                        ), listOf(
                            GeneratedMutableMap.map31928map,
                            GeneratedMutableMap.map31929map,
                            GeneratedMutableMap.map31930map,
                            GeneratedMutableMap.map31931map,
                            GeneratedMutableMap.map31932map,
                            GeneratedMutableMap.map31933map,
                            GeneratedMutableMap.map31934map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map31921map,
                                GeneratedMutableMap.map31922map,
                                GeneratedMutableMap.map31923map,
                                GeneratedMutableMap.map31924map,
                                GeneratedMutableMap.map31925map,
                                GeneratedMutableMap.map31926map,
                                GeneratedMutableMap.map31927map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ), listOf(
                            GeneratedMutableMap.map31935map,
                            GeneratedMutableMap.map31936map,
                            GeneratedMutableMap.map31937map,
                            GeneratedMutableMap.map31938map,
                            GeneratedMutableMap.map31939map,
                            GeneratedMutableMap.map31940map,
                            GeneratedMutableMap.map31941map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("integer"),
                        AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"123\""))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/str>","\"日本語\"@ja"))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/str>","\"english\"@en"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"français\"@fr"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ), listOf(
                            GeneratedMutableMap.map31935map,
                            GeneratedMutableMap.map31936map,
                            GeneratedMutableMap.map31937map,
                            GeneratedMutableMap.map31938map,
                            GeneratedMutableMap.map31939map,
                            GeneratedMutableMap.map31940map,
                            GeneratedMutableMap.map31941map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
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
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "error"
                        ), listOf(
                            GeneratedMutableMap.map32017map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if02.rq */ ,
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
                                GeneratedMutableMap.map35135map,
                                GeneratedMutableMap.map35136map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "eq"
                        ), listOf(
                            GeneratedMutableMap.map35140map,
                            GeneratedMutableMap.map35141map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "eq"
                        ), listOf(
                            GeneratedMutableMap.map35140map,
                            GeneratedMutableMap.map35141map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map35138map,
                                GeneratedMutableMap.map35139map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ), listOf(
                            GeneratedMutableMap.map35142map,
                            GeneratedMutableMap.map35143map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("eq"),
                        AOPEQ(AOPVariable("y"), AOPVariable("z")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ), listOf(
                            GeneratedMutableMap.map35142map,
                            GeneratedMutableMap.map35143map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
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
                                GeneratedMutableMap.map35135map,
                                GeneratedMutableMap.map35259map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35262map,
                            GeneratedMutableMap.map35263map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35262map,
                            GeneratedMutableMap.map35263map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map35138map,
                                GeneratedMutableMap.map35261map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35264map,
                            GeneratedMutableMap.map35265map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("sum"),
                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35264map,
                            GeneratedMutableMap.map35265map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
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
                                GeneratedMutableMap.map35136map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map35366map
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
                                GeneratedMutableMap.map35366map
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
                            GeneratedMutableMap.map35367map
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
                                GeneratedMutableMap.map35131map,
                                GeneratedMutableMap.map35479map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35480map,
                            GeneratedMutableMap.map35481map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35480map,
                            GeneratedMutableMap.map35481map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("sum"),
                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map35132map,
                                GeneratedMutableMap.map35137map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35139map,
                            GeneratedMutableMap.map35482map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("sum"),
                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35139map,
                            GeneratedMutableMap.map35482map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
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
                                GeneratedMutableMap.map35582map,
                                GeneratedMutableMap.map35583map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35585map,
                            GeneratedMutableMap.map35586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35585map,
                            GeneratedMutableMap.map35586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("dt"),
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map35132map,
                                GeneratedMutableMap.map35584map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35587map,
                            GeneratedMutableMap.map35588map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("dt"),
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35587map,
                            GeneratedMutableMap.map35588map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
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
                                GeneratedMutableMap.map35582map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35668map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
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
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35668map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("dt"),
                        AOPBuildInCallDATATYPE(AOPVariable("m")),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map35132map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35669map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("dt"),
                        AOPBuildInCallDATATYPE(AOPVariable("m")),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35669map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
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
                                GeneratedMutableMap.map35762map,
                                GeneratedMutableMap.map35763map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35765map,
                            GeneratedMutableMap.map35766map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","l",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        true                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35765map,
                            GeneratedMutableMap.map35766map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map35139map,
                                GeneratedMutableMap.map35764map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35767map,
                            GeneratedMutableMap.map35768map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("dt"),
                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("l"),graphName,false)                                                    }()
,
                                        true                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35767map,
                            GeneratedMutableMap.map35768map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
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
                                GeneratedMutableMap.map37491map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L",
                            "FullName"
                        ), listOf(
                            GeneratedMutableMap.map37493map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/firstName>","F",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/lastName>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L",
                            "FullName"
                        ), listOf(
                            GeneratedMutableMap.map37493map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                GeneratedMutableMap.map37492map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L"),
                            AOPVariable("FullName")
                        ), listOf(
                            GeneratedMutableMap.map37494map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("FullName"),
                        AOPBuildInCallCONCAT(AOPBuildInCallCONCAT(AOPVariable("F"), AOPSimpleLiteral("\"", " ")), AOPVariable("L")),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L"),
                            AOPVariable("FullName")
                        ), listOf(
                            GeneratedMutableMap.map37494map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
                                GeneratedMutableMap.map37497map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "s",
                            "p"
                        ), listOf(
                            GeneratedMutableMap.map37499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/firstName>","F",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/lastName>","L",false,true,false,EIndexPattern.SPO)
                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "FullName",
                            "s",
                            "p"
                        ), listOf(
                            GeneratedMutableMap.map37499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPBind(
                        AOPVariable("p"),
                        AOPIri("http://xmlns.com/foaf/0.1/name"),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s")
                            ), listOf(
                                GeneratedMutableMap.map37498map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("FullName"),
                            AOPVariable("s"),
                            AOPVariable("p")
                        ), listOf(
                            GeneratedMutableMap.map37500map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                                                                                                    }()
,
                                                                                                    {
                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("FullName"),
                            AOPVariable("s"),
                            AOPVariable("p")
                        ), listOf(
                            GeneratedMutableMap.map37500map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
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
