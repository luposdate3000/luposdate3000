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


class GeneratedPOPJoinHashMapTest {
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
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "O1"
                            ), listOf(
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O2"
                            ), listOf(
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map
                            )
                        ),
                        false                    ),
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1")
                            ), listOf(
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O2")
                            ), listOf(
                                GeneratedMutableMap.map2752map,
                                GeneratedMutableMap.map2753map,
                                GeneratedMutableMap.map2754map
                            )
                        ),
                        false                    ),
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/p>"),AOPVariable("O1"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/q>"),AOPVariable("O2"),graphName,false)                                    }()
,
                        false                    ),
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
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5906map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5906map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5906map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5906map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode2.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5906map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6339map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6339map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6339map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6339map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6339map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6339map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "S",
                            "P",
                            "O"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7199map,
                                GeneratedMutableMap.map7200map,
                                GeneratedMutableMap.map7201map,
                                GeneratedMutableMap.map7202map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map,
                            GeneratedMutableMap.map7211map,
                            GeneratedMutableMap.map7212map,
                            GeneratedMutableMap.map7213map,
                            GeneratedMutableMap.map7214map,
                            GeneratedMutableMap.map7215map,
                            GeneratedMutableMap.map7216map,
                            GeneratedMutableMap.map7217map,
                            GeneratedMutableMap.map7218map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/bind/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7733map,
                                GeneratedMutableMap.map7733map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7300map,
                            GeneratedMutableMap.map7301map,
                            GeneratedMutableMap.map7302map,
                            GeneratedMutableMap.map7303map,
                            GeneratedMutableMap.map7300map,
                            GeneratedMutableMap.map7301map,
                            GeneratedMutableMap.map7302map,
                            GeneratedMutableMap.map7303map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map8515map,
                                GeneratedMutableMap.map8516map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8511map,
                                GeneratedMutableMap.map8512map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8519map,
                            GeneratedMutableMap.map8520map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8519map,
                            GeneratedMutableMap.map8520map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title")
                            ), listOf(
                                GeneratedMutableMap.map8517map,
                                GeneratedMutableMap.map8518map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("price")
                            ), listOf(
                                GeneratedMutableMap.map8513map,
                                GeneratedMutableMap.map8514map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ), listOf(
                            GeneratedMutableMap.map8521map,
                            GeneratedMutableMap.map8522map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://purl.org/dc/elements/1.1/title>"),AOPVariable("title"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://example.org/ns#price>"),AOPVariable("price"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ), listOf(
                            GeneratedMutableMap.map8521map,
                            GeneratedMutableMap.map8522map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book"
                            ), listOf(
                                GeneratedMutableMap.map8523map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8519map,
                                GeneratedMutableMap.map8520map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8519map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8578map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8675map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map,
                                GeneratedMutableMap.map8679map,
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map,
                                GeneratedMutableMap.map8685map,
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8689map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                            ), listOf(
                                GeneratedMutableMap.map8690map,
                                GeneratedMutableMap.map8691map,
                                GeneratedMutableMap.map8692map,
                                GeneratedMutableMap.map8693map,
                                GeneratedMutableMap.map8694map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8695map,
                                GeneratedMutableMap.map8696map,
                                GeneratedMutableMap.map8697map,
                                GeneratedMutableMap.map8698map,
                                GeneratedMutableMap.map8699map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "p2",
                            "o2"
                        ), listOf(
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
                            GeneratedMutableMap.map8711map,
                            GeneratedMutableMap.map8712map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p1","o1",false,false,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p2","o2",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "p2",
                            "o2"
                        ), listOf(
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
                            GeneratedMutableMap.map8711map,
                            GeneratedMutableMap.map8712map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
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
                            GeneratedMutableMap.map8724map,
                            GeneratedMutableMap.map8725map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
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
                            GeneratedMutableMap.map8724map,
                            GeneratedMutableMap.map8725map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8814map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map,
                                GeneratedMutableMap.map8679map,
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map,
                                GeneratedMutableMap.map8685map,
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8815map,
                            GeneratedMutableMap.map8689map,
                            GeneratedMutableMap.map8816map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8901map,
                                GeneratedMutableMap.map8902map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map,
                                GeneratedMutableMap.map8679map,
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map,
                                GeneratedMutableMap.map8685map,
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8903map,
                            GeneratedMutableMap.map8904map,
                            GeneratedMutableMap.map8905map,
                            GeneratedMutableMap.map8905map,
                            GeneratedMutableMap.map8906map,
                            GeneratedMutableMap.map8816map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map8966map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                            ), listOf(
                                GeneratedMutableMap.map8690map,
                                GeneratedMutableMap.map8691map,
                                GeneratedMutableMap.map8692map,
                                GeneratedMutableMap.map8693map,
                                GeneratedMutableMap.map8694map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map8967map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map9048map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map,
                                GeneratedMutableMap.map9049map,
                                GeneratedMutableMap.map9050map,
                                GeneratedMutableMap.map9051map,
                                GeneratedMutableMap.map9052map,
                                GeneratedMutableMap.map9053map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o2",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map9054map,
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map9056map,
                            GeneratedMutableMap.map9057map,
                            GeneratedMutableMap.map9058map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                            ), listOf(
                                GeneratedMutableMap.map8690map,
                                GeneratedMutableMap.map8691map,
                                GeneratedMutableMap.map8692map,
                                GeneratedMutableMap.map9062map,
                                GeneratedMutableMap.map8693map,
                                GeneratedMutableMap.map8694map,
                                GeneratedMutableMap.map9063map,
                                GeneratedMutableMap.map9064map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map9059map,
                                GeneratedMutableMap.map9060map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9068map,
                            GeneratedMutableMap.map9069map,
                            GeneratedMutableMap.map9070map,
                            GeneratedMutableMap.map9071map,
                            GeneratedMutableMap.map9072map,
                            GeneratedMutableMap.map9073map,
                            GeneratedMutableMap.map9074map,
                            GeneratedMutableMap.map9075map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"alice@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Alice\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p1","o1",false,false,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","o2",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9068map,
                            GeneratedMutableMap.map9069map,
                            GeneratedMutableMap.map9070map,
                            GeneratedMutableMap.map9071map,
                            GeneratedMutableMap.map9072map,
                            GeneratedMutableMap.map9073map,
                            GeneratedMutableMap.map9074map,
                            GeneratedMutableMap.map9075map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map,
                                GeneratedMutableMap.map9066map,
                                GeneratedMutableMap.map9067map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8595map,
                                GeneratedMutableMap.map9061map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map9076map,
                            GeneratedMutableMap.map9077map,
                            GeneratedMutableMap.map9078map,
                            GeneratedMutableMap.map9079map,
                            GeneratedMutableMap.map9080map,
                            GeneratedMutableMap.map9081map,
                            GeneratedMutableMap.map9082map,
                            GeneratedMutableMap.map9083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"alice@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Alice\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("o2"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map9076map,
                            GeneratedMutableMap.map9077map,
                            GeneratedMutableMap.map9078map,
                            GeneratedMutableMap.map9079map,
                            GeneratedMutableMap.map9080map,
                            GeneratedMutableMap.map9081map,
                            GeneratedMutableMap.map9082map,
                            GeneratedMutableMap.map9083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map9191map,
                                GeneratedMutableMap.map9192map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8519map,
                                GeneratedMutableMap.map8520map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8519map,
                            GeneratedMutableMap.map8520map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book"
                            ), listOf(
                                GeneratedMutableMap.map8523map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map8515map,
                                GeneratedMutableMap.map8516map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title"
                        ), listOf(
                            GeneratedMutableMap.map8515map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map8515map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8511map,
                                GeneratedMutableMap.map8512map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8519map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10298map,
                                GeneratedMutableMap.map10299map,
                                GeneratedMutableMap.map10300map,
                                GeneratedMutableMap.map10301map,
                                GeneratedMutableMap.map10302map,
                                GeneratedMutableMap.map10303map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10554map,
                                GeneratedMutableMap.map10555map,
                                GeneratedMutableMap.map10556map,
                                GeneratedMutableMap.map10557map,
                                GeneratedMutableMap.map10558map,
                                GeneratedMutableMap.map10559map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10560map,
                            GeneratedMutableMap.map10561map,
                            GeneratedMutableMap.map10562map,
                            GeneratedMutableMap.map10563map,
                            GeneratedMutableMap.map10564map,
                            GeneratedMutableMap.map10565map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10298map,
                                GeneratedMutableMap.map10299map,
                                GeneratedMutableMap.map10300map,
                                GeneratedMutableMap.map10301map,
                                GeneratedMutableMap.map10302map,
                                GeneratedMutableMap.map10303map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10554map,
                                GeneratedMutableMap.map10555map,
                                GeneratedMutableMap.map10556map,
                                GeneratedMutableMap.map10557map,
                                GeneratedMutableMap.map10558map,
                                GeneratedMutableMap.map10559map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10560map,
                            GeneratedMutableMap.map10561map,
                            GeneratedMutableMap.map10562map,
                            GeneratedMutableMap.map10563map,
                            GeneratedMutableMap.map10564map,
                            GeneratedMutableMap.map10565map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10560map,
                            GeneratedMutableMap.map10561map,
                            GeneratedMutableMap.map10562map,
                            GeneratedMutableMap.map10563map,
                            GeneratedMutableMap.map10564map,
                            GeneratedMutableMap.map10565map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10380map,
                                GeneratedMutableMap.map10381map,
                                GeneratedMutableMap.map10382map,
                                GeneratedMutableMap.map10383map,
                                GeneratedMutableMap.map10384map,
                                GeneratedMutableMap.map10385map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10380map,
                                GeneratedMutableMap.map10381map,
                                GeneratedMutableMap.map10382map,
                                GeneratedMutableMap.map10383map,
                                GeneratedMutableMap.map10384map,
                                GeneratedMutableMap.map10385map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10690map,
                            GeneratedMutableMap.map10691map,
                            GeneratedMutableMap.map10692map,
                            GeneratedMutableMap.map10693map,
                            GeneratedMutableMap.map10694map,
                            GeneratedMutableMap.map10695map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10690map,
                            GeneratedMutableMap.map10691map,
                            GeneratedMutableMap.map10692map,
                            GeneratedMutableMap.map10693map,
                            GeneratedMutableMap.map10694map,
                            GeneratedMutableMap.map10695map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5906map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map,
                                GeneratedMutableMap.map11642map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map,
                            GeneratedMutableMap.map11642map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a"
                            ), listOf(
                                GeneratedMutableMap.map11802map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11530map,
                                GeneratedMutableMap.map11531map,
                                GeneratedMutableMap.map11532map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11530map,
                            GeneratedMutableMap.map11531map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","b",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11530map,
                            GeneratedMutableMap.map11531map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("a")
                            ), listOf(
                                GeneratedMutableMap.map11803map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                            ), listOf(
                                GeneratedMutableMap.map8595map,
                                GeneratedMutableMap.map11533map,
                                GeneratedMutableMap.map9061map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("b")
                        ), listOf(
                            GeneratedMutableMap.map8595map,
                            GeneratedMutableMap.map11533map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Alan\""),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("b"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("b")
                        ), listOf(
                            GeneratedMutableMap.map8595map,
                            GeneratedMutableMap.map11533map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a"
                            ), listOf(
                                GeneratedMutableMap.map11802map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map12036map,
                                GeneratedMutableMap.map12037map,
                                GeneratedMutableMap.map12038map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map12036map,
                            GeneratedMutableMap.map12037map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map12036map,
                            GeneratedMutableMap.map12037map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("a")
                            ), listOf(
                                GeneratedMutableMap.map11803map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map8595map,
                                GeneratedMutableMap.map11533map,
                                GeneratedMutableMap.map9061map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map8595map,
                            GeneratedMutableMap.map11533map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Alan\""),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("Var_B"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map8595map,
                            GeneratedMutableMap.map11533map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a"
                            ), listOf(
                                GeneratedMutableMap.map11802map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map12038map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("a")
                            ), listOf(
                                GeneratedMutableMap.map11803map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map9061map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Alan\""),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("Var_B"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map13186map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map8586map,
                                GeneratedMutableMap.map8587map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                                GeneratedMutableMap.map13187map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8593map,
                            GeneratedMutableMap.map8594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/a>"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("s"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8593map,
                            GeneratedMutableMap.map8594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map8586map,
                                GeneratedMutableMap.map8587map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable.calculate("<http://example.org/c>"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-04.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map13587map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map11282map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map,
                                GeneratedMutableMap.map11283map,
                                GeneratedMutableMap.map11284map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11283map,
                            GeneratedMutableMap.map11284map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/name>","\"Chris\"",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11283map,
                            GeneratedMutableMap.map11284map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                                GeneratedMutableMap.map13588map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map,
                                GeneratedMutableMap.map11285map,
                                GeneratedMutableMap.map11286map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map11285map,
                            GeneratedMutableMap.map11286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Chris\""),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map11285map,
                            GeneratedMutableMap.map11286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map13186map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map11282map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map,
                                GeneratedMutableMap.map11283map,
                                GeneratedMutableMap.map11284map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11282map,
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11282map,
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                                GeneratedMutableMap.map13187map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map,
                                GeneratedMutableMap.map11285map,
                                GeneratedMutableMap.map11286map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9065map,
                            GeneratedMutableMap.map8593map,
                            GeneratedMutableMap.map8594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/a>"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("s"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9065map,
                            GeneratedMutableMap.map8593map,
                            GeneratedMutableMap.map8594map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map11287map,
                                GeneratedMutableMap.map11283map,
                                GeneratedMutableMap.map11284map,
                                GeneratedMutableMap.map11288map,
                                GeneratedMutableMap.map11289map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>"))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/mbox>","\"dan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/name>","\"Dan\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map11290map,
                                GeneratedMutableMap.map11285map,
                                GeneratedMutableMap.map11286map,
                                GeneratedMutableMap.map11291map,
                                GeneratedMutableMap.map11292map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/a>"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("s"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>"))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/mbox>","\"dan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/name>","\"Dan\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-02.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map11282map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map,
                                GeneratedMutableMap.map11283map,
                                GeneratedMutableMap.map11284map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map9065map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map,
                                GeneratedMutableMap.map11285map,
                                GeneratedMutableMap.map11286map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable.calculate("<http://example.org/d>"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","\"chris@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-03.ru */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map13587map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map11287map,
                                GeneratedMutableMap.map11283map,
                                GeneratedMutableMap.map11284map,
                                GeneratedMutableMap.map11288map,
                                GeneratedMutableMap.map11289map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11287map,
                            GeneratedMutableMap.map11283map,
                            GeneratedMutableMap.map11284map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map14338map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map8586map,
                                GeneratedMutableMap.map8587map,
                                GeneratedMutableMap.map8588map,
                                GeneratedMutableMap.map8589map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map8586map,
                            GeneratedMutableMap.map8587map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map8586map,
                            GeneratedMutableMap.map8587map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s")
                            ), listOf(
                                GeneratedMutableMap.map11803map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8590map,
                                GeneratedMutableMap.map8591map,
                                GeneratedMutableMap.map8592map,
                                GeneratedMutableMap.map8593map,
                                GeneratedMutableMap.map8594map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8590map,
                            GeneratedMutableMap.map8591map,
                            GeneratedMutableMap.map8592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable.calculate("<http://example.org/b>"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8590map,
                            GeneratedMutableMap.map8591map,
                            GeneratedMutableMap.map8592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16171map,
                                GeneratedMutableMap.map16172map,
                                GeneratedMutableMap.map16173map,
                                GeneratedMutableMap.map16174map,
                                GeneratedMutableMap.map16175map,
                                GeneratedMutableMap.map16176map,
                                GeneratedMutableMap.map16177map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16170map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16174map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16439map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16439map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16439map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16633map,
                                GeneratedMutableMap.map16634map,
                                GeneratedMutableMap.map16635map,
                                GeneratedMutableMap.map16636map,
                                GeneratedMutableMap.map16171map,
                                GeneratedMutableMap.map16172map,
                                GeneratedMutableMap.map16173map,
                                GeneratedMutableMap.map16637map,
                                GeneratedMutableMap.map16638map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/d>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:x"))
                                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                                        graph.addData(1L,listOf("<http://example.org/x/d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                                        graph.addData(1L,listOf("<http://example.org/x/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                                        graph.addData(1L,listOf("_:x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("c")
                            ), listOf(
                                GeneratedMutableMap.map16639map,
                                GeneratedMutableMap.map16640map,
                                GeneratedMutableMap.map16641map,
                                GeneratedMutableMap.map16642map,
                                GeneratedMutableMap.map16643map,
                                GeneratedMutableMap.map16644map,
                                GeneratedMutableMap.map16645map,
                                GeneratedMutableMap.map16646map,
                                GeneratedMutableMap.map16647map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("c")
                            ), listOf(
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("c")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/d>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:x"))
                                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                                        graph.addData(1L,listOf("<http://example.org/x/d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                                        graph.addData(1L,listOf("<http://example.org/x/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                                        graph.addData(1L,listOf("_:x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable("c"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("c"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#subClassOf>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("c")
                        ), listOf(
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16711map,
                                GeneratedMutableMap.map16712map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16707map,
                                GeneratedMutableMap.map16708map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16711map,
                            GeneratedMutableMap.map16712map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/y>"))
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","_:y"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/x/p>","y",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16711map,
                            GeneratedMutableMap.map16712map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map16713map,
                                GeneratedMutableMap.map16714map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map16709map,
                                GeneratedMutableMap.map16710map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map16713map,
                            GeneratedMutableMap.map16714map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/y>"))
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","_:y"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://example.org/x/p>"),AOPVariable("y"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("y"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map16713map,
                            GeneratedMutableMap.map16714map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                            ), listOf(
                                GeneratedMutableMap.map16925map,
                                GeneratedMutableMap.map16926map,
                                GeneratedMutableMap.map16927map,
                                GeneratedMutableMap.map16928map,
                                GeneratedMutableMap.map16929map,
                                GeneratedMutableMap.map16930map,
                                GeneratedMutableMap.map16931map,
                                GeneratedMutableMap.map16932map,
                                GeneratedMutableMap.map16933map,
                                GeneratedMutableMap.map16934map,
                                GeneratedMutableMap.map16935map,
                                GeneratedMutableMap.map16936map,
                                GeneratedMutableMap.map16937map,
                                GeneratedMutableMap.map16938map,
                                GeneratedMutableMap.map16939map,
                                GeneratedMutableMap.map16940map,
                                GeneratedMutableMap.map16941map,
                                GeneratedMutableMap.map16942map,
                                GeneratedMutableMap.map16943map,
                                GeneratedMutableMap.map16944map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16819"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16819"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#c",
                                "#_16819",
                                "#_16824"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16924map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16819",
                            "#_16824",
                            "y"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0"
                            ), listOf(
                                GeneratedMutableMap.map17080map,
                                GeneratedMutableMap.map17081map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16965"
                            ), listOf(
                                GeneratedMutableMap.map17073map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965"
                        ), listOf(
                            GeneratedMutableMap.map17082map,
                            GeneratedMutableMap.map17083map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16965"
                            ), listOf(
                                GeneratedMutableMap.map17082map,
                                GeneratedMutableMap.map17083map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16965"
                            ), listOf(
                                GeneratedMutableMap.map17073map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965"
                        ), listOf(
                            GeneratedMutableMap.map17082map,
                            GeneratedMutableMap.map17083map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16965"
                            ), listOf(
                                GeneratedMutableMap.map17082map,
                                GeneratedMutableMap.map17083map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16974"
                            ), listOf(
                                GeneratedMutableMap.map17074map,
                                GeneratedMutableMap.map17075map,
                                GeneratedMutableMap.map17076map,
                                GeneratedMutableMap.map17077map,
                                GeneratedMutableMap.map17078map,
                                GeneratedMutableMap.map17079map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965",
                            "#_16974"
                        ), listOf(
                            GeneratedMutableMap.map17105map,
                            GeneratedMutableMap.map17106map,
                            GeneratedMutableMap.map17107map,
                            GeneratedMutableMap.map17108map,
                            GeneratedMutableMap.map17109map,
                            GeneratedMutableMap.map17110map,
                            GeneratedMutableMap.map17111map,
                            GeneratedMutableMap.map17112map,
                            GeneratedMutableMap.map17113map,
                            GeneratedMutableMap.map17114map,
                            GeneratedMutableMap.map17115map,
                            GeneratedMutableMap.map17116map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16965",
                                "#_16974"
                            ), listOf(
                                GeneratedMutableMap.map17105map,
                                GeneratedMutableMap.map17106map,
                                GeneratedMutableMap.map17107map,
                                GeneratedMutableMap.map17108map,
                                GeneratedMutableMap.map17109map,
                                GeneratedMutableMap.map17110map,
                                GeneratedMutableMap.map17111map,
                                GeneratedMutableMap.map17112map,
                                GeneratedMutableMap.map17113map,
                                GeneratedMutableMap.map17114map,
                                GeneratedMutableMap.map17115map,
                                GeneratedMutableMap.map17116map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16974"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965",
                            "#_16974"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16965",
                                "#_16974"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16965",
                                "#_16974"
                            ), listOf(
                                GeneratedMutableMap.map17084map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965",
                            "#_16974"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "#b0",
                                "#_16965",
                                "#_16974"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16965"
                            ), listOf(
                                GeneratedMutableMap.map17085map,
                                GeneratedMutableMap.map17086map,
                                GeneratedMutableMap.map17087map,
                                GeneratedMutableMap.map17088map,
                                GeneratedMutableMap.map17089map,
                                GeneratedMutableMap.map17090map,
                                GeneratedMutableMap.map17091map,
                                GeneratedMutableMap.map17092map,
                                GeneratedMutableMap.map17093map,
                                GeneratedMutableMap.map17094map,
                                GeneratedMutableMap.map17095map,
                                GeneratedMutableMap.map17096map,
                                GeneratedMutableMap.map17097map,
                                GeneratedMutableMap.map17098map,
                                GeneratedMutableMap.map17099map,
                                GeneratedMutableMap.map17100map,
                                GeneratedMutableMap.map17101map,
                                GeneratedMutableMap.map17102map,
                                GeneratedMutableMap.map17103map,
                                GeneratedMutableMap.map17104map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16965",
                            "#_16974"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map17191map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map17192map,
                                GeneratedMutableMap.map17193map,
                                GeneratedMutableMap.map17194map,
                                GeneratedMutableMap.map17195map,
                                GeneratedMutableMap.map17196map,
                                GeneratedMutableMap.map17197map,
                                GeneratedMutableMap.map17198map,
                                GeneratedMutableMap.map17199map,
                                GeneratedMutableMap.map17200map,
                                GeneratedMutableMap.map17201map,
                                GeneratedMutableMap.map17202map,
                                GeneratedMutableMap.map17203map,
                                GeneratedMutableMap.map17204map,
                                GeneratedMutableMap.map17205map,
                                GeneratedMutableMap.map17206map,
                                GeneratedMutableMap.map17207map,
                                GeneratedMutableMap.map17208map,
                                GeneratedMutableMap.map17209map,
                                GeneratedMutableMap.map17210map,
                                GeneratedMutableMap.map17211map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map17192map,
                            GeneratedMutableMap.map17193map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map17192map,
                                GeneratedMutableMap.map17193map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16756map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17275map,
                                GeneratedMutableMap.map17276map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map17279map,
                                GeneratedMutableMap.map17280map,
                                GeneratedMutableMap.map17281map,
                                GeneratedMutableMap.map17282map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p",
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map17279map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/John>","p","v",true,false,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p",
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map17279map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("p")
                            ), listOf(
                                GeneratedMutableMap.map17277map,
                                GeneratedMutableMap.map17278map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map17283map,
                                GeneratedMutableMap.map17284map,
                                GeneratedMutableMap.map17285map,
                                GeneratedMutableMap.map17286map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map17283map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#ObjectProperty>"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/John>"),AOPVariable("p"),AOPVariable("v"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map17283map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map17434map,
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map,
                                GeneratedMutableMap.map17435map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17432map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6724map,
                            GeneratedMutableMap.map6725map,
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6724map,
                            GeneratedMutableMap.map6725map,
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map17436map,
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map,
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map,
                                GeneratedMutableMap.map17437map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p")
                            ), listOf(
                                GeneratedMutableMap.map17433map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map,
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map,
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map17434map,
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map,
                                GeneratedMutableMap.map17435map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17432map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6724map,
                            GeneratedMutableMap.map6725map,
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17925map,
                                GeneratedMutableMap.map7199map,
                                GeneratedMutableMap.map7200map,
                                GeneratedMutableMap.map7201map,
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map17926map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map17927map,
                            GeneratedMutableMap.map17928map,
                            GeneratedMutableMap.map17929map,
                            GeneratedMutableMap.map17930map,
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map,
                            GeneratedMutableMap.map7211map,
                            GeneratedMutableMap.map7212map,
                            GeneratedMutableMap.map7213map,
                            GeneratedMutableMap.map7214map,
                            GeneratedMutableMap.map7215map,
                            GeneratedMutableMap.map7216map,
                            GeneratedMutableMap.map7217map,
                            GeneratedMutableMap.map7218map,
                            GeneratedMutableMap.map17931map,
                            GeneratedMutableMap.map17932map,
                            GeneratedMutableMap.map17933map,
                            GeneratedMutableMap.map17934map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17927map,
                                GeneratedMutableMap.map17928map,
                                GeneratedMutableMap.map17929map,
                                GeneratedMutableMap.map17930map,
                                GeneratedMutableMap.map7203map,
                                GeneratedMutableMap.map7204map,
                                GeneratedMutableMap.map7205map,
                                GeneratedMutableMap.map7206map,
                                GeneratedMutableMap.map7207map,
                                GeneratedMutableMap.map7208map,
                                GeneratedMutableMap.map7209map,
                                GeneratedMutableMap.map7210map,
                                GeneratedMutableMap.map7211map,
                                GeneratedMutableMap.map7212map,
                                GeneratedMutableMap.map7213map,
                                GeneratedMutableMap.map7214map,
                                GeneratedMutableMap.map7215map,
                                GeneratedMutableMap.map7216map,
                                GeneratedMutableMap.map7217map,
                                GeneratedMutableMap.map7218map,
                                GeneratedMutableMap.map17931map,
                                GeneratedMutableMap.map17932map,
                                GeneratedMutableMap.map17933map,
                                GeneratedMutableMap.map17934map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17924map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7203map,
                            GeneratedMutableMap.map7204map,
                            GeneratedMutableMap.map7205map,
                            GeneratedMutableMap.map7206map,
                            GeneratedMutableMap.map7207map,
                            GeneratedMutableMap.map7208map,
                            GeneratedMutableMap.map7209map,
                            GeneratedMutableMap.map7210map,
                            GeneratedMutableMap.map7211map,
                            GeneratedMutableMap.map7212map,
                            GeneratedMutableMap.map7213map,
                            GeneratedMutableMap.map7214map,
                            GeneratedMutableMap.map7215map,
                            GeneratedMutableMap.map7216map,
                            GeneratedMutableMap.map7217map,
                            GeneratedMutableMap.map7218map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16171map,
                                GeneratedMutableMap.map16173map,
                                GeneratedMutableMap.map16174map,
                                GeneratedMutableMap.map16637map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16633map,
                                GeneratedMutableMap.map16635map,
                                GeneratedMutableMap.map16171map,
                                GeneratedMutableMap.map16172map,
                                GeneratedMutableMap.map16173map,
                                GeneratedMutableMap.map16174map,
                                GeneratedMutableMap.map18915map,
                                GeneratedMutableMap.map16175map,
                                GeneratedMutableMap.map18916map,
                                GeneratedMutableMap.map18917map,
                                GeneratedMutableMap.map16638map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#y"
                            ), listOf(
                                GeneratedMutableMap.map18914map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c",
                            "#y"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map18990map,
                                GeneratedMutableMap.map18991map,
                                GeneratedMutableMap.map18992map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                            ), listOf(
                                GeneratedMutableMap.map18995map,
                                GeneratedMutableMap.map18996map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1"
                        ), listOf(
                            GeneratedMutableMap.map18995map,
                            GeneratedMutableMap.map18996map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>",false,true,true,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#name>","Y1",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1"
                        ), listOf(
                            GeneratedMutableMap.map18995map,
                            GeneratedMutableMap.map18996map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("X")
                            ), listOf(
                                GeneratedMutableMap.map18993map,
                                GeneratedMutableMap.map17328map,
                                GeneratedMutableMap.map18994map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                            ), listOf(
                                GeneratedMutableMap.map18997map,
                                GeneratedMutableMap.map18998map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1")
                        ), listOf(
                            GeneratedMutableMap.map18997map,
                            GeneratedMutableMap.map18998map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/test#Person>"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#name>"),AOPVariable("Y1"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1")
                        ), listOf(
                            GeneratedMutableMap.map18997map,
                            GeneratedMutableMap.map18998map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                            ), listOf(
                                GeneratedMutableMap.map18995map,
                                GeneratedMutableMap.map18996map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y2"
                            ), listOf(
                                GeneratedMutableMap.map18999map,
                                GeneratedMutableMap.map19000map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1",
                            "Y2"
                        ), listOf(
                            GeneratedMutableMap.map19003map,
                            GeneratedMutableMap.map19004map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>",false,true,true,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#name>","Y1",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#nick>","\"Anick\""))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#nick>","\"Bnick\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#nick>","Y2",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1",
                            "Y2"
                        ), listOf(
                            GeneratedMutableMap.map19003map,
                            GeneratedMutableMap.map19004map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                            ), listOf(
                                GeneratedMutableMap.map18997map,
                                GeneratedMutableMap.map18998map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y2")
                            ), listOf(
                                GeneratedMutableMap.map19001map,
                                GeneratedMutableMap.map19002map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ), listOf(
                            GeneratedMutableMap.map19005map,
                            GeneratedMutableMap.map19006map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/test#Person>"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#name>"),AOPVariable("Y1"),graphName,false)                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#nick>","\"Anick\""))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#nick>","\"Bnick\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#nick>"),AOPVariable("Y2"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ), listOf(
                            GeneratedMutableMap.map19005map,
                            GeneratedMutableMap.map19006map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map19136map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map19137map,
                                GeneratedMutableMap.map19138map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map19137map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map19137map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map19139map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd",
                            "#bb"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map19136map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19225map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19232map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#t>","Y",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19232map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#aa")
                            ), listOf(
                                GeneratedMutableMap.map19227map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19226map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19233map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#aa"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#t>"),AOPVariable("Y"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19233map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19232map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map19223map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19232map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#t>","Y",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#s>","#aa",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19232map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19233map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("Y"),
                                AOPVariable("#aa")
                            ), listOf(
                                GeneratedMutableMap.map19224map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19233map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#aa"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#t>"),AOPVariable("Y"),graphName,false)                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("Y"),AOPVariable.calculate("<http://example.org/test#s>"),AOPVariable("#aa"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19233map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19232map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                            ), listOf(
                                GeneratedMutableMap.map19228map,
                                GeneratedMutableMap.map19229map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19234map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    POPJoinHashMap(
                                        dictionary,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#t>","Y",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#s>","#aa",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#aa","<http://example.org/test#r>","Z",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19234map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19233map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("Z")
                            ), listOf(
                                GeneratedMutableMap.map19230map,
                                GeneratedMutableMap.map19231map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19235map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    LOPJoin(
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#aa"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#t>"),AOPVariable("Y"),graphName,false)                                                                    }()
,
                                                        false                                                    )
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("Y"),AOPVariable.calculate("<http://example.org/test#s>"),AOPVariable("#aa"),graphName,false)                                                    }()
,
                                        false                                    )
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#aa"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Z"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19235map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map19292map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19294map,
                                GeneratedMutableMap.map19295map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19296map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://example.org/test#r>","Y",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19296map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a")
                            ), listOf(
                                GeneratedMutableMap.map19293map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19230map,
                                GeneratedMutableMap.map19231map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19297map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Y"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19297map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map19357map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19359map,
                                GeneratedMutableMap.map19360map,
                                GeneratedMutableMap.map19361map,
                                GeneratedMutableMap.map19362map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19363map,
                            GeneratedMutableMap.map19364map,
                            GeneratedMutableMap.map19365map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://example.org/test#q>","Y",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19363map,
                            GeneratedMutableMap.map19364map,
                            GeneratedMutableMap.map19365map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a")
                            ), listOf(
                                GeneratedMutableMap.map19358map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19366map,
                                GeneratedMutableMap.map19367map,
                                GeneratedMutableMap.map19368map,
                                GeneratedMutableMap.map19369map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19370map,
                            GeneratedMutableMap.map19371map,
                            GeneratedMutableMap.map19372map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#q>"),AOPVariable("Y"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19370map,
                            GeneratedMutableMap.map19371map,
                            GeneratedMutableMap.map19372map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a"
                            ), listOf(
                                GeneratedMutableMap.map19470map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "X",
                                "a",
                                "b",
                                "Y"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map19466map,
                                GeneratedMutableMap.map19467map,
                                GeneratedMutableMap.map19468map,
                                GeneratedMutableMap.map19469map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "a",
                            "b",
                            "Y",
                            "c"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map19635map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19636map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19637map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19637map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19638map,
                                GeneratedMutableMap.map19639map,
                                GeneratedMutableMap.map19640map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19637map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19823map,
                                GeneratedMutableMap.map19824map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19823map,
                                GeneratedMutableMap.map19824map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19757"
                        ), listOf(
                            GeneratedMutableMap.map19823map,
                            GeneratedMutableMap.map19824map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19823map,
                                GeneratedMutableMap.map19824map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19823map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19757"
                        ), listOf(
                            GeneratedMutableMap.map19823map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19823map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19757"
                            ), listOf(
                                GeneratedMutableMap.map19825map,
                                GeneratedMutableMap.map19826map,
                                GeneratedMutableMap.map19827map,
                                GeneratedMutableMap.map19828map,
                                GeneratedMutableMap.map19829map,
                                GeneratedMutableMap.map19830map,
                                GeneratedMutableMap.map19831map,
                                GeneratedMutableMap.map19832map,
                                GeneratedMutableMap.map19833map,
                                GeneratedMutableMap.map19834map,
                                GeneratedMutableMap.map19835map,
                                GeneratedMutableMap.map19836map,
                                GeneratedMutableMap.map19837map,
                                GeneratedMutableMap.map19838map,
                                GeneratedMutableMap.map19839map,
                                GeneratedMutableMap.map19840map,
                                GeneratedMutableMap.map19841map,
                                GeneratedMutableMap.map19842map,
                                GeneratedMutableMap.map19843map,
                                GeneratedMutableMap.map19844map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19757",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19869"
                            ), listOf(
                                GeneratedMutableMap.map19940map,
                                GeneratedMutableMap.map19941map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19869"
                            ), listOf(
                                GeneratedMutableMap.map19940map,
                                GeneratedMutableMap.map19941map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19869"
                        ), listOf(
                            GeneratedMutableMap.map19940map,
                            GeneratedMutableMap.map19941map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19869"
                            ), listOf(
                                GeneratedMutableMap.map19940map,
                                GeneratedMutableMap.map19941map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19869"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19869"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19869"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19869"
                            ), listOf(
                                GeneratedMutableMap.map19942map,
                                GeneratedMutableMap.map19943map,
                                GeneratedMutableMap.map19944map,
                                GeneratedMutableMap.map19945map,
                                GeneratedMutableMap.map19946map,
                                GeneratedMutableMap.map19947map,
                                GeneratedMutableMap.map19948map,
                                GeneratedMutableMap.map19949map,
                                GeneratedMutableMap.map19950map,
                                GeneratedMutableMap.map19951map,
                                GeneratedMutableMap.map19952map,
                                GeneratedMutableMap.map19953map,
                                GeneratedMutableMap.map19954map,
                                GeneratedMutableMap.map19955map,
                                GeneratedMutableMap.map19956map,
                                GeneratedMutableMap.map19957map,
                                GeneratedMutableMap.map19958map,
                                GeneratedMutableMap.map19959map,
                                GeneratedMutableMap.map19960map,
                                GeneratedMutableMap.map19961map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19869",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19983"
                            ), listOf(
                                GeneratedMutableMap.map20049map,
                                GeneratedMutableMap.map20050map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19983"
                            ), listOf(
                                GeneratedMutableMap.map20049map,
                                GeneratedMutableMap.map20050map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19983"
                        ), listOf(
                            GeneratedMutableMap.map20049map,
                            GeneratedMutableMap.map20050map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19983"
                            ), listOf(
                                GeneratedMutableMap.map20049map,
                                GeneratedMutableMap.map20050map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19983"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19983"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_19983"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19983"
                            ), listOf(
                                GeneratedMutableMap.map20051map,
                                GeneratedMutableMap.map20052map,
                                GeneratedMutableMap.map20053map,
                                GeneratedMutableMap.map20054map,
                                GeneratedMutableMap.map20055map,
                                GeneratedMutableMap.map20056map,
                                GeneratedMutableMap.map20057map,
                                GeneratedMutableMap.map20058map,
                                GeneratedMutableMap.map20059map,
                                GeneratedMutableMap.map20060map,
                                GeneratedMutableMap.map20061map,
                                GeneratedMutableMap.map20062map,
                                GeneratedMutableMap.map20063map,
                                GeneratedMutableMap.map20064map,
                                GeneratedMutableMap.map20065map,
                                GeneratedMutableMap.map20066map,
                                GeneratedMutableMap.map20067map,
                                GeneratedMutableMap.map20068map,
                                GeneratedMutableMap.map20069map,
                                GeneratedMutableMap.map20070map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19983",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20093"
                            ), listOf(
                                GeneratedMutableMap.map20175map,
                                GeneratedMutableMap.map20176map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20093"
                            ), listOf(
                                GeneratedMutableMap.map20175map,
                                GeneratedMutableMap.map20176map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20093"
                        ), listOf(
                            GeneratedMutableMap.map20175map,
                            GeneratedMutableMap.map20176map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20093"
                            ), listOf(
                                GeneratedMutableMap.map20175map,
                                GeneratedMutableMap.map20176map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20093"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20093"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20093"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20093"
                            ), listOf(
                                GeneratedMutableMap.map20177map,
                                GeneratedMutableMap.map20178map,
                                GeneratedMutableMap.map20179map,
                                GeneratedMutableMap.map20180map,
                                GeneratedMutableMap.map20181map,
                                GeneratedMutableMap.map20182map,
                                GeneratedMutableMap.map20183map,
                                GeneratedMutableMap.map20184map,
                                GeneratedMutableMap.map20185map,
                                GeneratedMutableMap.map20186map,
                                GeneratedMutableMap.map20187map,
                                GeneratedMutableMap.map20188map,
                                GeneratedMutableMap.map20189map,
                                GeneratedMutableMap.map20190map,
                                GeneratedMutableMap.map20191map,
                                GeneratedMutableMap.map20192map,
                                GeneratedMutableMap.map20193map,
                                GeneratedMutableMap.map20194map,
                                GeneratedMutableMap.map20195map,
                                GeneratedMutableMap.map20196map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20093",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20219"
                            ), listOf(
                                GeneratedMutableMap.map20321map,
                                GeneratedMutableMap.map20322map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20219"
                            ), listOf(
                                GeneratedMutableMap.map20321map,
                                GeneratedMutableMap.map20322map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20219"
                        ), listOf(
                            GeneratedMutableMap.map20321map,
                            GeneratedMutableMap.map20322map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20219"
                            ), listOf(
                                GeneratedMutableMap.map20321map,
                                GeneratedMutableMap.map20322map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20219"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20219"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20219"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20219"
                            ), listOf(
                                GeneratedMutableMap.map20301map,
                                GeneratedMutableMap.map20302map,
                                GeneratedMutableMap.map20303map,
                                GeneratedMutableMap.map20304map,
                                GeneratedMutableMap.map20305map,
                                GeneratedMutableMap.map20306map,
                                GeneratedMutableMap.map20307map,
                                GeneratedMutableMap.map20308map,
                                GeneratedMutableMap.map20309map,
                                GeneratedMutableMap.map20310map,
                                GeneratedMutableMap.map20311map,
                                GeneratedMutableMap.map20312map,
                                GeneratedMutableMap.map20313map,
                                GeneratedMutableMap.map20314map,
                                GeneratedMutableMap.map20315map,
                                GeneratedMutableMap.map20316map,
                                GeneratedMutableMap.map20317map,
                                GeneratedMutableMap.map20318map,
                                GeneratedMutableMap.map20319map,
                                GeneratedMutableMap.map20320map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20219",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20345"
                            ), listOf(
                                GeneratedMutableMap.map20447map,
                                GeneratedMutableMap.map20448map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20345"
                            ), listOf(
                                GeneratedMutableMap.map20447map,
                                GeneratedMutableMap.map20448map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20345"
                        ), listOf(
                            GeneratedMutableMap.map20447map,
                            GeneratedMutableMap.map20448map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20345"
                            ), listOf(
                                GeneratedMutableMap.map20447map,
                                GeneratedMutableMap.map20448map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20345"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20345"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20345"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20345"
                            ), listOf(
                                GeneratedMutableMap.map20427map,
                                GeneratedMutableMap.map20428map,
                                GeneratedMutableMap.map20429map,
                                GeneratedMutableMap.map20430map,
                                GeneratedMutableMap.map20431map,
                                GeneratedMutableMap.map20432map,
                                GeneratedMutableMap.map20433map,
                                GeneratedMutableMap.map20434map,
                                GeneratedMutableMap.map20435map,
                                GeneratedMutableMap.map20436map,
                                GeneratedMutableMap.map20437map,
                                GeneratedMutableMap.map20438map,
                                GeneratedMutableMap.map20439map,
                                GeneratedMutableMap.map20440map,
                                GeneratedMutableMap.map20441map,
                                GeneratedMutableMap.map20442map,
                                GeneratedMutableMap.map20443map,
                                GeneratedMutableMap.map20444map,
                                GeneratedMutableMap.map20445map,
                                GeneratedMutableMap.map20446map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20345",
                            "parent"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20472"
                            ), listOf(
                                GeneratedMutableMap.map20540map,
                                GeneratedMutableMap.map20541map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20472"
                            ), listOf(
                                GeneratedMutableMap.map20540map,
                                GeneratedMutableMap.map20541map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20472"
                        ), listOf(
                            GeneratedMutableMap.map20540map,
                            GeneratedMutableMap.map20541map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20472"
                            ), listOf(
                                GeneratedMutableMap.map20540map,
                                GeneratedMutableMap.map20541map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20472"
                            ), listOf(
                                GeneratedMutableMap.map20540map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20472"
                        ), listOf(
                            GeneratedMutableMap.map20540map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20472"
                            ), listOf(
                                GeneratedMutableMap.map20540map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_20472"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20472",
                            "C"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                GeneratedMutableMap.map20646map,
                                GeneratedMutableMap.map20647map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "C",
                                "#b"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b"
                            ), listOf(
                                GeneratedMutableMap.map20646map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "C",
                            "#b"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20664",
                                "#_20669",
                                "#_20661"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20661"
                            ), listOf(
                                GeneratedMutableMap.map20753map,
                                GeneratedMutableMap.map20754map,
                                GeneratedMutableMap.map20755map,
                                GeneratedMutableMap.map20756map,
                                GeneratedMutableMap.map20757map,
                                GeneratedMutableMap.map20758map,
                                GeneratedMutableMap.map20759map,
                                GeneratedMutableMap.map20760map,
                                GeneratedMutableMap.map20761map,
                                GeneratedMutableMap.map20762map,
                                GeneratedMutableMap.map20763map,
                                GeneratedMutableMap.map20764map,
                                GeneratedMutableMap.map20765map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20664",
                            "#_20669",
                            "#_20661",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20782",
                                "#_20786",
                                "#_20796",
                                "#_20779"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20779"
                            ), listOf(
                                GeneratedMutableMap.map20910map,
                                GeneratedMutableMap.map20911map,
                                GeneratedMutableMap.map20912map,
                                GeneratedMutableMap.map20913map,
                                GeneratedMutableMap.map20914map,
                                GeneratedMutableMap.map20915map,
                                GeneratedMutableMap.map20916map,
                                GeneratedMutableMap.map20917map,
                                GeneratedMutableMap.map20918map,
                                GeneratedMutableMap.map20919map,
                                GeneratedMutableMap.map20920map,
                                GeneratedMutableMap.map20921map,
                                GeneratedMutableMap.map20922map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20782",
                            "#_20786",
                            "#_20796",
                            "#_20779",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_20936",
                                "#_20947",
                                "#_20952",
                                "#_20944"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20936"
                            ), listOf(
                                GeneratedMutableMap.map21067map,
                                GeneratedMutableMap.map21068map,
                                GeneratedMutableMap.map21069map,
                                GeneratedMutableMap.map21070map,
                                GeneratedMutableMap.map21071map,
                                GeneratedMutableMap.map21072map,
                                GeneratedMutableMap.map21073map,
                                GeneratedMutableMap.map21074map,
                                GeneratedMutableMap.map21075map,
                                GeneratedMutableMap.map21076map,
                                GeneratedMutableMap.map21077map,
                                GeneratedMutableMap.map21078map,
                                GeneratedMutableMap.map21079map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20936",
                            "#_20947",
                            "#_20952",
                            "#_20944",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21093"
                            ), listOf(
                                GeneratedMutableMap.map21197map,
                                GeneratedMutableMap.map21198map,
                                GeneratedMutableMap.map21199map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21098"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21093",
                            "#_21098"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21093",
                                "#_21098",
                                "#_21103"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21093"
                            ), listOf(
                                GeneratedMutableMap.map21200map,
                                GeneratedMutableMap.map21201map,
                                GeneratedMutableMap.map21202map,
                                GeneratedMutableMap.map21203map,
                                GeneratedMutableMap.map21204map,
                                GeneratedMutableMap.map21205map,
                                GeneratedMutableMap.map21206map,
                                GeneratedMutableMap.map21207map,
                                GeneratedMutableMap.map21208map,
                                GeneratedMutableMap.map21209map,
                                GeneratedMutableMap.map21210map,
                                GeneratedMutableMap.map21211map,
                                GeneratedMutableMap.map21212map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21093",
                            "#_21098",
                            "#_21103",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21226"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21234"
                            ), listOf(
                                GeneratedMutableMap.map21369map,
                                GeneratedMutableMap.map21370map,
                                GeneratedMutableMap.map21371map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21226",
                            "#_21234"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21226",
                                "#_21234",
                                "#_21239",
                                "#_21244"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21226"
                            ), listOf(
                                GeneratedMutableMap.map21372map,
                                GeneratedMutableMap.map21373map,
                                GeneratedMutableMap.map21374map,
                                GeneratedMutableMap.map21375map,
                                GeneratedMutableMap.map21376map,
                                GeneratedMutableMap.map21377map,
                                GeneratedMutableMap.map21378map,
                                GeneratedMutableMap.map21379map,
                                GeneratedMutableMap.map21380map,
                                GeneratedMutableMap.map21381map,
                                GeneratedMutableMap.map21382map,
                                GeneratedMutableMap.map21383map,
                                GeneratedMutableMap.map21384map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21226",
                            "#_21234",
                            "#_21239",
                            "#_21244",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21398"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21406"
                            ), listOf(
                                GeneratedMutableMap.map21579map,
                                GeneratedMutableMap.map21580map,
                                GeneratedMutableMap.map21581map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21398",
                            "#_21406"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21398",
                                "#_21406",
                                "#_21411",
                                "#_21416",
                                "#_21421"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21398"
                            ), listOf(
                                GeneratedMutableMap.map21566map,
                                GeneratedMutableMap.map21567map,
                                GeneratedMutableMap.map21568map,
                                GeneratedMutableMap.map21569map,
                                GeneratedMutableMap.map21570map,
                                GeneratedMutableMap.map21571map,
                                GeneratedMutableMap.map21572map,
                                GeneratedMutableMap.map21573map,
                                GeneratedMutableMap.map21574map,
                                GeneratedMutableMap.map21575map,
                                GeneratedMutableMap.map21576map,
                                GeneratedMutableMap.map21577map,
                                GeneratedMutableMap.map21578map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21398",
                            "#_21406",
                            "#_21411",
                            "#_21416",
                            "#_21421",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21595"
                            ), listOf(
                                GeneratedMutableMap.map21777map,
                                GeneratedMutableMap.map21778map,
                                GeneratedMutableMap.map21779map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21600"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21595",
                            "#_21600"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21595",
                                "#_21600"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21604"
                            ), listOf(
                                GeneratedMutableMap.map21774map,
                                GeneratedMutableMap.map21775map,
                                GeneratedMutableMap.map21776map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21595",
                            "#_21600",
                            "#_21604"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21595",
                                "#_21600",
                                "#_21604",
                                "#_21609",
                                "#_21614",
                                "#_21620"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21595"
                            ), listOf(
                                GeneratedMutableMap.map21780map,
                                GeneratedMutableMap.map21781map,
                                GeneratedMutableMap.map21782map,
                                GeneratedMutableMap.map21783map,
                                GeneratedMutableMap.map21784map,
                                GeneratedMutableMap.map21785map,
                                GeneratedMutableMap.map21786map,
                                GeneratedMutableMap.map21787map,
                                GeneratedMutableMap.map21788map,
                                GeneratedMutableMap.map21789map,
                                GeneratedMutableMap.map21790map,
                                GeneratedMutableMap.map21791map,
                                GeneratedMutableMap.map21792map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21595",
                            "#_21600",
                            "#_21604",
                            "#_21609",
                            "#_21614",
                            "#_21620",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_21806",
                                "#_21814"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21806"
                            ), listOf(
                                GeneratedMutableMap.map21913map,
                                GeneratedMutableMap.map21914map,
                                GeneratedMutableMap.map21915map,
                                GeneratedMutableMap.map21916map,
                                GeneratedMutableMap.map21917map,
                                GeneratedMutableMap.map21918map,
                                GeneratedMutableMap.map21919map,
                                GeneratedMutableMap.map21920map,
                                GeneratedMutableMap.map21921map,
                                GeneratedMutableMap.map21922map,
                                GeneratedMutableMap.map21923map,
                                GeneratedMutableMap.map21924map,
                                GeneratedMutableMap.map21925map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21806",
                            "#_21814",
                            "x"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map24929map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map24927map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map24931map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map24931map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map24930map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map24928map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map24932map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s6>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s7>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map24932map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map25527map,
                                GeneratedMutableMap.map25528map,
                                GeneratedMutableMap.map25529map,
                                GeneratedMutableMap.map25530map,
                                GeneratedMutableMap.map25531map,
                                GeneratedMutableMap.map25532map,
                                GeneratedMutableMap.map25533map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s2",
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map25541map,
                                GeneratedMutableMap.map25542map,
                                GeneratedMutableMap.map25543map,
                                GeneratedMutableMap.map25544map,
                                GeneratedMutableMap.map25545map,
                                GeneratedMutableMap.map25546map,
                                GeneratedMutableMap.map25547map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "str1",
                            "s2",
                            "str2"
                        ), listOf(
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
                            GeneratedMutableMap.map25594map,
                            GeneratedMutableMap.map25595map,
                            GeneratedMutableMap.map25596map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "str1",
                            "s2",
                            "str2"
                        ), listOf(
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
                            GeneratedMutableMap.map25594map,
                            GeneratedMutableMap.map25595map,
                            GeneratedMutableMap.map25596map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map25534map,
                                GeneratedMutableMap.map25535map,
                                GeneratedMutableMap.map25536map,
                                GeneratedMutableMap.map25537map,
                                GeneratedMutableMap.map25538map,
                                GeneratedMutableMap.map25539map,
                                GeneratedMutableMap.map25540map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s2"),
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map25534map,
                                GeneratedMutableMap.map25535map,
                                GeneratedMutableMap.map25536map,
                                GeneratedMutableMap.map25537map,
                                GeneratedMutableMap.map25538map,
                                GeneratedMutableMap.map25539map,
                                GeneratedMutableMap.map25540map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("str1"),
                            AOPVariable("s2"),
                            AOPVariable("str2")
                        ), listOf(
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
                            GeneratedMutableMap.map25621map,
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
                            GeneratedMutableMap.map25645map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s1"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                    }()
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
                                        LOPTriple(AOPVariable("s2"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("str1"),
                            AOPVariable("s2"),
                            AOPVariable("str2")
                        ), listOf(
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
                            GeneratedMutableMap.map25621map,
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
                            GeneratedMutableMap.map25645map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map27453map,
                                GeneratedMutableMap.map27454map,
                                GeneratedMutableMap.map27455map,
                                GeneratedMutableMap.map27456map,
                                GeneratedMutableMap.map27457map,
                                GeneratedMutableMap.map27458map,
                                GeneratedMutableMap.map27459map,
                                GeneratedMutableMap.map27460map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map27437map,
                                GeneratedMutableMap.map27438map,
                                GeneratedMutableMap.map27439map,
                                GeneratedMutableMap.map27440map,
                                GeneratedMutableMap.map27441map,
                                GeneratedMutableMap.map27442map,
                                GeneratedMutableMap.map27443map,
                                GeneratedMutableMap.map27444map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map27469map,
                            GeneratedMutableMap.map27470map,
                            GeneratedMutableMap.map27471map,
                            GeneratedMutableMap.map27472map,
                            GeneratedMutableMap.map27473map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map27469map,
                            GeneratedMutableMap.map27470map,
                            GeneratedMutableMap.map27471map,
                            GeneratedMutableMap.map27472map,
                            GeneratedMutableMap.map27473map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map27461map,
                                GeneratedMutableMap.map27462map,
                                GeneratedMutableMap.map27463map,
                                GeneratedMutableMap.map27464map,
                                GeneratedMutableMap.map27465map,
                                GeneratedMutableMap.map27466map,
                                GeneratedMutableMap.map27467map,
                                GeneratedMutableMap.map27468map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map27445map,
                                GeneratedMutableMap.map27446map,
                                GeneratedMutableMap.map27447map,
                                GeneratedMutableMap.map27448map,
                                GeneratedMutableMap.map27449map,
                                GeneratedMutableMap.map27450map,
                                GeneratedMutableMap.map27451map,
                                GeneratedMutableMap.map27452map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27478map,
                            GeneratedMutableMap.map27479map,
                            GeneratedMutableMap.map27480map,
                            GeneratedMutableMap.map27481map,
                            GeneratedMutableMap.map27482map,
                            GeneratedMutableMap.map27483map,
                            GeneratedMutableMap.map27484map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                    }()
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
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27478map,
                            GeneratedMutableMap.map27479map,
                            GeneratedMutableMap.map27480map,
                            GeneratedMutableMap.map27481map,
                            GeneratedMutableMap.map27482map,
                            GeneratedMutableMap.map27483map,
                            GeneratedMutableMap.map27484map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "a",
                                "s1"
                            ), listOf(
                                GeneratedMutableMap.map31312map,
                                GeneratedMutableMap.map31313map,
                                GeneratedMutableMap.map31314map,
                                GeneratedMutableMap.map31315map,
                                GeneratedMutableMap.map31316map,
                                GeneratedMutableMap.map31317map,
                                GeneratedMutableMap.map31318map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "s2"
                            ), listOf(
                                GeneratedMutableMap.map31305map,
                                GeneratedMutableMap.map31306map,
                                GeneratedMutableMap.map31307map,
                                GeneratedMutableMap.map31308map,
                                GeneratedMutableMap.map31309map,
                                GeneratedMutableMap.map31310map,
                                GeneratedMutableMap.map31311map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
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
                            GeneratedMutableMap.map31365map,
                            GeneratedMutableMap.map31366map,
                            GeneratedMutableMap.map31367map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
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
                            GeneratedMutableMap.map31365map,
                            GeneratedMutableMap.map31366map,
                            GeneratedMutableMap.map31367map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1")
                            ), listOf(
                                GeneratedMutableMap.map22991map,
                                GeneratedMutableMap.map22992map,
                                GeneratedMutableMap.map22993map,
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map22995map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("b"),
                                AOPVariable("s2")
                            ), listOf(
                                GeneratedMutableMap.map22991map,
                                GeneratedMutableMap.map22992map,
                                GeneratedMutableMap.map22993map,
                                GeneratedMutableMap.map22994map,
                                GeneratedMutableMap.map22995map,
                                GeneratedMutableMap.map22996map,
                                GeneratedMutableMap.map22997map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
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
                            GeneratedMutableMap.map31414map,
                            GeneratedMutableMap.map31415map,
                            GeneratedMutableMap.map31416map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                    }()
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
                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
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
                            GeneratedMutableMap.map31414map,
                            GeneratedMutableMap.map31415map,
                            GeneratedMutableMap.map31416map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map32781map,
                                GeneratedMutableMap.map32782map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32916map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32918map,
                            GeneratedMutableMap.map32919map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32918map,
                            GeneratedMutableMap.map32919map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map32783map,
                                GeneratedMutableMap.map32784map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32917map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32920map,
                            GeneratedMutableMap.map32921map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("v"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("w"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32920map,
                            GeneratedMutableMap.map32921map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map32781map,
                                GeneratedMutableMap.map32782map,
                                GeneratedMutableMap.map33063map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32916map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32918map,
                            GeneratedMutableMap.map32919map,
                            GeneratedMutableMap.map33065map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s3>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32918map,
                            GeneratedMutableMap.map32919map,
                            GeneratedMutableMap.map33065map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map32783map,
                                GeneratedMutableMap.map32784map,
                                GeneratedMutableMap.map33064map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32917map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32920map,
                            GeneratedMutableMap.map32921map,
                            GeneratedMutableMap.map33066map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example/s3>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("v"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("w"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32920map,
                            GeneratedMutableMap.map32921map,
                            GeneratedMutableMap.map33066map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10298map,
                                GeneratedMutableMap.map10299map,
                                GeneratedMutableMap.map33341map,
                                GeneratedMutableMap.map10301map,
                                GeneratedMutableMap.map33342map,
                                GeneratedMutableMap.map10303map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10554map,
                                GeneratedMutableMap.map10555map,
                                GeneratedMutableMap.map33541map,
                                GeneratedMutableMap.map10557map,
                                GeneratedMutableMap.map33542map,
                                GeneratedMutableMap.map10559map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10560map,
                            GeneratedMutableMap.map10561map,
                            GeneratedMutableMap.map33543map,
                            GeneratedMutableMap.map10563map,
                            GeneratedMutableMap.map33544map,
                            GeneratedMutableMap.map10565map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                                    }()
,
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10560map,
                            GeneratedMutableMap.map10561map,
                            GeneratedMutableMap.map33543map,
                            GeneratedMutableMap.map10563map,
                            GeneratedMutableMap.map33544map,
                            GeneratedMutableMap.map10565map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10380map,
                                GeneratedMutableMap.map10381map,
                                GeneratedMutableMap.map33343map,
                                GeneratedMutableMap.map10383map,
                                GeneratedMutableMap.map33344map,
                                GeneratedMutableMap.map10385map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10380map,
                                GeneratedMutableMap.map10381map,
                                GeneratedMutableMap.map33343map,
                                GeneratedMutableMap.map10383map,
                                GeneratedMutableMap.map33344map,
                                GeneratedMutableMap.map10385map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10690map,
                            GeneratedMutableMap.map10691map,
                            GeneratedMutableMap.map33545map,
                            GeneratedMutableMap.map10693map,
                            GeneratedMutableMap.map33546map,
                            GeneratedMutableMap.map10695map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"foo\""))
                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p2>","\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","_:o6"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10690map,
                            GeneratedMutableMap.map10691map,
                            GeneratedMutableMap.map33545map,
                            GeneratedMutableMap.map10693map,
                            GeneratedMutableMap.map33546map,
                            GeneratedMutableMap.map10695map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map35429map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35431map,
                                GeneratedMutableMap.map35432map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35434map,
                            GeneratedMutableMap.map35435map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35434map,
                            GeneratedMutableMap.map35435map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map35430map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map35430map,
                                GeneratedMutableMap.map35433map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35436map,
                            GeneratedMutableMap.map35437map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35436map,
                            GeneratedMutableMap.map35437map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map35429map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35431map,
                                GeneratedMutableMap.map35556map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35434map,
                            GeneratedMutableMap.map35557map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35434map,
                            GeneratedMutableMap.map35557map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map35430map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map35430map,
                                GeneratedMutableMap.map35558map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35436map,
                            GeneratedMutableMap.map35559map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35436map,
                            GeneratedMutableMap.map35559map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map35429map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35432map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35435map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map35429map,
                                GeneratedMutableMap.map36065map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                            ), listOf(
                                GeneratedMutableMap.map36067map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map36068map,
                            GeneratedMutableMap.map36069map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        true                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map36068map,
                            GeneratedMutableMap.map36069map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map35430map,
                                GeneratedMutableMap.map36066map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map35433map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35437map,
                            GeneratedMutableMap.map36070map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("l"),graphName,false)                                    }()
,
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35437map,
                            GeneratedMutableMap.map36070map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37381",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37517map,
                                GeneratedMutableMap.map37518map,
                                GeneratedMutableMap.map37519map,
                                GeneratedMutableMap.map37520map,
                                GeneratedMutableMap.map37521map,
                                GeneratedMutableMap.map37522map,
                                GeneratedMutableMap.map37523map,
                                GeneratedMutableMap.map37524map,
                                GeneratedMutableMap.map37525map,
                                GeneratedMutableMap.map37526map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37381"
                            ), listOf(
                                GeneratedMutableMap.map37497map,
                                GeneratedMutableMap.map37498map,
                                GeneratedMutableMap.map37499map,
                                GeneratedMutableMap.map37500map,
                                GeneratedMutableMap.map37501map,
                                GeneratedMutableMap.map37502map,
                                GeneratedMutableMap.map37503map,
                                GeneratedMutableMap.map37504map,
                                GeneratedMutableMap.map37505map,
                                GeneratedMutableMap.map37506map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37381",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37537map,
                            GeneratedMutableMap.map37538map,
                            GeneratedMutableMap.map37539map,
                            GeneratedMutableMap.map37540map,
                            GeneratedMutableMap.map37541map,
                            GeneratedMutableMap.map37542map,
                            GeneratedMutableMap.map37543map,
                            GeneratedMutableMap.map37544map,
                            GeneratedMutableMap.map37545map,
                            GeneratedMutableMap.map37546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("_:_37358","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37359","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37360","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37361","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37362","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37363","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37364","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37365","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37366","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37367","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37381","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37358"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37359"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37360"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37361"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37362"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37363"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37364"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37365"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37366"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37367"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37381",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37381",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37537map,
                            GeneratedMutableMap.map37538map,
                            GeneratedMutableMap.map37539map,
                            GeneratedMutableMap.map37540map,
                            GeneratedMutableMap.map37541map,
                            GeneratedMutableMap.map37542map,
                            GeneratedMutableMap.map37543map,
                            GeneratedMutableMap.map37544map,
                            GeneratedMutableMap.map37545map,
                            GeneratedMutableMap.map37546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#_37381"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37527map,
                                GeneratedMutableMap.map37528map,
                                GeneratedMutableMap.map37529map,
                                GeneratedMutableMap.map37530map,
                                GeneratedMutableMap.map37531map,
                                GeneratedMutableMap.map37532map,
                                GeneratedMutableMap.map37533map,
                                GeneratedMutableMap.map37534map,
                                GeneratedMutableMap.map37535map,
                                GeneratedMutableMap.map37536map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37381")
                            ), listOf(
                                GeneratedMutableMap.map37507map,
                                GeneratedMutableMap.map37508map,
                                GeneratedMutableMap.map37509map,
                                GeneratedMutableMap.map37510map,
                                GeneratedMutableMap.map37511map,
                                GeneratedMutableMap.map37512map,
                                GeneratedMutableMap.map37513map,
                                GeneratedMutableMap.map37514map,
                                GeneratedMutableMap.map37515map,
                                GeneratedMutableMap.map37516map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37381"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37547map,
                            GeneratedMutableMap.map37548map,
                            GeneratedMutableMap.map37549map,
                            GeneratedMutableMap.map37550map,
                            GeneratedMutableMap.map37551map,
                            GeneratedMutableMap.map37552map,
                            GeneratedMutableMap.map37553map,
                            GeneratedMutableMap.map37554map,
                            GeneratedMutableMap.map37555map,
                            GeneratedMutableMap.map37556map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("_:_37358","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37359","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37360","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37361","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37362","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37363","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37364","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37365","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37366","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37367","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_37381"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37358"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37359"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37360"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37361"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37362"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37363"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37364"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37365"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37366"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37367"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37381"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37381"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37547map,
                            GeneratedMutableMap.map37548map,
                            GeneratedMutableMap.map37549map,
                            GeneratedMutableMap.map37550map,
                            GeneratedMutableMap.map37551map,
                            GeneratedMutableMap.map37552map,
                            GeneratedMutableMap.map37553map,
                            GeneratedMutableMap.map37554map,
                            GeneratedMutableMap.map37555map,
                            GeneratedMutableMap.map37556map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37381",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37537map,
                                GeneratedMutableMap.map37538map,
                                GeneratedMutableMap.map37539map,
                                GeneratedMutableMap.map37540map,
                                GeneratedMutableMap.map37541map,
                                GeneratedMutableMap.map37542map,
                                GeneratedMutableMap.map37543map,
                                GeneratedMutableMap.map37544map,
                                GeneratedMutableMap.map37545map,
                                GeneratedMutableMap.map37546map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37557map,
                                GeneratedMutableMap.map37558map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37381",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37537map,
                            GeneratedMutableMap.map37538map,
                            GeneratedMutableMap.map37539map,
                            GeneratedMutableMap.map37540map,
                            GeneratedMutableMap.map37541map,
                            GeneratedMutableMap.map37542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("_:_37358","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37359","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37360","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37361","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37362","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37363","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37364","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37365","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37366","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37367","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37381","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37358"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37359"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37360"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37361"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37362"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37363"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37364"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37365"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37366"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37367"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37381",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                                                                                    }()

                                                                                    )

                                                                    )

                                                    )

                                    )
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37381",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37537map,
                            GeneratedMutableMap.map37538map,
                            GeneratedMutableMap.map37539map,
                            GeneratedMutableMap.map37540map,
                            GeneratedMutableMap.map37541map,
                            GeneratedMutableMap.map37542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#_37381"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37547map,
                                GeneratedMutableMap.map37548map,
                                GeneratedMutableMap.map37549map,
                                GeneratedMutableMap.map37550map,
                                GeneratedMutableMap.map37551map,
                                GeneratedMutableMap.map37552map,
                                GeneratedMutableMap.map37553map,
                                GeneratedMutableMap.map37554map,
                                GeneratedMutableMap.map37555map,
                                GeneratedMutableMap.map37556map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37561map,
                                GeneratedMutableMap.map37562map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37381"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37547map,
                            GeneratedMutableMap.map37548map,
                            GeneratedMutableMap.map37549map,
                            GeneratedMutableMap.map37550map,
                            GeneratedMutableMap.map37551map,
                            GeneratedMutableMap.map37552map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("_:_37358","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37359","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37360","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37361","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37362","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37363","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37364","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37365","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37366","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37367","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_37381"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37358"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37359"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37360"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37361"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37362"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37363"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37364"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37365"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37366"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37367"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37381"),graphName,false)                                                    }()
,
                                        false                                    )
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                    }()

                                                                                    )

                                                                    )

                                                    )

                                    )
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37381"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37547map,
                            GeneratedMutableMap.map37548map,
                            GeneratedMutableMap.map37549map,
                            GeneratedMutableMap.map37550map,
                            GeneratedMutableMap.map37551map,
                            GeneratedMutableMap.map37552map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "P",
                                "F"
                            ), listOf(
                                GeneratedMutableMap.map37829map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37831map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37832map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        false                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37832map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F")
                            ), listOf(
                                GeneratedMutableMap.map37830map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37833map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37834map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37834map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37868",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37988map,
                                GeneratedMutableMap.map37989map,
                                GeneratedMutableMap.map37990map,
                                GeneratedMutableMap.map37991map,
                                GeneratedMutableMap.map37992map,
                                GeneratedMutableMap.map37993map,
                                GeneratedMutableMap.map37994map,
                                GeneratedMutableMap.map37995map,
                                GeneratedMutableMap.map37996map,
                                GeneratedMutableMap.map37997map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37868"
                            ), listOf(
                                GeneratedMutableMap.map38008map,
                                GeneratedMutableMap.map38009map,
                                GeneratedMutableMap.map38010map,
                                GeneratedMutableMap.map38011map,
                                GeneratedMutableMap.map38012map,
                                GeneratedMutableMap.map38013map,
                                GeneratedMutableMap.map38014map,
                                GeneratedMutableMap.map38015map,
                                GeneratedMutableMap.map38016map,
                                GeneratedMutableMap.map38017map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37868",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map38028map,
                            GeneratedMutableMap.map38029map,
                            GeneratedMutableMap.map38030map,
                            GeneratedMutableMap.map38031map,
                            GeneratedMutableMap.map38032map,
                            GeneratedMutableMap.map38033map,
                            GeneratedMutableMap.map38034map,
                            GeneratedMutableMap.map38035map,
                            GeneratedMutableMap.map38036map,
                            GeneratedMutableMap.map38037map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("_:_37845","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37846","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37847","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37848","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37849","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37850","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37851","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37852","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37853","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37854","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37868","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37845"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37846"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37847"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37848"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37849"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37850"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37851"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37852"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37853"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37854"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37868",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37868",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map38028map,
                            GeneratedMutableMap.map38029map,
                            GeneratedMutableMap.map38030map,
                            GeneratedMutableMap.map38031map,
                            GeneratedMutableMap.map38032map,
                            GeneratedMutableMap.map38033map,
                            GeneratedMutableMap.map38034map,
                            GeneratedMutableMap.map38035map,
                            GeneratedMutableMap.map38036map,
                            GeneratedMutableMap.map38037map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#_37868"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37998map,
                                GeneratedMutableMap.map37999map,
                                GeneratedMutableMap.map38000map,
                                GeneratedMutableMap.map38001map,
                                GeneratedMutableMap.map38002map,
                                GeneratedMutableMap.map38003map,
                                GeneratedMutableMap.map38004map,
                                GeneratedMutableMap.map38005map,
                                GeneratedMutableMap.map38006map,
                                GeneratedMutableMap.map38007map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37868")
                            ), listOf(
                                GeneratedMutableMap.map38018map,
                                GeneratedMutableMap.map38019map,
                                GeneratedMutableMap.map38020map,
                                GeneratedMutableMap.map38021map,
                                GeneratedMutableMap.map38022map,
                                GeneratedMutableMap.map38023map,
                                GeneratedMutableMap.map38024map,
                                GeneratedMutableMap.map38025map,
                                GeneratedMutableMap.map38026map,
                                GeneratedMutableMap.map38027map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37868"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map38038map,
                            GeneratedMutableMap.map38039map,
                            GeneratedMutableMap.map38040map,
                            GeneratedMutableMap.map38041map,
                            GeneratedMutableMap.map38042map,
                            GeneratedMutableMap.map38043map,
                            GeneratedMutableMap.map38044map,
                            GeneratedMutableMap.map38045map,
                            GeneratedMutableMap.map38046map,
                            GeneratedMutableMap.map38047map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("_:_37845","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37846","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37847","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37848","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37849","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37850","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37851","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37852","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37853","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37854","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_37868"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37845"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37846"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37847"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37848"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37849"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37850"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37851"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37852"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37853"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37854"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37868"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37868"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map38038map,
                            GeneratedMutableMap.map38039map,
                            GeneratedMutableMap.map38040map,
                            GeneratedMutableMap.map38041map,
                            GeneratedMutableMap.map38042map,
                            GeneratedMutableMap.map38043map,
                            GeneratedMutableMap.map38044map,
                            GeneratedMutableMap.map38045map,
                            GeneratedMutableMap.map38046map,
                            GeneratedMutableMap.map38047map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "#_37868",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map38028map,
                                GeneratedMutableMap.map38029map,
                                GeneratedMutableMap.map38030map,
                                GeneratedMutableMap.map38031map,
                                GeneratedMutableMap.map38032map,
                                GeneratedMutableMap.map38033map,
                                GeneratedMutableMap.map38034map,
                                GeneratedMutableMap.map38035map,
                                GeneratedMutableMap.map38036map,
                                GeneratedMutableMap.map38037map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37557map,
                                GeneratedMutableMap.map37558map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37868",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map38028map,
                            GeneratedMutableMap.map38029map,
                            GeneratedMutableMap.map38030map,
                            GeneratedMutableMap.map38031map,
                            GeneratedMutableMap.map38032map,
                            GeneratedMutableMap.map38033map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                                    POPJoinHashMap(
                                        dictionary,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("_:_37845","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37846","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37847","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37848","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37849","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37850","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37851","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37852","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37853","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37854","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37868","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37845"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37846"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37847"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37848"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37849"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37850"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37851"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37852"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37853"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37854"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37868",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                        false                                    )
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                                                                                                    }()

                                                                                    )

                                                                    )

                                                    )

                                    )
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37868",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map38028map,
                            GeneratedMutableMap.map38029map,
                            GeneratedMutableMap.map38030map,
                            GeneratedMutableMap.map38031map,
                            GeneratedMutableMap.map38032map,
                            GeneratedMutableMap.map38033map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                        LOPValues(listOf(
                                AOPVariable("#_37868"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map38038map,
                                GeneratedMutableMap.map38039map,
                                GeneratedMutableMap.map38040map,
                                GeneratedMutableMap.map38041map,
                                GeneratedMutableMap.map38042map,
                                GeneratedMutableMap.map38043map,
                                GeneratedMutableMap.map38044map,
                                GeneratedMutableMap.map38045map,
                                GeneratedMutableMap.map38046map,
                                GeneratedMutableMap.map38047map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37561map,
                                GeneratedMutableMap.map37562map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37868"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map38038map,
                            GeneratedMutableMap.map38039map,
                            GeneratedMutableMap.map38040map,
                            GeneratedMutableMap.map38041map,
                            GeneratedMutableMap.map38042map,
                            GeneratedMutableMap.map38043map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPJoin(
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("_:_37845","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37846","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37847","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37848","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37849","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37850","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37851","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37852","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37853","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37854","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_37868"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37845"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37846"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37847"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37848"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37849"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37850"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37851"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37852"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37853"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37854"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37868"),graphName,false)                                                    }()
,
                                        false                                    )
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
                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                    }()

                                                                                    )

                                                                    )

                                                    )

                                    )
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37868"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map38038map,
                            GeneratedMutableMap.map38039map,
                            GeneratedMutableMap.map38040map,
                            GeneratedMutableMap.map38041map,
                            GeneratedMutableMap.map38042map,
                            GeneratedMutableMap.map38043map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
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
