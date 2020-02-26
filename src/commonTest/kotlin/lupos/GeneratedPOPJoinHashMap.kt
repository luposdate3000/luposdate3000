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
                                GeneratedMutableMap.map2749map,
                                GeneratedMutableMap.map2750map,
                                GeneratedMutableMap.map2751map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "O2"
                            ), listOf(
                                GeneratedMutableMap.map2755map,
                                GeneratedMutableMap.map2756map,
                                GeneratedMutableMap.map2757map
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
                                GeneratedMutableMap.map5891map
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
                                GeneratedMutableMap.map5891map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-data-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5891map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6323map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6323map
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
                                GeneratedMutableMap.map6323map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6323map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6323map
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
                                GeneratedMutableMap.map6323map
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
                            ), listOf(
                                GeneratedMutableMap.map5891map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5891map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5891map
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
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map6708map,
                                GeneratedMutableMap.map6709map,
                                GeneratedMutableMap.map6710map,
                                GeneratedMutableMap.map6711map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7183map,
                                GeneratedMutableMap.map7184map,
                                GeneratedMutableMap.map7185map,
                                GeneratedMutableMap.map7186map
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
                            GeneratedMutableMap.map7187map,
                            GeneratedMutableMap.map7188map,
                            GeneratedMutableMap.map7189map,
                            GeneratedMutableMap.map7190map,
                            GeneratedMutableMap.map7191map,
                            GeneratedMutableMap.map7192map,
                            GeneratedMutableMap.map7193map,
                            GeneratedMutableMap.map7194map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map
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
                                GeneratedMutableMap.map6708map,
                                GeneratedMutableMap.map6709map,
                                GeneratedMutableMap.map6710map,
                                GeneratedMutableMap.map6711map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7711map,
                                GeneratedMutableMap.map7711map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7281map,
                            GeneratedMutableMap.map7282map,
                            GeneratedMutableMap.map7283map,
                            GeneratedMutableMap.map7284map,
                            GeneratedMutableMap.map7281map,
                            GeneratedMutableMap.map7282map,
                            GeneratedMutableMap.map7283map,
                            GeneratedMutableMap.map7284map
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
                                GeneratedMutableMap.map8489map,
                                GeneratedMutableMap.map8490map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8493map,
                                GeneratedMutableMap.map8494map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8497map,
                            GeneratedMutableMap.map8498map
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
                            GeneratedMutableMap.map8497map,
                            GeneratedMutableMap.map8498map
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
                                GeneratedMutableMap.map8491map,
                                GeneratedMutableMap.map8492map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("price")
                            ), listOf(
                                GeneratedMutableMap.map8495map,
                                GeneratedMutableMap.map8496map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ), listOf(
                            GeneratedMutableMap.map8499map,
                            GeneratedMutableMap.map8500map
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
                            GeneratedMutableMap.map8499map,
                            GeneratedMutableMap.map8500map
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
                                GeneratedMutableMap.map8501map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8497map,
                                GeneratedMutableMap.map8498map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8497map
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
                                GeneratedMutableMap.map8556map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8557map,
                                GeneratedMutableMap.map8558map,
                                GeneratedMutableMap.map8559map,
                                GeneratedMutableMap.map8560map,
                                GeneratedMutableMap.map8561map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8562map
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
                                GeneratedMutableMap.map8653map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8654map,
                                GeneratedMutableMap.map8655map,
                                GeneratedMutableMap.map8656map,
                                GeneratedMutableMap.map8657map,
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8667map
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
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8673map,
                                GeneratedMutableMap.map8674map,
                                GeneratedMutableMap.map8675map,
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map
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
                            GeneratedMutableMap.map8688map,
                            GeneratedMutableMap.map8689map,
                            GeneratedMutableMap.map8690map
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
                            GeneratedMutableMap.map8688map,
                            GeneratedMutableMap.map8689map,
                            GeneratedMutableMap.map8690map
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
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map
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
                            GeneratedMutableMap.map8691map,
                            GeneratedMutableMap.map8692map,
                            GeneratedMutableMap.map8693map,
                            GeneratedMutableMap.map8694map,
                            GeneratedMutableMap.map8695map,
                            GeneratedMutableMap.map8696map,
                            GeneratedMutableMap.map8697map,
                            GeneratedMutableMap.map8698map,
                            GeneratedMutableMap.map8699map,
                            GeneratedMutableMap.map8700map,
                            GeneratedMutableMap.map8701map,
                            GeneratedMutableMap.map8702map,
                            GeneratedMutableMap.map8703map
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
                            GeneratedMutableMap.map8691map,
                            GeneratedMutableMap.map8692map,
                            GeneratedMutableMap.map8693map,
                            GeneratedMutableMap.map8694map,
                            GeneratedMutableMap.map8695map,
                            GeneratedMutableMap.map8696map,
                            GeneratedMutableMap.map8697map,
                            GeneratedMutableMap.map8698map,
                            GeneratedMutableMap.map8699map,
                            GeneratedMutableMap.map8700map,
                            GeneratedMutableMap.map8701map,
                            GeneratedMutableMap.map8702map,
                            GeneratedMutableMap.map8703map
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
                                GeneratedMutableMap.map8792map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8654map,
                                GeneratedMutableMap.map8655map,
                                GeneratedMutableMap.map8656map,
                                GeneratedMutableMap.map8657map,
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8793map,
                            GeneratedMutableMap.map8667map,
                            GeneratedMutableMap.map8794map
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
                                GeneratedMutableMap.map8879map,
                                GeneratedMutableMap.map8880map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8654map,
                                GeneratedMutableMap.map8655map,
                                GeneratedMutableMap.map8656map,
                                GeneratedMutableMap.map8657map,
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8881map,
                            GeneratedMutableMap.map8882map,
                            GeneratedMutableMap.map8883map,
                            GeneratedMutableMap.map8883map,
                            GeneratedMutableMap.map8884map,
                            GeneratedMutableMap.map8794map
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
                                GeneratedMutableMap.map8944map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                            ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map8945map
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
                                GeneratedMutableMap.map9026map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8654map,
                                GeneratedMutableMap.map8655map,
                                GeneratedMutableMap.map8656map,
                                GeneratedMutableMap.map9027map,
                                GeneratedMutableMap.map9028map,
                                GeneratedMutableMap.map9029map,
                                GeneratedMutableMap.map9030map,
                                GeneratedMutableMap.map9031map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o2",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map9032map,
                            GeneratedMutableMap.map9033map,
                            GeneratedMutableMap.map9034map,
                            GeneratedMutableMap.map9035map,
                            GeneratedMutableMap.map9036map
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
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map9040map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map,
                                GeneratedMutableMap.map9041map,
                                GeneratedMutableMap.map9042map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map9037map,
                                GeneratedMutableMap.map9038map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9046map,
                            GeneratedMutableMap.map9047map,
                            GeneratedMutableMap.map9048map,
                            GeneratedMutableMap.map9049map,
                            GeneratedMutableMap.map9050map,
                            GeneratedMutableMap.map9051map,
                            GeneratedMutableMap.map9052map,
                            GeneratedMutableMap.map9053map
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
                            GeneratedMutableMap.map9046map,
                            GeneratedMutableMap.map9047map,
                            GeneratedMutableMap.map9048map,
                            GeneratedMutableMap.map9049map,
                            GeneratedMutableMap.map9050map,
                            GeneratedMutableMap.map9051map,
                            GeneratedMutableMap.map9052map,
                            GeneratedMutableMap.map9053map
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
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map9043map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map9044map,
                                GeneratedMutableMap.map9045map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map9039map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
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
                                GeneratedMutableMap.map9169map,
                                GeneratedMutableMap.map9170map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8497map,
                                GeneratedMutableMap.map8498map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8497map,
                            GeneratedMutableMap.map8498map
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
                                GeneratedMutableMap.map8501map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map8489map,
                                GeneratedMutableMap.map8490map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title"
                        ), listOf(
                            GeneratedMutableMap.map8489map
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
                                GeneratedMutableMap.map8489map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8493map,
                                GeneratedMutableMap.map8494map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8497map
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
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map10278map,
                                GeneratedMutableMap.map10279map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10460map,
                                GeneratedMutableMap.map10461map,
                                GeneratedMutableMap.map10462map,
                                GeneratedMutableMap.map10463map,
                                GeneratedMutableMap.map10464map,
                                GeneratedMutableMap.map10465map
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
                            GeneratedMutableMap.map10466map,
                            GeneratedMutableMap.map10467map,
                            GeneratedMutableMap.map10468map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map
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
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map10278map,
                                GeneratedMutableMap.map10279map,
                                GeneratedMutableMap.map10280map,
                                GeneratedMutableMap.map10281map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10460map,
                                GeneratedMutableMap.map10461map,
                                GeneratedMutableMap.map10462map,
                                GeneratedMutableMap.map10463map,
                                GeneratedMutableMap.map10464map,
                                GeneratedMutableMap.map10465map
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
                            GeneratedMutableMap.map10466map,
                            GeneratedMutableMap.map10467map,
                            GeneratedMutableMap.map10468map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map
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
                            GeneratedMutableMap.map10466map,
                            GeneratedMutableMap.map10467map,
                            GeneratedMutableMap.map10468map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map
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
                                GeneratedMutableMap.map10358map,
                                GeneratedMutableMap.map10359map,
                                GeneratedMutableMap.map10360map,
                                GeneratedMutableMap.map10361map,
                                GeneratedMutableMap.map10362map,
                                GeneratedMutableMap.map10363map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10358map,
                                GeneratedMutableMap.map10359map,
                                GeneratedMutableMap.map10360map,
                                GeneratedMutableMap.map10361map,
                                GeneratedMutableMap.map10362map,
                                GeneratedMutableMap.map10363map
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
                            GeneratedMutableMap.map10596map,
                            GeneratedMutableMap.map10597map,
                            GeneratedMutableMap.map10598map,
                            GeneratedMutableMap.map10599map,
                            GeneratedMutableMap.map10600map,
                            GeneratedMutableMap.map10601map
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
                            GeneratedMutableMap.map10596map,
                            GeneratedMutableMap.map10597map,
                            GeneratedMutableMap.map10598map,
                            GeneratedMutableMap.map10599map,
                            GeneratedMutableMap.map10600map,
                            GeneratedMutableMap.map10601map
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
                                GeneratedMutableMap.map5891map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map
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
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map,
                                GeneratedMutableMap.map11352map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map,
                            GeneratedMutableMap.map11352map
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
                                GeneratedMutableMap.map11512map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11240map,
                                GeneratedMutableMap.map11241map,
                                GeneratedMutableMap.map11242map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11240map,
                            GeneratedMutableMap.map11241map
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
                            GeneratedMutableMap.map11240map,
                            GeneratedMutableMap.map11241map
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
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                            ), listOf(
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map11243map,
                                GeneratedMutableMap.map9039map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("b")
                        ), listOf(
                            GeneratedMutableMap.map8573map,
                            GeneratedMutableMap.map11243map
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
                            GeneratedMutableMap.map8573map,
                            GeneratedMutableMap.map11243map
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
                                GeneratedMutableMap.map11512map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map11746map,
                                GeneratedMutableMap.map11747map,
                                GeneratedMutableMap.map11748map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map11746map,
                            GeneratedMutableMap.map11747map
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
                            GeneratedMutableMap.map11746map,
                            GeneratedMutableMap.map11747map
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
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map8573map,
                                GeneratedMutableMap.map11243map,
                                GeneratedMutableMap.map9039map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map8573map,
                            GeneratedMutableMap.map11243map
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
                            GeneratedMutableMap.map8573map,
                            GeneratedMutableMap.map11243map
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
                                GeneratedMutableMap.map11512map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map11748map
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
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map9039map
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
                                GeneratedMutableMap.map12896map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8566map,
                            GeneratedMutableMap.map8567map
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
                            GeneratedMutableMap.map8566map,
                            GeneratedMutableMap.map8567map
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
                                GeneratedMutableMap.map12897map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8571map,
                            GeneratedMutableMap.map8572map
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
                            GeneratedMutableMap.map8571map,
                            GeneratedMutableMap.map8572map
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
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map
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
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map
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
                                GeneratedMutableMap.map13297map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map10992map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map10993map,
                                GeneratedMutableMap.map10994map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10993map,
                            GeneratedMutableMap.map10994map
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
                            GeneratedMutableMap.map10993map,
                            GeneratedMutableMap.map10994map
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
                                GeneratedMutableMap.map13298map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map9043map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map10995map,
                                GeneratedMutableMap.map10996map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10995map,
                            GeneratedMutableMap.map10996map
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
                            GeneratedMutableMap.map10995map,
                            GeneratedMutableMap.map10996map
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
                                GeneratedMutableMap.map12896map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map10992map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map10993map,
                                GeneratedMutableMap.map10994map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10992map,
                            GeneratedMutableMap.map8566map,
                            GeneratedMutableMap.map8567map
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
                            GeneratedMutableMap.map10992map,
                            GeneratedMutableMap.map8566map,
                            GeneratedMutableMap.map8567map
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
                                GeneratedMutableMap.map12897map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map9043map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map10995map,
                                GeneratedMutableMap.map10996map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9043map,
                            GeneratedMutableMap.map8571map,
                            GeneratedMutableMap.map8572map
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
                            GeneratedMutableMap.map9043map,
                            GeneratedMutableMap.map8571map,
                            GeneratedMutableMap.map8572map
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
                                GeneratedMutableMap.map10997map,
                                GeneratedMutableMap.map10993map,
                                GeneratedMutableMap.map10994map,
                                GeneratedMutableMap.map10998map,
                                GeneratedMutableMap.map10999map
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
                                GeneratedMutableMap.map11000map,
                                GeneratedMutableMap.map10995map,
                                GeneratedMutableMap.map10996map,
                                GeneratedMutableMap.map11001map,
                                GeneratedMutableMap.map11002map
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
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map10992map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map,
                                GeneratedMutableMap.map10993map,
                                GeneratedMutableMap.map10994map
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
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map9043map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map10995map,
                                GeneratedMutableMap.map10996map
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
                                GeneratedMutableMap.map13297map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10997map,
                                GeneratedMutableMap.map10993map,
                                GeneratedMutableMap.map10994map,
                                GeneratedMutableMap.map10998map,
                                GeneratedMutableMap.map10999map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10997map,
                            GeneratedMutableMap.map10993map,
                            GeneratedMutableMap.map10994map
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
                                GeneratedMutableMap.map14048map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8563map,
                                GeneratedMutableMap.map8564map,
                                GeneratedMutableMap.map8565map,
                                GeneratedMutableMap.map8566map,
                                GeneratedMutableMap.map8567map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8563map,
                            GeneratedMutableMap.map8564map,
                            GeneratedMutableMap.map8565map
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
                            GeneratedMutableMap.map8563map,
                            GeneratedMutableMap.map8564map,
                            GeneratedMutableMap.map8565map
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
                                GeneratedMutableMap.map11513map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8568map,
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8568map,
                            GeneratedMutableMap.map8569map,
                            GeneratedMutableMap.map8570map
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
                            GeneratedMutableMap.map8568map,
                            GeneratedMutableMap.map8569map,
                            GeneratedMutableMap.map8570map
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
                                GeneratedMutableMap.map15881map,
                                GeneratedMutableMap.map15882map,
                                GeneratedMutableMap.map15883map,
                                GeneratedMutableMap.map15884map,
                                GeneratedMutableMap.map15885map,
                                GeneratedMutableMap.map15886map,
                                GeneratedMutableMap.map15887map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map15880map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map15884map
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
                                GeneratedMutableMap.map16149map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16149map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16149map
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
                                GeneratedMutableMap.map16343map,
                                GeneratedMutableMap.map16344map,
                                GeneratedMutableMap.map16345map,
                                GeneratedMutableMap.map16346map,
                                GeneratedMutableMap.map15881map,
                                GeneratedMutableMap.map15882map,
                                GeneratedMutableMap.map15883map,
                                GeneratedMutableMap.map16347map,
                                GeneratedMutableMap.map16348map
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
                                GeneratedMutableMap.map16349map,
                                GeneratedMutableMap.map16350map,
                                GeneratedMutableMap.map16351map,
                                GeneratedMutableMap.map16352map,
                                GeneratedMutableMap.map16353map,
                                GeneratedMutableMap.map16354map,
                                GeneratedMutableMap.map16355map,
                                GeneratedMutableMap.map16356map,
                                GeneratedMutableMap.map16357map
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
                                GeneratedMutableMap.map16421map,
                                GeneratedMutableMap.map16422map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16417map,
                                GeneratedMutableMap.map16418map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16421map,
                            GeneratedMutableMap.map16422map
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
                            GeneratedMutableMap.map16421map,
                            GeneratedMutableMap.map16422map
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
                                GeneratedMutableMap.map16423map,
                                GeneratedMutableMap.map16424map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map16419map,
                                GeneratedMutableMap.map16420map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map16423map,
                            GeneratedMutableMap.map16424map
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
                            GeneratedMutableMap.map16423map,
                            GeneratedMutableMap.map16424map
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
                                GeneratedMutableMap.map16634map,
                                GeneratedMutableMap.map16635map,
                                GeneratedMutableMap.map16636map,
                                GeneratedMutableMap.map16637map,
                                GeneratedMutableMap.map16638map,
                                GeneratedMutableMap.map16639map,
                                GeneratedMutableMap.map16640map,
                                GeneratedMutableMap.map16641map,
                                GeneratedMutableMap.map16642map,
                                GeneratedMutableMap.map16643map,
                                GeneratedMutableMap.map16644map,
                                GeneratedMutableMap.map16645map,
                                GeneratedMutableMap.map16646map,
                                GeneratedMutableMap.map16647map,
                                GeneratedMutableMap.map16648map,
                                GeneratedMutableMap.map16649map,
                                GeneratedMutableMap.map16650map,
                                GeneratedMutableMap.map16651map,
                                GeneratedMutableMap.map16652map,
                                GeneratedMutableMap.map16653map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16529"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16529"
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
                                "#_16529",
                                "#_16534"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16654map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16529",
                            "#_16534",
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
                                GeneratedMutableMap.map16790map,
                                GeneratedMutableMap.map16791map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16675"
                            ), listOf(
                                GeneratedMutableMap.map16783map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675"
                        ), listOf(
                            GeneratedMutableMap.map16792map,
                            GeneratedMutableMap.map16793map
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
                                "#_16675"
                            ), listOf(
                                GeneratedMutableMap.map16792map,
                                GeneratedMutableMap.map16793map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16675"
                            ), listOf(
                                GeneratedMutableMap.map16783map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675"
                        ), listOf(
                            GeneratedMutableMap.map16792map,
                            GeneratedMutableMap.map16793map
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
                                "#_16675"
                            ), listOf(
                                GeneratedMutableMap.map16792map,
                                GeneratedMutableMap.map16793map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16684"
                            ), listOf(
                                GeneratedMutableMap.map16784map,
                                GeneratedMutableMap.map16785map,
                                GeneratedMutableMap.map16786map,
                                GeneratedMutableMap.map16787map,
                                GeneratedMutableMap.map16788map,
                                GeneratedMutableMap.map16789map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675",
                            "#_16684"
                        ), listOf(
                            GeneratedMutableMap.map16815map,
                            GeneratedMutableMap.map16816map,
                            GeneratedMutableMap.map16817map,
                            GeneratedMutableMap.map16818map,
                            GeneratedMutableMap.map16819map,
                            GeneratedMutableMap.map16820map,
                            GeneratedMutableMap.map16821map,
                            GeneratedMutableMap.map16822map,
                            GeneratedMutableMap.map16823map,
                            GeneratedMutableMap.map16824map,
                            GeneratedMutableMap.map16825map,
                            GeneratedMutableMap.map16826map
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
                                "#_16675",
                                "#_16684"
                            ), listOf(
                                GeneratedMutableMap.map16815map,
                                GeneratedMutableMap.map16816map,
                                GeneratedMutableMap.map16817map,
                                GeneratedMutableMap.map16818map,
                                GeneratedMutableMap.map16819map,
                                GeneratedMutableMap.map16820map,
                                GeneratedMutableMap.map16821map,
                                GeneratedMutableMap.map16822map,
                                GeneratedMutableMap.map16823map,
                                GeneratedMutableMap.map16824map,
                                GeneratedMutableMap.map16825map,
                                GeneratedMutableMap.map16826map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16684"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675",
                            "#_16684"
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
                                "#_16675",
                                "#_16684"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16675",
                                "#_16684"
                            ), listOf(
                                GeneratedMutableMap.map16794map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675",
                            "#_16684"
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
                                "#_16675",
                                "#_16684"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16675"
                            ), listOf(
                                GeneratedMutableMap.map16795map,
                                GeneratedMutableMap.map16796map,
                                GeneratedMutableMap.map16797map,
                                GeneratedMutableMap.map16798map,
                                GeneratedMutableMap.map16799map,
                                GeneratedMutableMap.map16800map,
                                GeneratedMutableMap.map16801map,
                                GeneratedMutableMap.map16802map,
                                GeneratedMutableMap.map16803map,
                                GeneratedMutableMap.map16804map,
                                GeneratedMutableMap.map16805map,
                                GeneratedMutableMap.map16806map,
                                GeneratedMutableMap.map16807map,
                                GeneratedMutableMap.map16808map,
                                GeneratedMutableMap.map16809map,
                                GeneratedMutableMap.map16810map,
                                GeneratedMutableMap.map16811map,
                                GeneratedMutableMap.map16812map,
                                GeneratedMutableMap.map16813map,
                                GeneratedMutableMap.map16814map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16675",
                            "#_16684"
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
                                GeneratedMutableMap.map16901map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16902map,
                                GeneratedMutableMap.map16903map,
                                GeneratedMutableMap.map16904map,
                                GeneratedMutableMap.map16905map,
                                GeneratedMutableMap.map16906map,
                                GeneratedMutableMap.map16907map,
                                GeneratedMutableMap.map16908map,
                                GeneratedMutableMap.map16909map,
                                GeneratedMutableMap.map16910map,
                                GeneratedMutableMap.map16911map,
                                GeneratedMutableMap.map16912map,
                                GeneratedMutableMap.map16913map,
                                GeneratedMutableMap.map16914map,
                                GeneratedMutableMap.map16915map,
                                GeneratedMutableMap.map16916map,
                                GeneratedMutableMap.map16917map,
                                GeneratedMutableMap.map16918map,
                                GeneratedMutableMap.map16919map,
                                GeneratedMutableMap.map16920map,
                                GeneratedMutableMap.map16921map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16902map,
                            GeneratedMutableMap.map16903map
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
                                GeneratedMutableMap.map16902map,
                                GeneratedMutableMap.map16903map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16466map
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
                                GeneratedMutableMap.map16993map,
                                GeneratedMutableMap.map16994map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map16985map,
                                GeneratedMutableMap.map16986map,
                                GeneratedMutableMap.map16987map,
                                GeneratedMutableMap.map16988map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p",
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map16985map
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
                            GeneratedMutableMap.map16985map
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
                                GeneratedMutableMap.map16995map,
                                GeneratedMutableMap.map16996map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map16989map,
                                GeneratedMutableMap.map16990map,
                                GeneratedMutableMap.map16991map,
                                GeneratedMutableMap.map16992map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map16989map
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
                            GeneratedMutableMap.map16989map
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
                                GeneratedMutableMap.map17144map,
                                GeneratedMutableMap.map6708map,
                                GeneratedMutableMap.map6709map,
                                GeneratedMutableMap.map6710map,
                                GeneratedMutableMap.map6711map,
                                GeneratedMutableMap.map17145map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17142map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6708map,
                            GeneratedMutableMap.map6709map,
                            GeneratedMutableMap.map6710map,
                            GeneratedMutableMap.map6711map
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
                            GeneratedMutableMap.map6708map,
                            GeneratedMutableMap.map6709map,
                            GeneratedMutableMap.map6710map,
                            GeneratedMutableMap.map6711map
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
                                GeneratedMutableMap.map17146map,
                                GeneratedMutableMap.map6712map,
                                GeneratedMutableMap.map6713map,
                                GeneratedMutableMap.map6714map,
                                GeneratedMutableMap.map6715map,
                                GeneratedMutableMap.map17147map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p")
                            ), listOf(
                                GeneratedMutableMap.map17143map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map6712map,
                            GeneratedMutableMap.map6713map,
                            GeneratedMutableMap.map6714map,
                            GeneratedMutableMap.map6715map
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
                            GeneratedMutableMap.map6712map,
                            GeneratedMutableMap.map6713map,
                            GeneratedMutableMap.map6714map,
                            GeneratedMutableMap.map6715map
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
                                GeneratedMutableMap.map17144map,
                                GeneratedMutableMap.map6708map,
                                GeneratedMutableMap.map6709map,
                                GeneratedMutableMap.map6710map,
                                GeneratedMutableMap.map6711map,
                                GeneratedMutableMap.map17145map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17142map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map6708map,
                            GeneratedMutableMap.map6709map,
                            GeneratedMutableMap.map6710map,
                            GeneratedMutableMap.map6711map
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
                                GeneratedMutableMap.map6708map,
                                GeneratedMutableMap.map6709map,
                                GeneratedMutableMap.map6710map,
                                GeneratedMutableMap.map6711map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17635map,
                                GeneratedMutableMap.map7183map,
                                GeneratedMutableMap.map7184map,
                                GeneratedMutableMap.map7185map,
                                GeneratedMutableMap.map7186map,
                                GeneratedMutableMap.map17636map
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
                            GeneratedMutableMap.map17637map,
                            GeneratedMutableMap.map17638map,
                            GeneratedMutableMap.map17639map,
                            GeneratedMutableMap.map17640map,
                            GeneratedMutableMap.map7187map,
                            GeneratedMutableMap.map7188map,
                            GeneratedMutableMap.map7189map,
                            GeneratedMutableMap.map7190map,
                            GeneratedMutableMap.map7191map,
                            GeneratedMutableMap.map7192map,
                            GeneratedMutableMap.map7193map,
                            GeneratedMutableMap.map7194map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map,
                            GeneratedMutableMap.map17641map,
                            GeneratedMutableMap.map17642map,
                            GeneratedMutableMap.map17643map,
                            GeneratedMutableMap.map17644map
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
                                GeneratedMutableMap.map17637map,
                                GeneratedMutableMap.map17638map,
                                GeneratedMutableMap.map17639map,
                                GeneratedMutableMap.map17640map,
                                GeneratedMutableMap.map7187map,
                                GeneratedMutableMap.map7188map,
                                GeneratedMutableMap.map7189map,
                                GeneratedMutableMap.map7190map,
                                GeneratedMutableMap.map7191map,
                                GeneratedMutableMap.map7192map,
                                GeneratedMutableMap.map7193map,
                                GeneratedMutableMap.map7194map,
                                GeneratedMutableMap.map7195map,
                                GeneratedMutableMap.map7196map,
                                GeneratedMutableMap.map7197map,
                                GeneratedMutableMap.map7198map,
                                GeneratedMutableMap.map7199map,
                                GeneratedMutableMap.map7200map,
                                GeneratedMutableMap.map7201map,
                                GeneratedMutableMap.map7202map,
                                GeneratedMutableMap.map17641map,
                                GeneratedMutableMap.map17642map,
                                GeneratedMutableMap.map17643map,
                                GeneratedMutableMap.map17644map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17634map
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
                            GeneratedMutableMap.map7187map,
                            GeneratedMutableMap.map7188map,
                            GeneratedMutableMap.map7189map,
                            GeneratedMutableMap.map7190map,
                            GeneratedMutableMap.map7191map,
                            GeneratedMutableMap.map7192map,
                            GeneratedMutableMap.map7193map,
                            GeneratedMutableMap.map7194map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map7197map,
                            GeneratedMutableMap.map7198map,
                            GeneratedMutableMap.map7199map,
                            GeneratedMutableMap.map7200map,
                            GeneratedMutableMap.map7201map,
                            GeneratedMutableMap.map7202map
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
                                GeneratedMutableMap.map15881map,
                                GeneratedMutableMap.map15883map,
                                GeneratedMutableMap.map15884map,
                                GeneratedMutableMap.map16347map
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
                                GeneratedMutableMap.map16343map,
                                GeneratedMutableMap.map16345map,
                                GeneratedMutableMap.map15881map,
                                GeneratedMutableMap.map15882map,
                                GeneratedMutableMap.map15883map,
                                GeneratedMutableMap.map15884map,
                                GeneratedMutableMap.map18617map,
                                GeneratedMutableMap.map15885map,
                                GeneratedMutableMap.map18618map,
                                GeneratedMutableMap.map18619map,
                                GeneratedMutableMap.map16348map
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
                                GeneratedMutableMap.map18620map
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
                                GeneratedMutableMap.map18693map,
                                GeneratedMutableMap.map18694map,
                                GeneratedMutableMap.map18695map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                            ), listOf(
                                GeneratedMutableMap.map18698map,
                                GeneratedMutableMap.map18699map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1"
                        ), listOf(
                            GeneratedMutableMap.map18698map,
                            GeneratedMutableMap.map18699map
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
                            GeneratedMutableMap.map18698map,
                            GeneratedMutableMap.map18699map
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
                                GeneratedMutableMap.map18696map,
                                GeneratedMutableMap.map17038map,
                                GeneratedMutableMap.map18697map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                            ), listOf(
                                GeneratedMutableMap.map18700map,
                                GeneratedMutableMap.map18701map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1")
                        ), listOf(
                            GeneratedMutableMap.map18700map,
                            GeneratedMutableMap.map18701map
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
                            GeneratedMutableMap.map18700map,
                            GeneratedMutableMap.map18701map
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
                                GeneratedMutableMap.map18698map,
                                GeneratedMutableMap.map18699map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y2"
                            ), listOf(
                                GeneratedMutableMap.map18702map,
                                GeneratedMutableMap.map18703map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1",
                            "Y2"
                        ), listOf(
                            GeneratedMutableMap.map18706map,
                            GeneratedMutableMap.map18707map
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
                            GeneratedMutableMap.map18706map,
                            GeneratedMutableMap.map18707map
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
                                GeneratedMutableMap.map18700map,
                                GeneratedMutableMap.map18701map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y2")
                            ), listOf(
                                GeneratedMutableMap.map18704map,
                                GeneratedMutableMap.map18705map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ), listOf(
                            GeneratedMutableMap.map18708map,
                            GeneratedMutableMap.map18709map
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
                            GeneratedMutableMap.map18708map,
                            GeneratedMutableMap.map18709map
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
                                GeneratedMutableMap.map18842map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map18839map,
                                GeneratedMutableMap.map18840map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map18839map
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
                                GeneratedMutableMap.map18839map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map18841map
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
                                GeneratedMutableMap.map18842map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map18928map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map18931map
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
                            GeneratedMutableMap.map18931map
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
                                GeneratedMutableMap.map18930map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map18929map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map18932map
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
                            GeneratedMutableMap.map18932map
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
                                GeneratedMutableMap.map18931map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map18926map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map18931map
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
                            GeneratedMutableMap.map18931map
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
                                GeneratedMutableMap.map18932map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("Y"),
                                AOPVariable("#aa")
                            ), listOf(
                                GeneratedMutableMap.map18927map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map18932map
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
                            GeneratedMutableMap.map18932map
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
                                GeneratedMutableMap.map18931map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                            ), listOf(
                                GeneratedMutableMap.map18933map,
                                GeneratedMutableMap.map18934map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map18937map
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
                            GeneratedMutableMap.map18937map
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
                                GeneratedMutableMap.map18932map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("Z")
                            ), listOf(
                                GeneratedMutableMap.map18935map,
                                GeneratedMutableMap.map18936map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map18938map
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
                            GeneratedMutableMap.map18938map
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
                                GeneratedMutableMap.map18995map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map18997map,
                                GeneratedMutableMap.map18998map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map18999map
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
                            GeneratedMutableMap.map18999map
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
                                GeneratedMutableMap.map18996map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map18935map,
                                GeneratedMutableMap.map18936map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19000map
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
                            GeneratedMutableMap.map19000map
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
                                GeneratedMutableMap.map19060map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19062map,
                                GeneratedMutableMap.map19063map,
                                GeneratedMutableMap.map19064map,
                                GeneratedMutableMap.map19065map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19066map,
                            GeneratedMutableMap.map19067map,
                            GeneratedMutableMap.map19068map
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
                            GeneratedMutableMap.map19066map,
                            GeneratedMutableMap.map19067map,
                            GeneratedMutableMap.map19068map
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
                                GeneratedMutableMap.map19061map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19069map,
                                GeneratedMutableMap.map19070map,
                                GeneratedMutableMap.map19071map,
                                GeneratedMutableMap.map19072map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19073map,
                            GeneratedMutableMap.map19074map,
                            GeneratedMutableMap.map19075map
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
                            GeneratedMutableMap.map19073map,
                            GeneratedMutableMap.map19074map,
                            GeneratedMutableMap.map19075map
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
                                GeneratedMutableMap.map19169map
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
                                GeneratedMutableMap.map19170map,
                                GeneratedMutableMap.map19171map,
                                GeneratedMutableMap.map19172map,
                                GeneratedMutableMap.map19173map
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
                                GeneratedMutableMap.map19339map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19338map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19343map
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
                                GeneratedMutableMap.map19343map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19340map,
                                GeneratedMutableMap.map19341map,
                                GeneratedMutableMap.map19342map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19343map
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
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19526map,
                                GeneratedMutableMap.map19527map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19526map,
                                GeneratedMutableMap.map19527map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19460"
                        ), listOf(
                            GeneratedMutableMap.map19526map,
                            GeneratedMutableMap.map19527map
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
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19526map,
                                GeneratedMutableMap.map19527map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19526map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19460"
                        ), listOf(
                            GeneratedMutableMap.map19526map
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
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19526map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19460"
                            ), listOf(
                                GeneratedMutableMap.map19528map,
                                GeneratedMutableMap.map19529map,
                                GeneratedMutableMap.map19530map,
                                GeneratedMutableMap.map19531map,
                                GeneratedMutableMap.map19532map,
                                GeneratedMutableMap.map19533map,
                                GeneratedMutableMap.map19534map,
                                GeneratedMutableMap.map19535map,
                                GeneratedMutableMap.map19536map,
                                GeneratedMutableMap.map19537map,
                                GeneratedMutableMap.map19538map,
                                GeneratedMutableMap.map19539map,
                                GeneratedMutableMap.map19540map,
                                GeneratedMutableMap.map19541map,
                                GeneratedMutableMap.map19542map,
                                GeneratedMutableMap.map19543map,
                                GeneratedMutableMap.map19544map,
                                GeneratedMutableMap.map19545map,
                                GeneratedMutableMap.map19546map,
                                GeneratedMutableMap.map19547map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19460",
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
                                "#_19572"
                            ), listOf(
                                GeneratedMutableMap.map19643map,
                                GeneratedMutableMap.map19644map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19572"
                            ), listOf(
                                GeneratedMutableMap.map19643map,
                                GeneratedMutableMap.map19644map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19572"
                        ), listOf(
                            GeneratedMutableMap.map19643map,
                            GeneratedMutableMap.map19644map
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
                                "#_19572"
                            ), listOf(
                                GeneratedMutableMap.map19643map,
                                GeneratedMutableMap.map19644map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19572"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19572"
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
                                "#_19572"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19572"
                            ), listOf(
                                GeneratedMutableMap.map19645map,
                                GeneratedMutableMap.map19646map,
                                GeneratedMutableMap.map19647map,
                                GeneratedMutableMap.map19648map,
                                GeneratedMutableMap.map19649map,
                                GeneratedMutableMap.map19650map,
                                GeneratedMutableMap.map19651map,
                                GeneratedMutableMap.map19652map,
                                GeneratedMutableMap.map19653map,
                                GeneratedMutableMap.map19654map,
                                GeneratedMutableMap.map19655map,
                                GeneratedMutableMap.map19656map,
                                GeneratedMutableMap.map19657map,
                                GeneratedMutableMap.map19658map,
                                GeneratedMutableMap.map19659map,
                                GeneratedMutableMap.map19660map,
                                GeneratedMutableMap.map19661map,
                                GeneratedMutableMap.map19662map,
                                GeneratedMutableMap.map19663map,
                                GeneratedMutableMap.map19664map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19572",
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
                                "#_19686"
                            ), listOf(
                                GeneratedMutableMap.map19772map,
                                GeneratedMutableMap.map19773map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19686"
                            ), listOf(
                                GeneratedMutableMap.map19772map,
                                GeneratedMutableMap.map19773map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19686"
                        ), listOf(
                            GeneratedMutableMap.map19772map,
                            GeneratedMutableMap.map19773map
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
                                "#_19686"
                            ), listOf(
                                GeneratedMutableMap.map19772map,
                                GeneratedMutableMap.map19773map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19686"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19686"
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
                                "#_19686"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19686"
                            ), listOf(
                                GeneratedMutableMap.map19752map,
                                GeneratedMutableMap.map19753map,
                                GeneratedMutableMap.map19754map,
                                GeneratedMutableMap.map19755map,
                                GeneratedMutableMap.map19756map,
                                GeneratedMutableMap.map19757map,
                                GeneratedMutableMap.map19758map,
                                GeneratedMutableMap.map19759map,
                                GeneratedMutableMap.map19760map,
                                GeneratedMutableMap.map19761map,
                                GeneratedMutableMap.map19762map,
                                GeneratedMutableMap.map19763map,
                                GeneratedMutableMap.map19764map,
                                GeneratedMutableMap.map19765map,
                                GeneratedMutableMap.map19766map,
                                GeneratedMutableMap.map19767map,
                                GeneratedMutableMap.map19768map,
                                GeneratedMutableMap.map19769map,
                                GeneratedMutableMap.map19770map,
                                GeneratedMutableMap.map19771map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19686",
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
                                "#_19796"
                            ), listOf(
                                GeneratedMutableMap.map19878map,
                                GeneratedMutableMap.map19879map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19796"
                            ), listOf(
                                GeneratedMutableMap.map19878map,
                                GeneratedMutableMap.map19879map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19796"
                        ), listOf(
                            GeneratedMutableMap.map19878map,
                            GeneratedMutableMap.map19879map
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
                                "#_19796"
                            ), listOf(
                                GeneratedMutableMap.map19878map,
                                GeneratedMutableMap.map19879map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19796"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19796"
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
                                "#_19796"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19796"
                            ), listOf(
                                GeneratedMutableMap.map19880map,
                                GeneratedMutableMap.map19881map,
                                GeneratedMutableMap.map19882map,
                                GeneratedMutableMap.map19883map,
                                GeneratedMutableMap.map19884map,
                                GeneratedMutableMap.map19885map,
                                GeneratedMutableMap.map19886map,
                                GeneratedMutableMap.map19887map,
                                GeneratedMutableMap.map19888map,
                                GeneratedMutableMap.map19889map,
                                GeneratedMutableMap.map19890map,
                                GeneratedMutableMap.map19891map,
                                GeneratedMutableMap.map19892map,
                                GeneratedMutableMap.map19893map,
                                GeneratedMutableMap.map19894map,
                                GeneratedMutableMap.map19895map,
                                GeneratedMutableMap.map19896map,
                                GeneratedMutableMap.map19897map,
                                GeneratedMutableMap.map19898map,
                                GeneratedMutableMap.map19899map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19796",
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
                                "#_19922"
                            ), listOf(
                                GeneratedMutableMap.map20004map,
                                GeneratedMutableMap.map20005map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19922"
                            ), listOf(
                                GeneratedMutableMap.map20004map,
                                GeneratedMutableMap.map20005map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19922"
                        ), listOf(
                            GeneratedMutableMap.map20004map,
                            GeneratedMutableMap.map20005map
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
                                "#_19922"
                            ), listOf(
                                GeneratedMutableMap.map20004map,
                                GeneratedMutableMap.map20005map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19922"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19922"
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
                                "#_19922"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19922"
                            ), listOf(
                                GeneratedMutableMap.map20006map,
                                GeneratedMutableMap.map20007map,
                                GeneratedMutableMap.map20008map,
                                GeneratedMutableMap.map20009map,
                                GeneratedMutableMap.map20010map,
                                GeneratedMutableMap.map20011map,
                                GeneratedMutableMap.map20012map,
                                GeneratedMutableMap.map20013map,
                                GeneratedMutableMap.map20014map,
                                GeneratedMutableMap.map20015map,
                                GeneratedMutableMap.map20016map,
                                GeneratedMutableMap.map20017map,
                                GeneratedMutableMap.map20018map,
                                GeneratedMutableMap.map20019map,
                                GeneratedMutableMap.map20020map,
                                GeneratedMutableMap.map20021map,
                                GeneratedMutableMap.map20022map,
                                GeneratedMutableMap.map20023map,
                                GeneratedMutableMap.map20024map,
                                GeneratedMutableMap.map20025map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19922",
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
                                "#_20048"
                            ), listOf(
                                GeneratedMutableMap.map20130map,
                                GeneratedMutableMap.map20131map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20048"
                            ), listOf(
                                GeneratedMutableMap.map20130map,
                                GeneratedMutableMap.map20131map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20048"
                        ), listOf(
                            GeneratedMutableMap.map20130map,
                            GeneratedMutableMap.map20131map
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
                                "#_20048"
                            ), listOf(
                                GeneratedMutableMap.map20130map,
                                GeneratedMutableMap.map20131map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20048"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20048"
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
                                "#_20048"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20048"
                            ), listOf(
                                GeneratedMutableMap.map20132map,
                                GeneratedMutableMap.map20133map,
                                GeneratedMutableMap.map20134map,
                                GeneratedMutableMap.map20135map,
                                GeneratedMutableMap.map20136map,
                                GeneratedMutableMap.map20137map,
                                GeneratedMutableMap.map20138map,
                                GeneratedMutableMap.map20139map,
                                GeneratedMutableMap.map20140map,
                                GeneratedMutableMap.map20141map,
                                GeneratedMutableMap.map20142map,
                                GeneratedMutableMap.map20143map,
                                GeneratedMutableMap.map20144map,
                                GeneratedMutableMap.map20145map,
                                GeneratedMutableMap.map20146map,
                                GeneratedMutableMap.map20147map,
                                GeneratedMutableMap.map20148map,
                                GeneratedMutableMap.map20149map,
                                GeneratedMutableMap.map20150map,
                                GeneratedMutableMap.map20151map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20048",
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
                                "#_20175"
                            ), listOf(
                                GeneratedMutableMap.map20243map,
                                GeneratedMutableMap.map20244map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20175"
                            ), listOf(
                                GeneratedMutableMap.map20243map,
                                GeneratedMutableMap.map20244map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20175"
                        ), listOf(
                            GeneratedMutableMap.map20243map,
                            GeneratedMutableMap.map20244map
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
                                "#_20175"
                            ), listOf(
                                GeneratedMutableMap.map20243map,
                                GeneratedMutableMap.map20244map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20175"
                            ), listOf(
                                GeneratedMutableMap.map20243map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20175"
                        ), listOf(
                            GeneratedMutableMap.map20243map
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
                                "#_20175"
                            ), listOf(
                                GeneratedMutableMap.map20243map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_20175"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20175",
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
                                GeneratedMutableMap.map20349map,
                                GeneratedMutableMap.map20350map
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
                                GeneratedMutableMap.map20349map
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
                                "#_20367",
                                "#_20372",
                                "#_20364"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20364"
                            ), listOf(
                                GeneratedMutableMap.map20456map,
                                GeneratedMutableMap.map20457map,
                                GeneratedMutableMap.map20458map,
                                GeneratedMutableMap.map20459map,
                                GeneratedMutableMap.map20460map,
                                GeneratedMutableMap.map20461map,
                                GeneratedMutableMap.map20462map,
                                GeneratedMutableMap.map20463map,
                                GeneratedMutableMap.map20464map,
                                GeneratedMutableMap.map20465map,
                                GeneratedMutableMap.map20466map,
                                GeneratedMutableMap.map20467map,
                                GeneratedMutableMap.map20468map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20367",
                            "#_20372",
                            "#_20364",
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
                                "#_20485",
                                "#_20489",
                                "#_20499",
                                "#_20482"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20482"
                            ), listOf(
                                GeneratedMutableMap.map20613map,
                                GeneratedMutableMap.map20614map,
                                GeneratedMutableMap.map20615map,
                                GeneratedMutableMap.map20616map,
                                GeneratedMutableMap.map20617map,
                                GeneratedMutableMap.map20618map,
                                GeneratedMutableMap.map20619map,
                                GeneratedMutableMap.map20620map,
                                GeneratedMutableMap.map20621map,
                                GeneratedMutableMap.map20622map,
                                GeneratedMutableMap.map20623map,
                                GeneratedMutableMap.map20624map,
                                GeneratedMutableMap.map20625map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20485",
                            "#_20489",
                            "#_20499",
                            "#_20482",
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
                                "#_20639",
                                "#_20650",
                                "#_20655",
                                "#_20647"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20639"
                            ), listOf(
                                GeneratedMutableMap.map20770map,
                                GeneratedMutableMap.map20771map,
                                GeneratedMutableMap.map20772map,
                                GeneratedMutableMap.map20773map,
                                GeneratedMutableMap.map20774map,
                                GeneratedMutableMap.map20775map,
                                GeneratedMutableMap.map20776map,
                                GeneratedMutableMap.map20777map,
                                GeneratedMutableMap.map20778map,
                                GeneratedMutableMap.map20779map,
                                GeneratedMutableMap.map20780map,
                                GeneratedMutableMap.map20781map,
                                GeneratedMutableMap.map20782map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20639",
                            "#_20650",
                            "#_20655",
                            "#_20647",
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
                                "#_20796"
                            ), listOf(
                                GeneratedMutableMap.map20900map,
                                GeneratedMutableMap.map20901map,
                                GeneratedMutableMap.map20902map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20801"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20796",
                            "#_20801"
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
                                "#_20796",
                                "#_20801",
                                "#_20806"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20796"
                            ), listOf(
                                GeneratedMutableMap.map20903map,
                                GeneratedMutableMap.map20904map,
                                GeneratedMutableMap.map20905map,
                                GeneratedMutableMap.map20906map,
                                GeneratedMutableMap.map20907map,
                                GeneratedMutableMap.map20908map,
                                GeneratedMutableMap.map20909map,
                                GeneratedMutableMap.map20910map,
                                GeneratedMutableMap.map20911map,
                                GeneratedMutableMap.map20912map,
                                GeneratedMutableMap.map20913map,
                                GeneratedMutableMap.map20914map,
                                GeneratedMutableMap.map20915map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20796",
                            "#_20801",
                            "#_20806",
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
                                "#_20929"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20937"
                            ), listOf(
                                GeneratedMutableMap.map21085map,
                                GeneratedMutableMap.map21086map,
                                GeneratedMutableMap.map21087map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20929",
                            "#_20937"
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
                                "#_20929",
                                "#_20937",
                                "#_20942",
                                "#_20947"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20929"
                            ), listOf(
                                GeneratedMutableMap.map21072map,
                                GeneratedMutableMap.map21073map,
                                GeneratedMutableMap.map21074map,
                                GeneratedMutableMap.map21075map,
                                GeneratedMutableMap.map21076map,
                                GeneratedMutableMap.map21077map,
                                GeneratedMutableMap.map21078map,
                                GeneratedMutableMap.map21079map,
                                GeneratedMutableMap.map21080map,
                                GeneratedMutableMap.map21081map,
                                GeneratedMutableMap.map21082map,
                                GeneratedMutableMap.map21083map,
                                GeneratedMutableMap.map21084map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20929",
                            "#_20937",
                            "#_20942",
                            "#_20947",
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
                                "#_21101"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21109"
                            ), listOf(
                                GeneratedMutableMap.map21269map,
                                GeneratedMutableMap.map21270map,
                                GeneratedMutableMap.map21271map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21101",
                            "#_21109"
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
                                "#_21101",
                                "#_21109",
                                "#_21114",
                                "#_21119",
                                "#_21124"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21101"
                            ), listOf(
                                GeneratedMutableMap.map21272map,
                                GeneratedMutableMap.map21273map,
                                GeneratedMutableMap.map21274map,
                                GeneratedMutableMap.map21275map,
                                GeneratedMutableMap.map21276map,
                                GeneratedMutableMap.map21277map,
                                GeneratedMutableMap.map21278map,
                                GeneratedMutableMap.map21279map,
                                GeneratedMutableMap.map21280map,
                                GeneratedMutableMap.map21281map,
                                GeneratedMutableMap.map21282map,
                                GeneratedMutableMap.map21283map,
                                GeneratedMutableMap.map21284map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21101",
                            "#_21109",
                            "#_21114",
                            "#_21119",
                            "#_21124",
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
                                "#_21298"
                            ), listOf(
                                GeneratedMutableMap.map21477map,
                                GeneratedMutableMap.map21478map,
                                GeneratedMutableMap.map21479map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21303"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21298",
                            "#_21303"
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
                                "#_21298",
                                "#_21303"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21307"
                            ), listOf(
                                GeneratedMutableMap.map21480map,
                                GeneratedMutableMap.map21481map,
                                GeneratedMutableMap.map21482map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21298",
                            "#_21303",
                            "#_21307"
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
                                "#_21298",
                                "#_21303",
                                "#_21307",
                                "#_21312",
                                "#_21317",
                                "#_21323"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21298"
                            ), listOf(
                                GeneratedMutableMap.map21483map,
                                GeneratedMutableMap.map21484map,
                                GeneratedMutableMap.map21485map,
                                GeneratedMutableMap.map21486map,
                                GeneratedMutableMap.map21487map,
                                GeneratedMutableMap.map21488map,
                                GeneratedMutableMap.map21489map,
                                GeneratedMutableMap.map21490map,
                                GeneratedMutableMap.map21491map,
                                GeneratedMutableMap.map21492map,
                                GeneratedMutableMap.map21493map,
                                GeneratedMutableMap.map21494map,
                                GeneratedMutableMap.map21495map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21298",
                            "#_21303",
                            "#_21307",
                            "#_21312",
                            "#_21317",
                            "#_21323",
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
                                "#_21509",
                                "#_21517"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21509"
                            ), listOf(
                                GeneratedMutableMap.map21616map,
                                GeneratedMutableMap.map21617map,
                                GeneratedMutableMap.map21618map,
                                GeneratedMutableMap.map21619map,
                                GeneratedMutableMap.map21620map,
                                GeneratedMutableMap.map21621map,
                                GeneratedMutableMap.map21622map,
                                GeneratedMutableMap.map21623map,
                                GeneratedMutableMap.map21624map,
                                GeneratedMutableMap.map21625map,
                                GeneratedMutableMap.map21626map,
                                GeneratedMutableMap.map21627map,
                                GeneratedMutableMap.map21628map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21509",
                            "#_21517",
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
                                GeneratedMutableMap.map24630map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map24632map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map24634map
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
                            GeneratedMutableMap.map24634map
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
                                GeneratedMutableMap.map24631map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map24633map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map24635map
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
                            GeneratedMutableMap.map24635map
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
                                GeneratedMutableMap.map25244map,
                                GeneratedMutableMap.map25245map,
                                GeneratedMutableMap.map25246map,
                                GeneratedMutableMap.map25247map,
                                GeneratedMutableMap.map25248map,
                                GeneratedMutableMap.map25249map,
                                GeneratedMutableMap.map25250map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s2",
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map25230map,
                                GeneratedMutableMap.map25231map,
                                GeneratedMutableMap.map25232map,
                                GeneratedMutableMap.map25233map,
                                GeneratedMutableMap.map25234map,
                                GeneratedMutableMap.map25235map,
                                GeneratedMutableMap.map25236map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "str1",
                            "s2",
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map25251map,
                            GeneratedMutableMap.map25252map,
                            GeneratedMutableMap.map25253map,
                            GeneratedMutableMap.map25254map,
                            GeneratedMutableMap.map25255map,
                            GeneratedMutableMap.map25256map,
                            GeneratedMutableMap.map25257map,
                            GeneratedMutableMap.map25258map,
                            GeneratedMutableMap.map25259map,
                            GeneratedMutableMap.map25260map,
                            GeneratedMutableMap.map25261map,
                            GeneratedMutableMap.map25262map,
                            GeneratedMutableMap.map25263map,
                            GeneratedMutableMap.map25264map,
                            GeneratedMutableMap.map25265map,
                            GeneratedMutableMap.map25266map,
                            GeneratedMutableMap.map25267map,
                            GeneratedMutableMap.map25268map,
                            GeneratedMutableMap.map25269map,
                            GeneratedMutableMap.map25270map,
                            GeneratedMutableMap.map25271map,
                            GeneratedMutableMap.map25272map,
                            GeneratedMutableMap.map25273map,
                            GeneratedMutableMap.map25274map,
                            GeneratedMutableMap.map25275map,
                            GeneratedMutableMap.map25276map,
                            GeneratedMutableMap.map25277map,
                            GeneratedMutableMap.map25278map,
                            GeneratedMutableMap.map25279map,
                            GeneratedMutableMap.map25280map,
                            GeneratedMutableMap.map25281map,
                            GeneratedMutableMap.map25282map,
                            GeneratedMutableMap.map25283map,
                            GeneratedMutableMap.map25284map,
                            GeneratedMutableMap.map25285map,
                            GeneratedMutableMap.map25286map,
                            GeneratedMutableMap.map25287map,
                            GeneratedMutableMap.map25288map,
                            GeneratedMutableMap.map25289map,
                            GeneratedMutableMap.map25290map,
                            GeneratedMutableMap.map25291map,
                            GeneratedMutableMap.map25292map,
                            GeneratedMutableMap.map25293map,
                            GeneratedMutableMap.map25294map,
                            GeneratedMutableMap.map25295map,
                            GeneratedMutableMap.map25296map,
                            GeneratedMutableMap.map25297map,
                            GeneratedMutableMap.map25298map,
                            GeneratedMutableMap.map25299map
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
                            GeneratedMutableMap.map25251map,
                            GeneratedMutableMap.map25252map,
                            GeneratedMutableMap.map25253map,
                            GeneratedMutableMap.map25254map,
                            GeneratedMutableMap.map25255map,
                            GeneratedMutableMap.map25256map,
                            GeneratedMutableMap.map25257map,
                            GeneratedMutableMap.map25258map,
                            GeneratedMutableMap.map25259map,
                            GeneratedMutableMap.map25260map,
                            GeneratedMutableMap.map25261map,
                            GeneratedMutableMap.map25262map,
                            GeneratedMutableMap.map25263map,
                            GeneratedMutableMap.map25264map,
                            GeneratedMutableMap.map25265map,
                            GeneratedMutableMap.map25266map,
                            GeneratedMutableMap.map25267map,
                            GeneratedMutableMap.map25268map,
                            GeneratedMutableMap.map25269map,
                            GeneratedMutableMap.map25270map,
                            GeneratedMutableMap.map25271map,
                            GeneratedMutableMap.map25272map,
                            GeneratedMutableMap.map25273map,
                            GeneratedMutableMap.map25274map,
                            GeneratedMutableMap.map25275map,
                            GeneratedMutableMap.map25276map,
                            GeneratedMutableMap.map25277map,
                            GeneratedMutableMap.map25278map,
                            GeneratedMutableMap.map25279map,
                            GeneratedMutableMap.map25280map,
                            GeneratedMutableMap.map25281map,
                            GeneratedMutableMap.map25282map,
                            GeneratedMutableMap.map25283map,
                            GeneratedMutableMap.map25284map,
                            GeneratedMutableMap.map25285map,
                            GeneratedMutableMap.map25286map,
                            GeneratedMutableMap.map25287map,
                            GeneratedMutableMap.map25288map,
                            GeneratedMutableMap.map25289map,
                            GeneratedMutableMap.map25290map,
                            GeneratedMutableMap.map25291map,
                            GeneratedMutableMap.map25292map,
                            GeneratedMutableMap.map25293map,
                            GeneratedMutableMap.map25294map,
                            GeneratedMutableMap.map25295map,
                            GeneratedMutableMap.map25296map,
                            GeneratedMutableMap.map25297map,
                            GeneratedMutableMap.map25298map,
                            GeneratedMutableMap.map25299map
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
                                GeneratedMutableMap.map25237map,
                                GeneratedMutableMap.map25238map,
                                GeneratedMutableMap.map25239map,
                                GeneratedMutableMap.map25240map,
                                GeneratedMutableMap.map25241map,
                                GeneratedMutableMap.map25242map,
                                GeneratedMutableMap.map25243map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s2"),
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map25237map,
                                GeneratedMutableMap.map25238map,
                                GeneratedMutableMap.map25239map,
                                GeneratedMutableMap.map25240map,
                                GeneratedMutableMap.map25241map,
                                GeneratedMutableMap.map25242map,
                                GeneratedMutableMap.map25243map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("str1"),
                            AOPVariable("s2"),
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map25300map,
                            GeneratedMutableMap.map25301map,
                            GeneratedMutableMap.map25302map,
                            GeneratedMutableMap.map25303map,
                            GeneratedMutableMap.map25304map,
                            GeneratedMutableMap.map25305map,
                            GeneratedMutableMap.map25306map,
                            GeneratedMutableMap.map25307map,
                            GeneratedMutableMap.map25308map,
                            GeneratedMutableMap.map25309map,
                            GeneratedMutableMap.map25310map,
                            GeneratedMutableMap.map25311map,
                            GeneratedMutableMap.map25312map,
                            GeneratedMutableMap.map25313map,
                            GeneratedMutableMap.map25314map,
                            GeneratedMutableMap.map25315map,
                            GeneratedMutableMap.map25316map,
                            GeneratedMutableMap.map25317map,
                            GeneratedMutableMap.map25318map,
                            GeneratedMutableMap.map25319map,
                            GeneratedMutableMap.map25320map,
                            GeneratedMutableMap.map25321map,
                            GeneratedMutableMap.map25322map,
                            GeneratedMutableMap.map25323map,
                            GeneratedMutableMap.map25324map,
                            GeneratedMutableMap.map25325map,
                            GeneratedMutableMap.map25326map,
                            GeneratedMutableMap.map25327map,
                            GeneratedMutableMap.map25328map,
                            GeneratedMutableMap.map25329map,
                            GeneratedMutableMap.map25330map,
                            GeneratedMutableMap.map25331map,
                            GeneratedMutableMap.map25332map,
                            GeneratedMutableMap.map25333map,
                            GeneratedMutableMap.map25334map,
                            GeneratedMutableMap.map25335map,
                            GeneratedMutableMap.map25336map,
                            GeneratedMutableMap.map25337map,
                            GeneratedMutableMap.map25338map,
                            GeneratedMutableMap.map25339map,
                            GeneratedMutableMap.map25340map,
                            GeneratedMutableMap.map25341map,
                            GeneratedMutableMap.map25342map,
                            GeneratedMutableMap.map25343map,
                            GeneratedMutableMap.map25344map,
                            GeneratedMutableMap.map25345map,
                            GeneratedMutableMap.map25346map,
                            GeneratedMutableMap.map25347map,
                            GeneratedMutableMap.map25348map
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
                            GeneratedMutableMap.map25300map,
                            GeneratedMutableMap.map25301map,
                            GeneratedMutableMap.map25302map,
                            GeneratedMutableMap.map25303map,
                            GeneratedMutableMap.map25304map,
                            GeneratedMutableMap.map25305map,
                            GeneratedMutableMap.map25306map,
                            GeneratedMutableMap.map25307map,
                            GeneratedMutableMap.map25308map,
                            GeneratedMutableMap.map25309map,
                            GeneratedMutableMap.map25310map,
                            GeneratedMutableMap.map25311map,
                            GeneratedMutableMap.map25312map,
                            GeneratedMutableMap.map25313map,
                            GeneratedMutableMap.map25314map,
                            GeneratedMutableMap.map25315map,
                            GeneratedMutableMap.map25316map,
                            GeneratedMutableMap.map25317map,
                            GeneratedMutableMap.map25318map,
                            GeneratedMutableMap.map25319map,
                            GeneratedMutableMap.map25320map,
                            GeneratedMutableMap.map25321map,
                            GeneratedMutableMap.map25322map,
                            GeneratedMutableMap.map25323map,
                            GeneratedMutableMap.map25324map,
                            GeneratedMutableMap.map25325map,
                            GeneratedMutableMap.map25326map,
                            GeneratedMutableMap.map25327map,
                            GeneratedMutableMap.map25328map,
                            GeneratedMutableMap.map25329map,
                            GeneratedMutableMap.map25330map,
                            GeneratedMutableMap.map25331map,
                            GeneratedMutableMap.map25332map,
                            GeneratedMutableMap.map25333map,
                            GeneratedMutableMap.map25334map,
                            GeneratedMutableMap.map25335map,
                            GeneratedMutableMap.map25336map,
                            GeneratedMutableMap.map25337map,
                            GeneratedMutableMap.map25338map,
                            GeneratedMutableMap.map25339map,
                            GeneratedMutableMap.map25340map,
                            GeneratedMutableMap.map25341map,
                            GeneratedMutableMap.map25342map,
                            GeneratedMutableMap.map25343map,
                            GeneratedMutableMap.map25344map,
                            GeneratedMutableMap.map25345map,
                            GeneratedMutableMap.map25346map,
                            GeneratedMutableMap.map25347map,
                            GeneratedMutableMap.map25348map
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
                                GeneratedMutableMap.map27140map,
                                GeneratedMutableMap.map27141map,
                                GeneratedMutableMap.map27142map,
                                GeneratedMutableMap.map27143map,
                                GeneratedMutableMap.map27144map,
                                GeneratedMutableMap.map27145map,
                                GeneratedMutableMap.map27146map,
                                GeneratedMutableMap.map27147map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map27156map,
                                GeneratedMutableMap.map27157map,
                                GeneratedMutableMap.map27158map,
                                GeneratedMutableMap.map27159map,
                                GeneratedMutableMap.map27160map,
                                GeneratedMutableMap.map27161map,
                                GeneratedMutableMap.map27162map,
                                GeneratedMutableMap.map27163map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map27164map,
                            GeneratedMutableMap.map27165map,
                            GeneratedMutableMap.map27166map,
                            GeneratedMutableMap.map27167map,
                            GeneratedMutableMap.map27168map,
                            GeneratedMutableMap.map27169map,
                            GeneratedMutableMap.map27170map,
                            GeneratedMutableMap.map27171map
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
                            GeneratedMutableMap.map27164map,
                            GeneratedMutableMap.map27165map,
                            GeneratedMutableMap.map27166map,
                            GeneratedMutableMap.map27167map,
                            GeneratedMutableMap.map27168map,
                            GeneratedMutableMap.map27169map,
                            GeneratedMutableMap.map27170map,
                            GeneratedMutableMap.map27171map
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
                                GeneratedMutableMap.map27148map,
                                GeneratedMutableMap.map27149map,
                                GeneratedMutableMap.map27150map,
                                GeneratedMutableMap.map27151map,
                                GeneratedMutableMap.map27152map,
                                GeneratedMutableMap.map27153map,
                                GeneratedMutableMap.map27154map,
                                GeneratedMutableMap.map27155map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map27172map,
                                GeneratedMutableMap.map27173map,
                                GeneratedMutableMap.map27174map,
                                GeneratedMutableMap.map27175map,
                                GeneratedMutableMap.map27176map,
                                GeneratedMutableMap.map27177map,
                                GeneratedMutableMap.map27178map,
                                GeneratedMutableMap.map27179map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map27180map,
                            GeneratedMutableMap.map27181map,
                            GeneratedMutableMap.map27182map,
                            GeneratedMutableMap.map27183map,
                            GeneratedMutableMap.map27184map,
                            GeneratedMutableMap.map27185map,
                            GeneratedMutableMap.map27186map,
                            GeneratedMutableMap.map27187map
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
                            GeneratedMutableMap.map27180map,
                            GeneratedMutableMap.map27181map,
                            GeneratedMutableMap.map27182map,
                            GeneratedMutableMap.map27183map,
                            GeneratedMutableMap.map27184map,
                            GeneratedMutableMap.map27185map,
                            GeneratedMutableMap.map27186map,
                            GeneratedMutableMap.map27187map
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
                                GeneratedMutableMap.map30945map,
                                GeneratedMutableMap.map30946map,
                                GeneratedMutableMap.map30947map,
                                GeneratedMutableMap.map30948map,
                                GeneratedMutableMap.map30949map,
                                GeneratedMutableMap.map30950map,
                                GeneratedMutableMap.map30951map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "s2"
                            ), listOf(
                                GeneratedMutableMap.map30938map,
                                GeneratedMutableMap.map30939map,
                                GeneratedMutableMap.map30940map,
                                GeneratedMutableMap.map30941map,
                                GeneratedMutableMap.map30942map,
                                GeneratedMutableMap.map30943map,
                                GeneratedMutableMap.map30944map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "s1",
                            "b",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map30952map,
                            GeneratedMutableMap.map30953map,
                            GeneratedMutableMap.map30954map,
                            GeneratedMutableMap.map30955map,
                            GeneratedMutableMap.map30956map,
                            GeneratedMutableMap.map30957map,
                            GeneratedMutableMap.map30958map,
                            GeneratedMutableMap.map30959map,
                            GeneratedMutableMap.map30960map,
                            GeneratedMutableMap.map30961map,
                            GeneratedMutableMap.map30962map,
                            GeneratedMutableMap.map30963map,
                            GeneratedMutableMap.map30964map,
                            GeneratedMutableMap.map30965map,
                            GeneratedMutableMap.map30966map,
                            GeneratedMutableMap.map30967map,
                            GeneratedMutableMap.map30968map,
                            GeneratedMutableMap.map30969map,
                            GeneratedMutableMap.map30970map,
                            GeneratedMutableMap.map30971map,
                            GeneratedMutableMap.map30972map,
                            GeneratedMutableMap.map30973map,
                            GeneratedMutableMap.map30974map,
                            GeneratedMutableMap.map30975map,
                            GeneratedMutableMap.map30976map,
                            GeneratedMutableMap.map30977map,
                            GeneratedMutableMap.map30978map,
                            GeneratedMutableMap.map30979map,
                            GeneratedMutableMap.map30980map,
                            GeneratedMutableMap.map30981map,
                            GeneratedMutableMap.map30982map,
                            GeneratedMutableMap.map30983map,
                            GeneratedMutableMap.map30984map,
                            GeneratedMutableMap.map30985map,
                            GeneratedMutableMap.map30986map,
                            GeneratedMutableMap.map30987map,
                            GeneratedMutableMap.map30988map,
                            GeneratedMutableMap.map30989map,
                            GeneratedMutableMap.map30990map,
                            GeneratedMutableMap.map30991map,
                            GeneratedMutableMap.map30992map,
                            GeneratedMutableMap.map30993map,
                            GeneratedMutableMap.map30994map,
                            GeneratedMutableMap.map30995map,
                            GeneratedMutableMap.map30996map,
                            GeneratedMutableMap.map30997map,
                            GeneratedMutableMap.map30998map,
                            GeneratedMutableMap.map30999map,
                            GeneratedMutableMap.map31000map
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
                            GeneratedMutableMap.map30952map,
                            GeneratedMutableMap.map30953map,
                            GeneratedMutableMap.map30954map,
                            GeneratedMutableMap.map30955map,
                            GeneratedMutableMap.map30956map,
                            GeneratedMutableMap.map30957map,
                            GeneratedMutableMap.map30958map,
                            GeneratedMutableMap.map30959map,
                            GeneratedMutableMap.map30960map,
                            GeneratedMutableMap.map30961map,
                            GeneratedMutableMap.map30962map,
                            GeneratedMutableMap.map30963map,
                            GeneratedMutableMap.map30964map,
                            GeneratedMutableMap.map30965map,
                            GeneratedMutableMap.map30966map,
                            GeneratedMutableMap.map30967map,
                            GeneratedMutableMap.map30968map,
                            GeneratedMutableMap.map30969map,
                            GeneratedMutableMap.map30970map,
                            GeneratedMutableMap.map30971map,
                            GeneratedMutableMap.map30972map,
                            GeneratedMutableMap.map30973map,
                            GeneratedMutableMap.map30974map,
                            GeneratedMutableMap.map30975map,
                            GeneratedMutableMap.map30976map,
                            GeneratedMutableMap.map30977map,
                            GeneratedMutableMap.map30978map,
                            GeneratedMutableMap.map30979map,
                            GeneratedMutableMap.map30980map,
                            GeneratedMutableMap.map30981map,
                            GeneratedMutableMap.map30982map,
                            GeneratedMutableMap.map30983map,
                            GeneratedMutableMap.map30984map,
                            GeneratedMutableMap.map30985map,
                            GeneratedMutableMap.map30986map,
                            GeneratedMutableMap.map30987map,
                            GeneratedMutableMap.map30988map,
                            GeneratedMutableMap.map30989map,
                            GeneratedMutableMap.map30990map,
                            GeneratedMutableMap.map30991map,
                            GeneratedMutableMap.map30992map,
                            GeneratedMutableMap.map30993map,
                            GeneratedMutableMap.map30994map,
                            GeneratedMutableMap.map30995map,
                            GeneratedMutableMap.map30996map,
                            GeneratedMutableMap.map30997map,
                            GeneratedMutableMap.map30998map,
                            GeneratedMutableMap.map30999map,
                            GeneratedMutableMap.map31000map
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
                                GeneratedMutableMap.map22694map,
                                GeneratedMutableMap.map22695map,
                                GeneratedMutableMap.map22696map,
                                GeneratedMutableMap.map22697map,
                                GeneratedMutableMap.map22698map,
                                GeneratedMutableMap.map22699map,
                                GeneratedMutableMap.map22700map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("b"),
                                AOPVariable("s2")
                            ), listOf(
                                GeneratedMutableMap.map22694map,
                                GeneratedMutableMap.map22695map,
                                GeneratedMutableMap.map22696map,
                                GeneratedMutableMap.map22697map,
                                GeneratedMutableMap.map22698map,
                                GeneratedMutableMap.map22699map,
                                GeneratedMutableMap.map22700map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("s1"),
                            AOPVariable("b"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map31001map,
                            GeneratedMutableMap.map31002map,
                            GeneratedMutableMap.map31003map,
                            GeneratedMutableMap.map31004map,
                            GeneratedMutableMap.map31005map,
                            GeneratedMutableMap.map31006map,
                            GeneratedMutableMap.map31007map,
                            GeneratedMutableMap.map31008map,
                            GeneratedMutableMap.map31009map,
                            GeneratedMutableMap.map31010map,
                            GeneratedMutableMap.map31011map,
                            GeneratedMutableMap.map31012map,
                            GeneratedMutableMap.map31013map,
                            GeneratedMutableMap.map31014map,
                            GeneratedMutableMap.map31015map,
                            GeneratedMutableMap.map31016map,
                            GeneratedMutableMap.map31017map,
                            GeneratedMutableMap.map31018map,
                            GeneratedMutableMap.map31019map,
                            GeneratedMutableMap.map31020map,
                            GeneratedMutableMap.map31021map,
                            GeneratedMutableMap.map31022map,
                            GeneratedMutableMap.map31023map,
                            GeneratedMutableMap.map31024map,
                            GeneratedMutableMap.map31025map,
                            GeneratedMutableMap.map31026map,
                            GeneratedMutableMap.map31027map,
                            GeneratedMutableMap.map31028map,
                            GeneratedMutableMap.map31029map,
                            GeneratedMutableMap.map31030map,
                            GeneratedMutableMap.map31031map,
                            GeneratedMutableMap.map31032map,
                            GeneratedMutableMap.map31033map,
                            GeneratedMutableMap.map31034map,
                            GeneratedMutableMap.map31035map,
                            GeneratedMutableMap.map31036map,
                            GeneratedMutableMap.map31037map,
                            GeneratedMutableMap.map31038map,
                            GeneratedMutableMap.map31039map,
                            GeneratedMutableMap.map31040map,
                            GeneratedMutableMap.map31041map,
                            GeneratedMutableMap.map31042map,
                            GeneratedMutableMap.map31043map,
                            GeneratedMutableMap.map31044map,
                            GeneratedMutableMap.map31045map,
                            GeneratedMutableMap.map31046map,
                            GeneratedMutableMap.map31047map,
                            GeneratedMutableMap.map31048map,
                            GeneratedMutableMap.map31049map
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
                            GeneratedMutableMap.map31001map,
                            GeneratedMutableMap.map31002map,
                            GeneratedMutableMap.map31003map,
                            GeneratedMutableMap.map31004map,
                            GeneratedMutableMap.map31005map,
                            GeneratedMutableMap.map31006map,
                            GeneratedMutableMap.map31007map,
                            GeneratedMutableMap.map31008map,
                            GeneratedMutableMap.map31009map,
                            GeneratedMutableMap.map31010map,
                            GeneratedMutableMap.map31011map,
                            GeneratedMutableMap.map31012map,
                            GeneratedMutableMap.map31013map,
                            GeneratedMutableMap.map31014map,
                            GeneratedMutableMap.map31015map,
                            GeneratedMutableMap.map31016map,
                            GeneratedMutableMap.map31017map,
                            GeneratedMutableMap.map31018map,
                            GeneratedMutableMap.map31019map,
                            GeneratedMutableMap.map31020map,
                            GeneratedMutableMap.map31021map,
                            GeneratedMutableMap.map31022map,
                            GeneratedMutableMap.map31023map,
                            GeneratedMutableMap.map31024map,
                            GeneratedMutableMap.map31025map,
                            GeneratedMutableMap.map31026map,
                            GeneratedMutableMap.map31027map,
                            GeneratedMutableMap.map31028map,
                            GeneratedMutableMap.map31029map,
                            GeneratedMutableMap.map31030map,
                            GeneratedMutableMap.map31031map,
                            GeneratedMutableMap.map31032map,
                            GeneratedMutableMap.map31033map,
                            GeneratedMutableMap.map31034map,
                            GeneratedMutableMap.map31035map,
                            GeneratedMutableMap.map31036map,
                            GeneratedMutableMap.map31037map,
                            GeneratedMutableMap.map31038map,
                            GeneratedMutableMap.map31039map,
                            GeneratedMutableMap.map31040map,
                            GeneratedMutableMap.map31041map,
                            GeneratedMutableMap.map31042map,
                            GeneratedMutableMap.map31043map,
                            GeneratedMutableMap.map31044map,
                            GeneratedMutableMap.map31045map,
                            GeneratedMutableMap.map31046map,
                            GeneratedMutableMap.map31047map,
                            GeneratedMutableMap.map31048map,
                            GeneratedMutableMap.map31049map
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
                                GeneratedMutableMap.map32414map,
                                GeneratedMutableMap.map32415map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32549map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32551map,
                            GeneratedMutableMap.map32552map
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
                            GeneratedMutableMap.map32551map,
                            GeneratedMutableMap.map32552map
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
                                GeneratedMutableMap.map32416map,
                                GeneratedMutableMap.map32417map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32550map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32553map,
                            GeneratedMutableMap.map32554map
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
                            GeneratedMutableMap.map32553map,
                            GeneratedMutableMap.map32554map
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
                                GeneratedMutableMap.map32414map,
                                GeneratedMutableMap.map32415map,
                                GeneratedMutableMap.map32696map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32549map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32551map,
                            GeneratedMutableMap.map32552map,
                            GeneratedMutableMap.map32698map
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
                            GeneratedMutableMap.map32551map,
                            GeneratedMutableMap.map32552map,
                            GeneratedMutableMap.map32698map
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
                                GeneratedMutableMap.map32416map,
                                GeneratedMutableMap.map32417map,
                                GeneratedMutableMap.map32697map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32550map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32553map,
                            GeneratedMutableMap.map32554map,
                            GeneratedMutableMap.map32699map
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
                            GeneratedMutableMap.map32553map,
                            GeneratedMutableMap.map32554map,
                            GeneratedMutableMap.map32699map
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
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map32974map,
                                GeneratedMutableMap.map10279map,
                                GeneratedMutableMap.map32975map,
                                GeneratedMutableMap.map10281map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10460map,
                                GeneratedMutableMap.map10461map,
                                GeneratedMutableMap.map33102map,
                                GeneratedMutableMap.map10463map,
                                GeneratedMutableMap.map33103map,
                                GeneratedMutableMap.map10465map
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
                            GeneratedMutableMap.map10466map,
                            GeneratedMutableMap.map10467map,
                            GeneratedMutableMap.map33104map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map33105map,
                            GeneratedMutableMap.map10471map
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
                            GeneratedMutableMap.map10466map,
                            GeneratedMutableMap.map10467map,
                            GeneratedMutableMap.map33104map,
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map33105map,
                            GeneratedMutableMap.map10471map
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
                                GeneratedMutableMap.map10358map,
                                GeneratedMutableMap.map10359map,
                                GeneratedMutableMap.map32976map,
                                GeneratedMutableMap.map10361map,
                                GeneratedMutableMap.map32977map,
                                GeneratedMutableMap.map10363map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10358map,
                                GeneratedMutableMap.map10359map,
                                GeneratedMutableMap.map32976map,
                                GeneratedMutableMap.map10361map,
                                GeneratedMutableMap.map32977map,
                                GeneratedMutableMap.map10363map
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
                            GeneratedMutableMap.map10596map,
                            GeneratedMutableMap.map10597map,
                            GeneratedMutableMap.map33106map,
                            GeneratedMutableMap.map10599map,
                            GeneratedMutableMap.map33107map,
                            GeneratedMutableMap.map10601map
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
                            GeneratedMutableMap.map10596map,
                            GeneratedMutableMap.map10597map,
                            GeneratedMutableMap.map33106map,
                            GeneratedMutableMap.map10599map,
                            GeneratedMutableMap.map33107map,
                            GeneratedMutableMap.map10601map
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
                                GeneratedMutableMap.map34830map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34832map,
                                GeneratedMutableMap.map34833map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map34835map,
                            GeneratedMutableMap.map34836map
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
                            GeneratedMutableMap.map34835map,
                            GeneratedMutableMap.map34836map
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
                                GeneratedMutableMap.map34831map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map34831map,
                                GeneratedMutableMap.map34834map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map34837map,
                            GeneratedMutableMap.map34838map
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
                            GeneratedMutableMap.map34837map,
                            GeneratedMutableMap.map34838map
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
                                GeneratedMutableMap.map34830map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34832map,
                                GeneratedMutableMap.map34957map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map34835map,
                            GeneratedMutableMap.map34958map
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
                            GeneratedMutableMap.map34835map,
                            GeneratedMutableMap.map34958map
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
                                GeneratedMutableMap.map34831map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map34831map,
                                GeneratedMutableMap.map34959map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map34837map,
                            GeneratedMutableMap.map34960map
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
                            GeneratedMutableMap.map34837map,
                            GeneratedMutableMap.map34960map
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
                                GeneratedMutableMap.map34830map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map34833map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map34836map
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
                                GeneratedMutableMap.map34830map,
                                GeneratedMutableMap.map35450map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                            ), listOf(
                                GeneratedMutableMap.map35452map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map35453map,
                            GeneratedMutableMap.map35454map
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
                            GeneratedMutableMap.map35453map,
                            GeneratedMutableMap.map35454map
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
                                GeneratedMutableMap.map34831map,
                                GeneratedMutableMap.map35451map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map34834map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map34838map,
                            GeneratedMutableMap.map35455map
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
                            GeneratedMutableMap.map34838map,
                            GeneratedMutableMap.map35455map
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
                                "#_36766",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map36890map,
                                GeneratedMutableMap.map36891map,
                                GeneratedMutableMap.map36892map,
                                GeneratedMutableMap.map36893map,
                                GeneratedMutableMap.map36894map,
                                GeneratedMutableMap.map36895map,
                                GeneratedMutableMap.map36896map,
                                GeneratedMutableMap.map36897map,
                                GeneratedMutableMap.map36898map,
                                GeneratedMutableMap.map36899map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_36766"
                            ), listOf(
                                GeneratedMutableMap.map36910map,
                                GeneratedMutableMap.map36911map,
                                GeneratedMutableMap.map36912map,
                                GeneratedMutableMap.map36913map,
                                GeneratedMutableMap.map36914map,
                                GeneratedMutableMap.map36915map,
                                GeneratedMutableMap.map36916map,
                                GeneratedMutableMap.map36917map,
                                GeneratedMutableMap.map36918map,
                                GeneratedMutableMap.map36919map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_36766",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36931map,
                            GeneratedMutableMap.map36932map,
                            GeneratedMutableMap.map36933map,
                            GeneratedMutableMap.map36934map,
                            GeneratedMutableMap.map36935map,
                            GeneratedMutableMap.map36936map,
                            GeneratedMutableMap.map36937map,
                            GeneratedMutableMap.map36938map,
                            GeneratedMutableMap.map36939map
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
                                        graph.addData(1L,listOf("_:_36743","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_36744","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_36745","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_36746","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_36747","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_36766","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36743"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36744"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36745"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36746"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36747"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36748"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36749"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36750"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36751"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36752"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_36766",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_36766",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36931map,
                            GeneratedMutableMap.map36932map,
                            GeneratedMutableMap.map36933map,
                            GeneratedMutableMap.map36934map,
                            GeneratedMutableMap.map36935map,
                            GeneratedMutableMap.map36936map,
                            GeneratedMutableMap.map36937map,
                            GeneratedMutableMap.map36938map,
                            GeneratedMutableMap.map36939map
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
                                AOPVariable("#_36766"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map36900map,
                                GeneratedMutableMap.map36901map,
                                GeneratedMutableMap.map36902map,
                                GeneratedMutableMap.map36903map,
                                GeneratedMutableMap.map36904map,
                                GeneratedMutableMap.map36905map,
                                GeneratedMutableMap.map36906map,
                                GeneratedMutableMap.map36907map,
                                GeneratedMutableMap.map36908map,
                                GeneratedMutableMap.map36909map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_36766")
                            ), listOf(
                                GeneratedMutableMap.map36920map,
                                GeneratedMutableMap.map36921map,
                                GeneratedMutableMap.map36922map,
                                GeneratedMutableMap.map36923map,
                                GeneratedMutableMap.map36924map,
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map,
                                GeneratedMutableMap.map36928map,
                                GeneratedMutableMap.map36929map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_36766"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36940map,
                            GeneratedMutableMap.map36941map,
                            GeneratedMutableMap.map36942map,
                            GeneratedMutableMap.map36943map,
                            GeneratedMutableMap.map36944map,
                            GeneratedMutableMap.map36945map,
                            GeneratedMutableMap.map36946map,
                            GeneratedMutableMap.map36947map,
                            GeneratedMutableMap.map36948map,
                            GeneratedMutableMap.map36949map
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
                                        graph.addData(1L,listOf("_:_36743","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_36744","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_36745","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_36746","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_36747","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_36766"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36743"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36744"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36745"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36746"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36747"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36748"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36749"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36750"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36751"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36752"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_36766"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_36766"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36940map,
                            GeneratedMutableMap.map36941map,
                            GeneratedMutableMap.map36942map,
                            GeneratedMutableMap.map36943map,
                            GeneratedMutableMap.map36944map,
                            GeneratedMutableMap.map36945map,
                            GeneratedMutableMap.map36946map,
                            GeneratedMutableMap.map36947map,
                            GeneratedMutableMap.map36948map,
                            GeneratedMutableMap.map36949map
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
                                "#_36766",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map36930map,
                                GeneratedMutableMap.map36931map,
                                GeneratedMutableMap.map36932map,
                                GeneratedMutableMap.map36933map,
                                GeneratedMutableMap.map36934map,
                                GeneratedMutableMap.map36935map,
                                GeneratedMutableMap.map36936map,
                                GeneratedMutableMap.map36937map,
                                GeneratedMutableMap.map36938map,
                                GeneratedMutableMap.map36939map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map36882map,
                                GeneratedMutableMap.map36883map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_36766",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36931map,
                            GeneratedMutableMap.map36932map,
                            GeneratedMutableMap.map36933map,
                            GeneratedMutableMap.map36934map,
                            GeneratedMutableMap.map36935map
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
                                                        graph.addData(1L,listOf("_:_36743","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_36744","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_36745","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_36746","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_36747","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_36766","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36743"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36744"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36745"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36746"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36747"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36748"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36749"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36750"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36751"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36752"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_36766",false,true,false,EIndexPattern.SPO)
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
                            "#_36766",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36931map,
                            GeneratedMutableMap.map36932map,
                            GeneratedMutableMap.map36933map,
                            GeneratedMutableMap.map36934map,
                            GeneratedMutableMap.map36935map
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
                                AOPVariable("#_36766"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map36940map,
                                GeneratedMutableMap.map36941map,
                                GeneratedMutableMap.map36942map,
                                GeneratedMutableMap.map36943map,
                                GeneratedMutableMap.map36944map,
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map,
                                GeneratedMutableMap.map36948map,
                                GeneratedMutableMap.map36949map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map36886map,
                                GeneratedMutableMap.map36887map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_36766"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36940map,
                            GeneratedMutableMap.map36941map,
                            GeneratedMutableMap.map36942map,
                            GeneratedMutableMap.map36943map,
                            GeneratedMutableMap.map36944map,
                            GeneratedMutableMap.map36945map
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
                                                        graph.addData(1L,listOf("_:_36743","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_36744","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_36745","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_36746","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_36747","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_36766"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36743"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36744"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36745"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36746"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36747"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36748"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36749"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36750"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36751"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36752"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_36766"),graphName,false)                                                    }()
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
                            AOPVariable("#_36766"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36940map,
                            GeneratedMutableMap.map36941map,
                            GeneratedMutableMap.map36942map,
                            GeneratedMutableMap.map36943map,
                            GeneratedMutableMap.map36944map,
                            GeneratedMutableMap.map36945map
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
                                GeneratedMutableMap.map37144map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37142map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37146map
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
                            GeneratedMutableMap.map37146map
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
                                GeneratedMutableMap.map37145map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37143map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37147map
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
                            GeneratedMutableMap.map37147map
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
                                "#_37181",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37297map,
                                GeneratedMutableMap.map37298map,
                                GeneratedMutableMap.map37299map,
                                GeneratedMutableMap.map37300map,
                                GeneratedMutableMap.map37301map,
                                GeneratedMutableMap.map37302map,
                                GeneratedMutableMap.map37303map,
                                GeneratedMutableMap.map37304map,
                                GeneratedMutableMap.map37305map,
                                GeneratedMutableMap.map37306map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37181"
                            ), listOf(
                                GeneratedMutableMap.map37317map,
                                GeneratedMutableMap.map37318map,
                                GeneratedMutableMap.map37319map,
                                GeneratedMutableMap.map37320map,
                                GeneratedMutableMap.map37321map,
                                GeneratedMutableMap.map37322map,
                                GeneratedMutableMap.map37323map,
                                GeneratedMutableMap.map37324map,
                                GeneratedMutableMap.map37325map,
                                GeneratedMutableMap.map37326map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37181",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37327map,
                            GeneratedMutableMap.map37328map,
                            GeneratedMutableMap.map37329map,
                            GeneratedMutableMap.map37330map,
                            GeneratedMutableMap.map37331map,
                            GeneratedMutableMap.map37332map,
                            GeneratedMutableMap.map37333map,
                            GeneratedMutableMap.map37334map,
                            GeneratedMutableMap.map37335map,
                            GeneratedMutableMap.map37336map
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
                                        graph.addData(1L,listOf("_:_37158","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37159","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37160","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37161","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37162","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37181","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37158"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37159"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37160"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37161"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37162"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37163"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37164"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37165"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37166"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37167"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37181",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37181",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37327map,
                            GeneratedMutableMap.map37328map,
                            GeneratedMutableMap.map37329map,
                            GeneratedMutableMap.map37330map,
                            GeneratedMutableMap.map37331map,
                            GeneratedMutableMap.map37332map,
                            GeneratedMutableMap.map37333map,
                            GeneratedMutableMap.map37334map,
                            GeneratedMutableMap.map37335map,
                            GeneratedMutableMap.map37336map
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
                                AOPVariable("#_37181"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37307map,
                                GeneratedMutableMap.map37308map,
                                GeneratedMutableMap.map37309map,
                                GeneratedMutableMap.map37310map,
                                GeneratedMutableMap.map37311map,
                                GeneratedMutableMap.map37312map,
                                GeneratedMutableMap.map37313map,
                                GeneratedMutableMap.map37314map,
                                GeneratedMutableMap.map37315map,
                                GeneratedMutableMap.map37316map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37181")
                            ), listOf(
                                GeneratedMutableMap.map37337map,
                                GeneratedMutableMap.map37338map,
                                GeneratedMutableMap.map37339map,
                                GeneratedMutableMap.map37340map,
                                GeneratedMutableMap.map37341map,
                                GeneratedMutableMap.map37342map,
                                GeneratedMutableMap.map37343map,
                                GeneratedMutableMap.map37344map,
                                GeneratedMutableMap.map37345map,
                                GeneratedMutableMap.map37346map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37181"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37347map,
                            GeneratedMutableMap.map37348map,
                            GeneratedMutableMap.map37349map,
                            GeneratedMutableMap.map37350map,
                            GeneratedMutableMap.map37351map,
                            GeneratedMutableMap.map37352map,
                            GeneratedMutableMap.map37353map,
                            GeneratedMutableMap.map37354map,
                            GeneratedMutableMap.map37355map,
                            GeneratedMutableMap.map37356map
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
                                        graph.addData(1L,listOf("_:_37158","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37159","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37160","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37161","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37162","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_37181"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37158"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37159"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37160"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37161"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37162"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37163"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37164"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37165"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37166"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37167"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37181"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37181"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37347map,
                            GeneratedMutableMap.map37348map,
                            GeneratedMutableMap.map37349map,
                            GeneratedMutableMap.map37350map,
                            GeneratedMutableMap.map37351map,
                            GeneratedMutableMap.map37352map,
                            GeneratedMutableMap.map37353map,
                            GeneratedMutableMap.map37354map,
                            GeneratedMutableMap.map37355map,
                            GeneratedMutableMap.map37356map
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
                                "#_37181",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37327map,
                                GeneratedMutableMap.map37328map,
                                GeneratedMutableMap.map37329map,
                                GeneratedMutableMap.map37330map,
                                GeneratedMutableMap.map37331map,
                                GeneratedMutableMap.map37332map,
                                GeneratedMutableMap.map37333map,
                                GeneratedMutableMap.map37334map,
                                GeneratedMutableMap.map37335map,
                                GeneratedMutableMap.map37336map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map36882map,
                                GeneratedMutableMap.map36883map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37181",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37327map,
                            GeneratedMutableMap.map37328map,
                            GeneratedMutableMap.map37329map,
                            GeneratedMutableMap.map37330map,
                            GeneratedMutableMap.map37331map,
                            GeneratedMutableMap.map37332map
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
                                                        graph.addData(1L,listOf("_:_37158","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37159","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37160","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37161","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37162","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37181","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37158"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37159"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37160"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37161"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37162"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37163"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37164"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37165"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37166"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37167"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37181",false,true,false,EIndexPattern.SPO)
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
                            "#_37181",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37327map,
                            GeneratedMutableMap.map37328map,
                            GeneratedMutableMap.map37329map,
                            GeneratedMutableMap.map37330map,
                            GeneratedMutableMap.map37331map,
                            GeneratedMutableMap.map37332map
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
                                AOPVariable("#_37181"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37347map,
                                GeneratedMutableMap.map37348map,
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map,
                                GeneratedMutableMap.map37353map,
                                GeneratedMutableMap.map37354map,
                                GeneratedMutableMap.map37355map,
                                GeneratedMutableMap.map37356map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map36886map,
                                GeneratedMutableMap.map36887map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37181"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37347map,
                            GeneratedMutableMap.map37348map,
                            GeneratedMutableMap.map37349map,
                            GeneratedMutableMap.map37350map,
                            GeneratedMutableMap.map37351map,
                            GeneratedMutableMap.map37352map
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
                                                        graph.addData(1L,listOf("_:_37158","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37159","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37160","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37161","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37162","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_37181"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37158"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37159"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37160"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37161"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37162"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37163"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37164"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37165"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37166"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37167"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37181"),graphName,false)                                                    }()
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
                            AOPVariable("#_37181"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37347map,
                            GeneratedMutableMap.map37348map,
                            GeneratedMutableMap.map37349map,
                            GeneratedMutableMap.map37350map,
                            GeneratedMutableMap.map37351map,
                            GeneratedMutableMap.map37352map
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
