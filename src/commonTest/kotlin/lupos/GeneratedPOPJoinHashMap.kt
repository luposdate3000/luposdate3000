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
                                GeneratedMutableMap.map5900map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5901map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5901map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5901map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5901map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5901map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map5901map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "count"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O",
                            "count"
                        ), listOf(
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPJoinHashMap(
                        dictionary,
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
                            )
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map5900map
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
                                GeneratedMutableMap.map5900map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6334map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6334map
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
                                GeneratedMutableMap.map6334map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X"
                            ), listOf(
                                GeneratedMutableMap.map6334map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6334map
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
                                GeneratedMutableMap.map6334map
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
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7193map,
                                GeneratedMutableMap.map7194map,
                                GeneratedMutableMap.map7195map,
                                GeneratedMutableMap.map7196map
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
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7723map,
                                GeneratedMutableMap.map7723map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7292map,
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map,
                            GeneratedMutableMap.map7292map,
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map
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
                                GeneratedMutableMap.map8505map,
                                GeneratedMutableMap.map8506map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8501map,
                                GeneratedMutableMap.map8502map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8509map,
                            GeneratedMutableMap.map8510map
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
                            GeneratedMutableMap.map8509map,
                            GeneratedMutableMap.map8510map
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
                                GeneratedMutableMap.map8507map,
                                GeneratedMutableMap.map8508map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("price")
                            ), listOf(
                                GeneratedMutableMap.map8503map,
                                GeneratedMutableMap.map8504map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ), listOf(
                            GeneratedMutableMap.map8511map,
                            GeneratedMutableMap.map8512map
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
                            GeneratedMutableMap.map8511map,
                            GeneratedMutableMap.map8512map
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
                                GeneratedMutableMap.map8513map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8509map,
                                GeneratedMutableMap.map8510map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8509map
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
                                GeneratedMutableMap.map8568map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8569map,
                                GeneratedMutableMap.map8570map,
                                GeneratedMutableMap.map8571map,
                                GeneratedMutableMap.map8572map,
                                GeneratedMutableMap.map8573map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8574map
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
                                GeneratedMutableMap.map8665map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map,
                                GeneratedMutableMap.map8673map,
                                GeneratedMutableMap.map8674map,
                                GeneratedMutableMap.map8675map,
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8679map
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
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8685map,
                                GeneratedMutableMap.map8686map,
                                GeneratedMutableMap.map8687map,
                                GeneratedMutableMap.map8688map,
                                GeneratedMutableMap.map8689map
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
                            GeneratedMutableMap.map8690map,
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
                            GeneratedMutableMap.map8702map
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
                            GeneratedMutableMap.map8690map,
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
                            GeneratedMutableMap.map8702map
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
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
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
                            GeneratedMutableMap.map8703map,
                            GeneratedMutableMap.map8704map,
                            GeneratedMutableMap.map8705map,
                            GeneratedMutableMap.map8706map,
                            GeneratedMutableMap.map8707map,
                            GeneratedMutableMap.map8708map,
                            GeneratedMutableMap.map8709map,
                            GeneratedMutableMap.map8710map,
                            GeneratedMutableMap.map8711map,
                            GeneratedMutableMap.map8712map,
                            GeneratedMutableMap.map8713map,
                            GeneratedMutableMap.map8714map,
                            GeneratedMutableMap.map8715map
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
                            GeneratedMutableMap.map8703map,
                            GeneratedMutableMap.map8704map,
                            GeneratedMutableMap.map8705map,
                            GeneratedMutableMap.map8706map,
                            GeneratedMutableMap.map8707map,
                            GeneratedMutableMap.map8708map,
                            GeneratedMutableMap.map8709map,
                            GeneratedMutableMap.map8710map,
                            GeneratedMutableMap.map8711map,
                            GeneratedMutableMap.map8712map,
                            GeneratedMutableMap.map8713map,
                            GeneratedMutableMap.map8714map,
                            GeneratedMutableMap.map8715map
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
                                GeneratedMutableMap.map8804map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map,
                                GeneratedMutableMap.map8673map,
                                GeneratedMutableMap.map8674map,
                                GeneratedMutableMap.map8675map,
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8805map,
                            GeneratedMutableMap.map8679map,
                            GeneratedMutableMap.map8806map
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
                                GeneratedMutableMap.map8891map,
                                GeneratedMutableMap.map8892map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map8669map,
                                GeneratedMutableMap.map8670map,
                                GeneratedMutableMap.map8671map,
                                GeneratedMutableMap.map8672map,
                                GeneratedMutableMap.map8673map,
                                GeneratedMutableMap.map8674map,
                                GeneratedMutableMap.map8675map,
                                GeneratedMutableMap.map8676map,
                                GeneratedMutableMap.map8677map,
                                GeneratedMutableMap.map8678map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o1",
                            "o2",
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map8893map,
                            GeneratedMutableMap.map8894map,
                            GeneratedMutableMap.map8895map,
                            GeneratedMutableMap.map8895map,
                            GeneratedMutableMap.map8896map,
                            GeneratedMutableMap.map8806map
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
                                GeneratedMutableMap.map8956map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                            ), listOf(
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p1",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map8957map
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
                                GeneratedMutableMap.map9038map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o1",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map9039map,
                                GeneratedMutableMap.map9040map,
                                GeneratedMutableMap.map9041map,
                                GeneratedMutableMap.map9042map,
                                GeneratedMutableMap.map9043map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "o2",
                            "s",
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map9044map,
                            GeneratedMutableMap.map9045map,
                            GeneratedMutableMap.map9046map,
                            GeneratedMutableMap.map9047map,
                            GeneratedMutableMap.map9048map
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
                                GeneratedMutableMap.map8680map,
                                GeneratedMutableMap.map8681map,
                                GeneratedMutableMap.map8682map,
                                GeneratedMutableMap.map9052map,
                                GeneratedMutableMap.map8683map,
                                GeneratedMutableMap.map8684map,
                                GeneratedMutableMap.map9053map,
                                GeneratedMutableMap.map9054map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map9049map,
                                GeneratedMutableMap.map9050map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9058map,
                            GeneratedMutableMap.map9059map,
                            GeneratedMutableMap.map9060map,
                            GeneratedMutableMap.map9061map,
                            GeneratedMutableMap.map9062map,
                            GeneratedMutableMap.map9063map,
                            GeneratedMutableMap.map9064map,
                            GeneratedMutableMap.map9065map
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
                            GeneratedMutableMap.map9058map,
                            GeneratedMutableMap.map9059map,
                            GeneratedMutableMap.map9060map,
                            GeneratedMutableMap.map9061map,
                            GeneratedMutableMap.map9062map,
                            GeneratedMutableMap.map9063map,
                            GeneratedMutableMap.map9064map,
                            GeneratedMutableMap.map9065map
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
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map9056map,
                                GeneratedMutableMap.map9057map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map9051map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map9066map,
                            GeneratedMutableMap.map9067map,
                            GeneratedMutableMap.map9068map,
                            GeneratedMutableMap.map9069map,
                            GeneratedMutableMap.map9070map,
                            GeneratedMutableMap.map9071map,
                            GeneratedMutableMap.map9072map,
                            GeneratedMutableMap.map9073map
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
                            GeneratedMutableMap.map9066map,
                            GeneratedMutableMap.map9067map,
                            GeneratedMutableMap.map9068map,
                            GeneratedMutableMap.map9069map,
                            GeneratedMutableMap.map9070map,
                            GeneratedMutableMap.map9071map,
                            GeneratedMutableMap.map9072map,
                            GeneratedMutableMap.map9073map
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
                                GeneratedMutableMap.map9181map,
                                GeneratedMutableMap.map9182map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8509map,
                                GeneratedMutableMap.map8510map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8509map,
                            GeneratedMutableMap.map8510map
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
                                GeneratedMutableMap.map8513map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                            ), listOf(
                                GeneratedMutableMap.map8505map,
                                GeneratedMutableMap.map8506map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title"
                        ), listOf(
                            GeneratedMutableMap.map8505map
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
                                GeneratedMutableMap.map8505map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8501map,
                                GeneratedMutableMap.map8502map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "book",
                            "title",
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8509map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10508map,
                                GeneratedMutableMap.map10509map,
                                GeneratedMutableMap.map10510map,
                                GeneratedMutableMap.map10511map,
                                GeneratedMutableMap.map10512map,
                                GeneratedMutableMap.map10513map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10508map,
                                GeneratedMutableMap.map10509map,
                                GeneratedMutableMap.map10510map,
                                GeneratedMutableMap.map10511map,
                                GeneratedMutableMap.map10512map,
                                GeneratedMutableMap.map10513map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10372map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map10374map,
                                GeneratedMutableMap.map10375map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10372map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map10374map,
                                GeneratedMutableMap.map10375map
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map
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
                                GeneratedMutableMap.map5900map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map
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
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map,
                                GeneratedMutableMap.map11498map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map,
                            GeneratedMutableMap.map11498map
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
                                GeneratedMutableMap.map11658map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                            ), listOf(
                                GeneratedMutableMap.map11386map,
                                GeneratedMutableMap.map11387map,
                                GeneratedMutableMap.map11388map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11386map,
                            GeneratedMutableMap.map11387map
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
                            GeneratedMutableMap.map11386map,
                            GeneratedMutableMap.map11387map
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
                                GeneratedMutableMap.map11659map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map11389map,
                                GeneratedMutableMap.map9051map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("b")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map
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
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map
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
                                GeneratedMutableMap.map11658map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map11892map,
                                GeneratedMutableMap.map11893map,
                                GeneratedMutableMap.map11894map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map11892map,
                            GeneratedMutableMap.map11893map
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
                            GeneratedMutableMap.map11892map,
                            GeneratedMutableMap.map11893map
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
                                GeneratedMutableMap.map11659map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map11389map,
                                GeneratedMutableMap.map9051map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map
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
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map
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
                                GeneratedMutableMap.map11658map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                            ), listOf(
                                GeneratedMutableMap.map11894map
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
                                GeneratedMutableMap.map11659map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                            ), listOf(
                                GeneratedMutableMap.map9051map
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
                                GeneratedMutableMap.map13042map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                                GeneratedMutableMap.map13043map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
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
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
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
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map
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
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
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
                                GeneratedMutableMap.map13443map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map11138map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map11139map,
                                GeneratedMutableMap.map11140map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map
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
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map
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
                                GeneratedMutableMap.map13444map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map11141map,
                                GeneratedMutableMap.map11142map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map11141map,
                            GeneratedMutableMap.map11142map
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
                            GeneratedMutableMap.map11141map,
                            GeneratedMutableMap.map11142map
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
                                GeneratedMutableMap.map13042map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map11138map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map11139map,
                                GeneratedMutableMap.map11140map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                                GeneratedMutableMap.map13043map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map11141map,
                                GeneratedMutableMap.map11142map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
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
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
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
                                GeneratedMutableMap.map11143map,
                                GeneratedMutableMap.map11139map,
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11144map,
                                GeneratedMutableMap.map11145map
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
                                GeneratedMutableMap.map11146map,
                                GeneratedMutableMap.map11141map,
                                GeneratedMutableMap.map11142map,
                                GeneratedMutableMap.map11147map,
                                GeneratedMutableMap.map11148map
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
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map11138map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map,
                                GeneratedMutableMap.map11139map,
                                GeneratedMutableMap.map11140map
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
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map9055map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map,
                                GeneratedMutableMap.map11141map,
                                GeneratedMutableMap.map11142map
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
                                GeneratedMutableMap.map13443map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map11143map,
                                GeneratedMutableMap.map11139map,
                                GeneratedMutableMap.map11140map,
                                GeneratedMutableMap.map11144map,
                                GeneratedMutableMap.map11145map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map11143map,
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map
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
                                GeneratedMutableMap.map14194map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map8575map,
                                GeneratedMutableMap.map8576map,
                                GeneratedMutableMap.map8577map,
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map
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
                                GeneratedMutableMap.map11659map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map,
                                GeneratedMutableMap.map8582map,
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8580map,
                            GeneratedMutableMap.map8581map,
                            GeneratedMutableMap.map8582map
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
                            GeneratedMutableMap.map8580map,
                            GeneratedMutableMap.map8581map,
                            GeneratedMutableMap.map8582map
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
                                GeneratedMutableMap.map16027map,
                                GeneratedMutableMap.map16028map,
                                GeneratedMutableMap.map16029map,
                                GeneratedMutableMap.map16030map,
                                GeneratedMutableMap.map16031map,
                                GeneratedMutableMap.map16032map,
                                GeneratedMutableMap.map16033map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16026map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16030map
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
                                GeneratedMutableMap.map16295map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16295map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16295map
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
                                GeneratedMutableMap.map16489map,
                                GeneratedMutableMap.map16490map,
                                GeneratedMutableMap.map16491map,
                                GeneratedMutableMap.map16492map,
                                GeneratedMutableMap.map16027map,
                                GeneratedMutableMap.map16028map,
                                GeneratedMutableMap.map16029map,
                                GeneratedMutableMap.map16493map,
                                GeneratedMutableMap.map16494map
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
                                GeneratedMutableMap.map16495map,
                                GeneratedMutableMap.map16496map,
                                GeneratedMutableMap.map16497map,
                                GeneratedMutableMap.map16498map,
                                GeneratedMutableMap.map16499map,
                                GeneratedMutableMap.map16500map,
                                GeneratedMutableMap.map16501map,
                                GeneratedMutableMap.map16502map,
                                GeneratedMutableMap.map16503map
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
                                GeneratedMutableMap.map16563map,
                                GeneratedMutableMap.map16564map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16567map,
                                GeneratedMutableMap.map16568map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16563map,
                            GeneratedMutableMap.map16564map
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
                            GeneratedMutableMap.map16563map,
                            GeneratedMutableMap.map16564map
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
                                GeneratedMutableMap.map16565map,
                                GeneratedMutableMap.map16566map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map16569map,
                                GeneratedMutableMap.map16570map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map16565map,
                            GeneratedMutableMap.map16566map
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
                            GeneratedMutableMap.map16565map,
                            GeneratedMutableMap.map16566map
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
                                GeneratedMutableMap.map16781map,
                                GeneratedMutableMap.map16782map,
                                GeneratedMutableMap.map16783map,
                                GeneratedMutableMap.map16784map,
                                GeneratedMutableMap.map16785map,
                                GeneratedMutableMap.map16786map,
                                GeneratedMutableMap.map16787map,
                                GeneratedMutableMap.map16788map,
                                GeneratedMutableMap.map16789map,
                                GeneratedMutableMap.map16790map,
                                GeneratedMutableMap.map16791map,
                                GeneratedMutableMap.map16792map,
                                GeneratedMutableMap.map16793map,
                                GeneratedMutableMap.map16794map,
                                GeneratedMutableMap.map16795map,
                                GeneratedMutableMap.map16796map,
                                GeneratedMutableMap.map16797map,
                                GeneratedMutableMap.map16798map,
                                GeneratedMutableMap.map16799map,
                                GeneratedMutableMap.map16800map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16675"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16675"
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
                                "#_16675",
                                "#_16680"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16780map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c",
                            "#_16675",
                            "#_16680",
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
                                GeneratedMutableMap.map16929map,
                                GeneratedMutableMap.map16930map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16821"
                            ), listOf(
                                GeneratedMutableMap.map16931map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821"
                        ), listOf(
                            GeneratedMutableMap.map16932map,
                            GeneratedMutableMap.map16933map
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
                                "#_16821"
                            ), listOf(
                                GeneratedMutableMap.map16932map,
                                GeneratedMutableMap.map16933map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16821"
                            ), listOf(
                                GeneratedMutableMap.map16931map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821"
                        ), listOf(
                            GeneratedMutableMap.map16932map,
                            GeneratedMutableMap.map16933map
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
                                "#_16821"
                            ), listOf(
                                GeneratedMutableMap.map16932map,
                                GeneratedMutableMap.map16933map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16830"
                            ), listOf(
                                GeneratedMutableMap.map16935map,
                                GeneratedMutableMap.map16936map,
                                GeneratedMutableMap.map16937map,
                                GeneratedMutableMap.map16938map,
                                GeneratedMutableMap.map16939map,
                                GeneratedMutableMap.map16940map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821",
                            "#_16830"
                        ), listOf(
                            GeneratedMutableMap.map16961map,
                            GeneratedMutableMap.map16962map,
                            GeneratedMutableMap.map16963map,
                            GeneratedMutableMap.map16964map,
                            GeneratedMutableMap.map16965map,
                            GeneratedMutableMap.map16966map,
                            GeneratedMutableMap.map16967map,
                            GeneratedMutableMap.map16968map,
                            GeneratedMutableMap.map16969map,
                            GeneratedMutableMap.map16970map,
                            GeneratedMutableMap.map16971map,
                            GeneratedMutableMap.map16972map
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
                                "#_16821",
                                "#_16830"
                            ), listOf(
                                GeneratedMutableMap.map16961map,
                                GeneratedMutableMap.map16962map,
                                GeneratedMutableMap.map16963map,
                                GeneratedMutableMap.map16964map,
                                GeneratedMutableMap.map16965map,
                                GeneratedMutableMap.map16966map,
                                GeneratedMutableMap.map16967map,
                                GeneratedMutableMap.map16968map,
                                GeneratedMutableMap.map16969map,
                                GeneratedMutableMap.map16970map,
                                GeneratedMutableMap.map16971map,
                                GeneratedMutableMap.map16972map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16830"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821",
                            "#_16830"
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
                                "#_16821",
                                "#_16830"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_16821",
                                "#_16830"
                            ), listOf(
                                GeneratedMutableMap.map16934map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821",
                            "#_16830"
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
                                "#_16821",
                                "#_16830"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#b0",
                                "#_16821"
                            ), listOf(
                                GeneratedMutableMap.map16941map,
                                GeneratedMutableMap.map16942map,
                                GeneratedMutableMap.map16943map,
                                GeneratedMutableMap.map16944map,
                                GeneratedMutableMap.map16945map,
                                GeneratedMutableMap.map16946map,
                                GeneratedMutableMap.map16947map,
                                GeneratedMutableMap.map16948map,
                                GeneratedMutableMap.map16949map,
                                GeneratedMutableMap.map16950map,
                                GeneratedMutableMap.map16951map,
                                GeneratedMutableMap.map16952map,
                                GeneratedMutableMap.map16953map,
                                GeneratedMutableMap.map16954map,
                                GeneratedMutableMap.map16955map,
                                GeneratedMutableMap.map16956map,
                                GeneratedMutableMap.map16957map,
                                GeneratedMutableMap.map16958map,
                                GeneratedMutableMap.map16959map,
                                GeneratedMutableMap.map16960map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0",
                            "#_16821",
                            "#_16830"
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
                                GeneratedMutableMap.map17047map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map17048map,
                                GeneratedMutableMap.map17049map,
                                GeneratedMutableMap.map17050map,
                                GeneratedMutableMap.map17051map,
                                GeneratedMutableMap.map17052map,
                                GeneratedMutableMap.map17053map,
                                GeneratedMutableMap.map17054map,
                                GeneratedMutableMap.map17055map,
                                GeneratedMutableMap.map17056map,
                                GeneratedMutableMap.map17057map,
                                GeneratedMutableMap.map17058map,
                                GeneratedMutableMap.map17059map,
                                GeneratedMutableMap.map17060map,
                                GeneratedMutableMap.map17061map,
                                GeneratedMutableMap.map17062map,
                                GeneratedMutableMap.map17063map,
                                GeneratedMutableMap.map17064map,
                                GeneratedMutableMap.map17065map,
                                GeneratedMutableMap.map17066map,
                                GeneratedMutableMap.map17067map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map17048map,
                            GeneratedMutableMap.map17049map
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
                                GeneratedMutableMap.map17048map,
                                GeneratedMutableMap.map17049map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16612map
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
                                GeneratedMutableMap.map17131map,
                                GeneratedMutableMap.map17132map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map17135map,
                                GeneratedMutableMap.map17136map,
                                GeneratedMutableMap.map17137map,
                                GeneratedMutableMap.map17138map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "p",
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map17135map
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
                            GeneratedMutableMap.map17135map
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
                                GeneratedMutableMap.map17133map,
                                GeneratedMutableMap.map17134map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map17139map,
                                GeneratedMutableMap.map17140map,
                                GeneratedMutableMap.map17141map,
                                GeneratedMutableMap.map17142map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map17139map
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
                            GeneratedMutableMap.map17139map
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
                                GeneratedMutableMap.map17290map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map,
                                GeneratedMutableMap.map17291map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17288map
                            )
                        ),
                        false                    ),
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
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map,
                            GeneratedMutableMap.map6720map,
                            GeneratedMutableMap.map6721map
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
                                GeneratedMutableMap.map17292map,
                                GeneratedMutableMap.map6722map,
                                GeneratedMutableMap.map6723map,
                                GeneratedMutableMap.map6724map,
                                GeneratedMutableMap.map6725map,
                                GeneratedMutableMap.map17293map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("p")
                            ), listOf(
                                GeneratedMutableMap.map17289map
                            )
                        ),
                        false                    ),
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
                            GeneratedMutableMap.map6722map,
                            GeneratedMutableMap.map6723map,
                            GeneratedMutableMap.map6724map,
                            GeneratedMutableMap.map6725map
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
                                GeneratedMutableMap.map17290map,
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map,
                                GeneratedMutableMap.map17291map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p"
                            ), listOf(
                                GeneratedMutableMap.map17288map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map6718map,
                                GeneratedMutableMap.map6719map,
                                GeneratedMutableMap.map6720map,
                                GeneratedMutableMap.map6721map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map17780map,
                                GeneratedMutableMap.map7193map,
                                GeneratedMutableMap.map7194map,
                                GeneratedMutableMap.map7195map,
                                GeneratedMutableMap.map7196map,
                                GeneratedMutableMap.map17781map
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
                            GeneratedMutableMap.map17783map,
                            GeneratedMutableMap.map17784map,
                            GeneratedMutableMap.map17785map,
                            GeneratedMutableMap.map17786map,
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
                            GeneratedMutableMap.map7212map,
                            GeneratedMutableMap.map17787map,
                            GeneratedMutableMap.map17788map,
                            GeneratedMutableMap.map17789map,
                            GeneratedMutableMap.map17790map
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
                                GeneratedMutableMap.map17783map,
                                GeneratedMutableMap.map17784map,
                                GeneratedMutableMap.map17785map,
                                GeneratedMutableMap.map17786map,
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
                                GeneratedMutableMap.map7212map,
                                GeneratedMutableMap.map17787map,
                                GeneratedMutableMap.map17788map,
                                GeneratedMutableMap.map17789map,
                                GeneratedMutableMap.map17790map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "p1"
                            ), listOf(
                                GeneratedMutableMap.map17782map
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
                                GeneratedMutableMap.map16027map,
                                GeneratedMutableMap.map16029map,
                                GeneratedMutableMap.map16030map,
                                GeneratedMutableMap.map16493map
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
                                GeneratedMutableMap.map16489map,
                                GeneratedMutableMap.map16491map,
                                GeneratedMutableMap.map16027map,
                                GeneratedMutableMap.map16028map,
                                GeneratedMutableMap.map16029map,
                                GeneratedMutableMap.map16030map,
                                GeneratedMutableMap.map18765map,
                                GeneratedMutableMap.map16031map,
                                GeneratedMutableMap.map18766map,
                                GeneratedMutableMap.map18767map,
                                GeneratedMutableMap.map16494map
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
                                GeneratedMutableMap.map18768map
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
                                GeneratedMutableMap.map18841map,
                                GeneratedMutableMap.map18842map,
                                GeneratedMutableMap.map18843map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                            ), listOf(
                                GeneratedMutableMap.map18846map,
                                GeneratedMutableMap.map18847map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1"
                        ), listOf(
                            GeneratedMutableMap.map18846map,
                            GeneratedMutableMap.map18847map
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
                            GeneratedMutableMap.map18846map,
                            GeneratedMutableMap.map18847map
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
                                GeneratedMutableMap.map18844map,
                                GeneratedMutableMap.map17184map,
                                GeneratedMutableMap.map18845map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                            ), listOf(
                                GeneratedMutableMap.map18848map,
                                GeneratedMutableMap.map18849map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1")
                        ), listOf(
                            GeneratedMutableMap.map18848map,
                            GeneratedMutableMap.map18849map
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
                            GeneratedMutableMap.map18848map,
                            GeneratedMutableMap.map18849map
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
                                GeneratedMutableMap.map18846map,
                                GeneratedMutableMap.map18847map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y2"
                            ), listOf(
                                GeneratedMutableMap.map18850map,
                                GeneratedMutableMap.map18851map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y1",
                            "Y2"
                        ), listOf(
                            GeneratedMutableMap.map18854map,
                            GeneratedMutableMap.map18855map
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
                            GeneratedMutableMap.map18854map,
                            GeneratedMutableMap.map18855map
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
                                GeneratedMutableMap.map18848map,
                                GeneratedMutableMap.map18849map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y2")
                            ), listOf(
                                GeneratedMutableMap.map18852map,
                                GeneratedMutableMap.map18853map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ), listOf(
                            GeneratedMutableMap.map18856map,
                            GeneratedMutableMap.map18857map
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
                            GeneratedMutableMap.map18856map,
                            GeneratedMutableMap.map18857map
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
                                GeneratedMutableMap.map18990map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "#dd"
                            ), listOf(
                                GeneratedMutableMap.map18987map,
                                GeneratedMutableMap.map18988map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map18987map
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
                                GeneratedMutableMap.map18987map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#dd",
                                "#bb"
                            ), listOf(
                                GeneratedMutableMap.map18989map
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
                                GeneratedMutableMap.map18990map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19076map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19083map
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
                            GeneratedMutableMap.map19083map
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
                                GeneratedMutableMap.map19078map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19077map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19084map
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
                            GeneratedMutableMap.map19084map
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
                                GeneratedMutableMap.map19083map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                            ), listOf(
                                GeneratedMutableMap.map19074map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19083map
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
                            GeneratedMutableMap.map19083map
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
                                GeneratedMutableMap.map19084map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("Y"),
                                AOPVariable("#aa")
                            ), listOf(
                                GeneratedMutableMap.map19075map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19084map
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
                            GeneratedMutableMap.map19084map
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
                                GeneratedMutableMap.map19083map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                            ), listOf(
                                GeneratedMutableMap.map19079map,
                                GeneratedMutableMap.map19080map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19085map
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
                            GeneratedMutableMap.map19085map
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
                                GeneratedMutableMap.map19084map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("Z")
                            ), listOf(
                                GeneratedMutableMap.map19081map,
                                GeneratedMutableMap.map19082map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19086map
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
                            GeneratedMutableMap.map19086map
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
                                GeneratedMutableMap.map19143map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19145map,
                                GeneratedMutableMap.map19146map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19147map
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
                            GeneratedMutableMap.map19147map
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
                                GeneratedMutableMap.map19144map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19081map,
                                GeneratedMutableMap.map19082map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19148map
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
                            GeneratedMutableMap.map19148map
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
                                GeneratedMutableMap.map19208map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19210map,
                                GeneratedMutableMap.map19211map,
                                GeneratedMutableMap.map19212map,
                                GeneratedMutableMap.map19213map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19218map,
                            GeneratedMutableMap.map19219map,
                            GeneratedMutableMap.map19220map
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
                            GeneratedMutableMap.map19218map,
                            GeneratedMutableMap.map19219map,
                            GeneratedMutableMap.map19220map
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
                                GeneratedMutableMap.map19209map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19214map,
                                GeneratedMutableMap.map19215map,
                                GeneratedMutableMap.map19216map,
                                GeneratedMutableMap.map19217map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19221map,
                            GeneratedMutableMap.map19222map,
                            GeneratedMutableMap.map19223map
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
                            GeneratedMutableMap.map19221map,
                            GeneratedMutableMap.map19222map,
                            GeneratedMutableMap.map19223map
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
                                GeneratedMutableMap.map19321map
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
                                GeneratedMutableMap.map19317map,
                                GeneratedMutableMap.map19318map,
                                GeneratedMutableMap.map19319map,
                                GeneratedMutableMap.map19320map
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
                                GeneratedMutableMap.map19486map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19487map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19488map
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
                                GeneratedMutableMap.map19488map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19489map,
                                GeneratedMutableMap.map19490map,
                                GeneratedMutableMap.map19491map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "a",
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19488map
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
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19674map,
                                GeneratedMutableMap.map19675map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19674map,
                                GeneratedMutableMap.map19675map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19608"
                        ), listOf(
                            GeneratedMutableMap.map19674map,
                            GeneratedMutableMap.map19675map
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
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19674map,
                                GeneratedMutableMap.map19675map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19674map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19608"
                        ), listOf(
                            GeneratedMutableMap.map19674map
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
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19674map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19608"
                            ), listOf(
                                GeneratedMutableMap.map19676map,
                                GeneratedMutableMap.map19677map,
                                GeneratedMutableMap.map19678map,
                                GeneratedMutableMap.map19679map,
                                GeneratedMutableMap.map19680map,
                                GeneratedMutableMap.map19681map,
                                GeneratedMutableMap.map19682map,
                                GeneratedMutableMap.map19683map,
                                GeneratedMutableMap.map19684map,
                                GeneratedMutableMap.map19685map,
                                GeneratedMutableMap.map19686map,
                                GeneratedMutableMap.map19687map,
                                GeneratedMutableMap.map19688map,
                                GeneratedMutableMap.map19689map,
                                GeneratedMutableMap.map19690map,
                                GeneratedMutableMap.map19691map,
                                GeneratedMutableMap.map19692map,
                                GeneratedMutableMap.map19693map,
                                GeneratedMutableMap.map19694map,
                                GeneratedMutableMap.map19695map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19608",
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
                                "#_19720"
                            ), listOf(
                                GeneratedMutableMap.map19811map,
                                GeneratedMutableMap.map19812map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19720"
                            ), listOf(
                                GeneratedMutableMap.map19811map,
                                GeneratedMutableMap.map19812map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19720"
                        ), listOf(
                            GeneratedMutableMap.map19811map,
                            GeneratedMutableMap.map19812map
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
                                "#_19720"
                            ), listOf(
                                GeneratedMutableMap.map19811map,
                                GeneratedMutableMap.map19812map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19720"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19720"
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
                                "#_19720"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19720"
                            ), listOf(
                                GeneratedMutableMap.map19791map,
                                GeneratedMutableMap.map19792map,
                                GeneratedMutableMap.map19793map,
                                GeneratedMutableMap.map19794map,
                                GeneratedMutableMap.map19795map,
                                GeneratedMutableMap.map19796map,
                                GeneratedMutableMap.map19797map,
                                GeneratedMutableMap.map19798map,
                                GeneratedMutableMap.map19799map,
                                GeneratedMutableMap.map19800map,
                                GeneratedMutableMap.map19801map,
                                GeneratedMutableMap.map19802map,
                                GeneratedMutableMap.map19803map,
                                GeneratedMutableMap.map19804map,
                                GeneratedMutableMap.map19805map,
                                GeneratedMutableMap.map19806map,
                                GeneratedMutableMap.map19807map,
                                GeneratedMutableMap.map19808map,
                                GeneratedMutableMap.map19809map,
                                GeneratedMutableMap.map19810map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19720",
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
                                "#_19834"
                            ), listOf(
                                GeneratedMutableMap.map19920map,
                                GeneratedMutableMap.map19921map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19834"
                            ), listOf(
                                GeneratedMutableMap.map19920map,
                                GeneratedMutableMap.map19921map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19834"
                        ), listOf(
                            GeneratedMutableMap.map19920map,
                            GeneratedMutableMap.map19921map
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
                                "#_19834"
                            ), listOf(
                                GeneratedMutableMap.map19920map,
                                GeneratedMutableMap.map19921map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19834"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19834"
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
                                "#_19834"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19834"
                            ), listOf(
                                GeneratedMutableMap.map19900map,
                                GeneratedMutableMap.map19901map,
                                GeneratedMutableMap.map19902map,
                                GeneratedMutableMap.map19903map,
                                GeneratedMutableMap.map19904map,
                                GeneratedMutableMap.map19905map,
                                GeneratedMutableMap.map19906map,
                                GeneratedMutableMap.map19907map,
                                GeneratedMutableMap.map19908map,
                                GeneratedMutableMap.map19909map,
                                GeneratedMutableMap.map19910map,
                                GeneratedMutableMap.map19911map,
                                GeneratedMutableMap.map19912map,
                                GeneratedMutableMap.map19913map,
                                GeneratedMutableMap.map19914map,
                                GeneratedMutableMap.map19915map,
                                GeneratedMutableMap.map19916map,
                                GeneratedMutableMap.map19917map,
                                GeneratedMutableMap.map19918map,
                                GeneratedMutableMap.map19919map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19834",
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
                                "#_19944"
                            ), listOf(
                                GeneratedMutableMap.map20026map,
                                GeneratedMutableMap.map20027map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19944"
                            ), listOf(
                                GeneratedMutableMap.map20026map,
                                GeneratedMutableMap.map20027map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19944"
                        ), listOf(
                            GeneratedMutableMap.map20026map,
                            GeneratedMutableMap.map20027map
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
                                "#_19944"
                            ), listOf(
                                GeneratedMutableMap.map20026map,
                                GeneratedMutableMap.map20027map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_19944"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19944"
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
                                "#_19944"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_19944"
                            ), listOf(
                                GeneratedMutableMap.map20028map,
                                GeneratedMutableMap.map20029map,
                                GeneratedMutableMap.map20030map,
                                GeneratedMutableMap.map20031map,
                                GeneratedMutableMap.map20032map,
                                GeneratedMutableMap.map20033map,
                                GeneratedMutableMap.map20034map,
                                GeneratedMutableMap.map20035map,
                                GeneratedMutableMap.map20036map,
                                GeneratedMutableMap.map20037map,
                                GeneratedMutableMap.map20038map,
                                GeneratedMutableMap.map20039map,
                                GeneratedMutableMap.map20040map,
                                GeneratedMutableMap.map20041map,
                                GeneratedMutableMap.map20042map,
                                GeneratedMutableMap.map20043map,
                                GeneratedMutableMap.map20044map,
                                GeneratedMutableMap.map20045map,
                                GeneratedMutableMap.map20046map,
                                GeneratedMutableMap.map20047map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_19944",
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
                                "#_20070"
                            ), listOf(
                                GeneratedMutableMap.map20172map,
                                GeneratedMutableMap.map20173map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20070"
                            ), listOf(
                                GeneratedMutableMap.map20172map,
                                GeneratedMutableMap.map20173map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20070"
                        ), listOf(
                            GeneratedMutableMap.map20172map,
                            GeneratedMutableMap.map20173map
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
                                "#_20070"
                            ), listOf(
                                GeneratedMutableMap.map20172map,
                                GeneratedMutableMap.map20173map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20070"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20070"
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
                                "#_20070"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20070"
                            ), listOf(
                                GeneratedMutableMap.map20152map,
                                GeneratedMutableMap.map20153map,
                                GeneratedMutableMap.map20154map,
                                GeneratedMutableMap.map20155map,
                                GeneratedMutableMap.map20156map,
                                GeneratedMutableMap.map20157map,
                                GeneratedMutableMap.map20158map,
                                GeneratedMutableMap.map20159map,
                                GeneratedMutableMap.map20160map,
                                GeneratedMutableMap.map20161map,
                                GeneratedMutableMap.map20162map,
                                GeneratedMutableMap.map20163map,
                                GeneratedMutableMap.map20164map,
                                GeneratedMutableMap.map20165map,
                                GeneratedMutableMap.map20166map,
                                GeneratedMutableMap.map20167map,
                                GeneratedMutableMap.map20168map,
                                GeneratedMutableMap.map20169map,
                                GeneratedMutableMap.map20170map,
                                GeneratedMutableMap.map20171map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20070",
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
                                "#_20196"
                            ), listOf(
                                GeneratedMutableMap.map20298map,
                                GeneratedMutableMap.map20299map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20196"
                            ), listOf(
                                GeneratedMutableMap.map20298map,
                                GeneratedMutableMap.map20299map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20196"
                        ), listOf(
                            GeneratedMutableMap.map20298map,
                            GeneratedMutableMap.map20299map
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
                                "#_20196"
                            ), listOf(
                                GeneratedMutableMap.map20298map,
                                GeneratedMutableMap.map20299map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20196"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20196"
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
                                "#_20196"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "#_20196"
                            ), listOf(
                                GeneratedMutableMap.map20278map,
                                GeneratedMutableMap.map20279map,
                                GeneratedMutableMap.map20280map,
                                GeneratedMutableMap.map20281map,
                                GeneratedMutableMap.map20282map,
                                GeneratedMutableMap.map20283map,
                                GeneratedMutableMap.map20284map,
                                GeneratedMutableMap.map20285map,
                                GeneratedMutableMap.map20286map,
                                GeneratedMutableMap.map20287map,
                                GeneratedMutableMap.map20288map,
                                GeneratedMutableMap.map20289map,
                                GeneratedMutableMap.map20290map,
                                GeneratedMutableMap.map20291map,
                                GeneratedMutableMap.map20292map,
                                GeneratedMutableMap.map20293map,
                                GeneratedMutableMap.map20294map,
                                GeneratedMutableMap.map20295map,
                                GeneratedMutableMap.map20296map,
                                GeneratedMutableMap.map20297map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20196",
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
                                "#_20323"
                            ), listOf(
                                GeneratedMutableMap.map20391map,
                                GeneratedMutableMap.map20392map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20323"
                            ), listOf(
                                GeneratedMutableMap.map20391map,
                                GeneratedMutableMap.map20392map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20323"
                        ), listOf(
                            GeneratedMutableMap.map20391map,
                            GeneratedMutableMap.map20392map
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
                                "#_20323"
                            ), listOf(
                                GeneratedMutableMap.map20391map,
                                GeneratedMutableMap.map20392map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20323"
                            ), listOf(
                                GeneratedMutableMap.map20391map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20323"
                        ), listOf(
                            GeneratedMutableMap.map20391map
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
                                "#_20323"
                            ), listOf(
                                GeneratedMutableMap.map20391map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "C",
                                "#_20323"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20323",
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
                                GeneratedMutableMap.map20497map,
                                GeneratedMutableMap.map20498map
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
                                GeneratedMutableMap.map20497map
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
                                "#_20515",
                                "#_20520",
                                "#_20512"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20512"
                            ), listOf(
                                GeneratedMutableMap.map20604map,
                                GeneratedMutableMap.map20605map,
                                GeneratedMutableMap.map20606map,
                                GeneratedMutableMap.map20607map,
                                GeneratedMutableMap.map20608map,
                                GeneratedMutableMap.map20609map,
                                GeneratedMutableMap.map20610map,
                                GeneratedMutableMap.map20611map,
                                GeneratedMutableMap.map20612map,
                                GeneratedMutableMap.map20613map,
                                GeneratedMutableMap.map20614map,
                                GeneratedMutableMap.map20615map,
                                GeneratedMutableMap.map20616map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20515",
                            "#_20520",
                            "#_20512",
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
                                "#_20633",
                                "#_20637",
                                "#_20647",
                                "#_20630"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20630"
                            ), listOf(
                                GeneratedMutableMap.map20761map,
                                GeneratedMutableMap.map20762map,
                                GeneratedMutableMap.map20763map,
                                GeneratedMutableMap.map20764map,
                                GeneratedMutableMap.map20765map,
                                GeneratedMutableMap.map20766map,
                                GeneratedMutableMap.map20767map,
                                GeneratedMutableMap.map20768map,
                                GeneratedMutableMap.map20769map,
                                GeneratedMutableMap.map20770map,
                                GeneratedMutableMap.map20771map,
                                GeneratedMutableMap.map20772map,
                                GeneratedMutableMap.map20773map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20633",
                            "#_20637",
                            "#_20647",
                            "#_20630",
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
                                "#_20787",
                                "#_20798",
                                "#_20803",
                                "#_20795"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20787"
                            ), listOf(
                                GeneratedMutableMap.map20918map,
                                GeneratedMutableMap.map20919map,
                                GeneratedMutableMap.map20920map,
                                GeneratedMutableMap.map20921map,
                                GeneratedMutableMap.map20922map,
                                GeneratedMutableMap.map20923map,
                                GeneratedMutableMap.map20924map,
                                GeneratedMutableMap.map20925map,
                                GeneratedMutableMap.map20926map,
                                GeneratedMutableMap.map20927map,
                                GeneratedMutableMap.map20928map,
                                GeneratedMutableMap.map20929map,
                                GeneratedMutableMap.map20930map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20787",
                            "#_20798",
                            "#_20803",
                            "#_20795",
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
                                "#_20944"
                            ), listOf(
                                GeneratedMutableMap.map21048map,
                                GeneratedMutableMap.map21049map,
                                GeneratedMutableMap.map21050map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_20949"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20944",
                            "#_20949"
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
                                "#_20944",
                                "#_20949",
                                "#_20954"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_20944"
                            ), listOf(
                                GeneratedMutableMap.map21051map,
                                GeneratedMutableMap.map21052map,
                                GeneratedMutableMap.map21053map,
                                GeneratedMutableMap.map21054map,
                                GeneratedMutableMap.map21055map,
                                GeneratedMutableMap.map21056map,
                                GeneratedMutableMap.map21057map,
                                GeneratedMutableMap.map21058map,
                                GeneratedMutableMap.map21059map,
                                GeneratedMutableMap.map21060map,
                                GeneratedMutableMap.map21061map,
                                GeneratedMutableMap.map21062map,
                                GeneratedMutableMap.map21063map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_20944",
                            "#_20949",
                            "#_20954",
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
                                "#_21077"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21085"
                            ), listOf(
                                GeneratedMutableMap.map21233map,
                                GeneratedMutableMap.map21234map,
                                GeneratedMutableMap.map21235map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21077",
                            "#_21085"
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
                                "#_21077",
                                "#_21085",
                                "#_21090",
                                "#_21095"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21077"
                            ), listOf(
                                GeneratedMutableMap.map21220map,
                                GeneratedMutableMap.map21221map,
                                GeneratedMutableMap.map21222map,
                                GeneratedMutableMap.map21223map,
                                GeneratedMutableMap.map21224map,
                                GeneratedMutableMap.map21225map,
                                GeneratedMutableMap.map21226map,
                                GeneratedMutableMap.map21227map,
                                GeneratedMutableMap.map21228map,
                                GeneratedMutableMap.map21229map,
                                GeneratedMutableMap.map21230map,
                                GeneratedMutableMap.map21231map,
                                GeneratedMutableMap.map21232map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21077",
                            "#_21085",
                            "#_21090",
                            "#_21095",
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
                                "#_21249"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21257"
                            ), listOf(
                                GeneratedMutableMap.map21430map,
                                GeneratedMutableMap.map21431map,
                                GeneratedMutableMap.map21432map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21249",
                            "#_21257"
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
                                "#_21249",
                                "#_21257",
                                "#_21262",
                                "#_21267",
                                "#_21272"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21249"
                            ), listOf(
                                GeneratedMutableMap.map21417map,
                                GeneratedMutableMap.map21418map,
                                GeneratedMutableMap.map21419map,
                                GeneratedMutableMap.map21420map,
                                GeneratedMutableMap.map21421map,
                                GeneratedMutableMap.map21422map,
                                GeneratedMutableMap.map21423map,
                                GeneratedMutableMap.map21424map,
                                GeneratedMutableMap.map21425map,
                                GeneratedMutableMap.map21426map,
                                GeneratedMutableMap.map21427map,
                                GeneratedMutableMap.map21428map,
                                GeneratedMutableMap.map21429map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21249",
                            "#_21257",
                            "#_21262",
                            "#_21267",
                            "#_21272",
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
                                "#_21446"
                            ), listOf(
                                GeneratedMutableMap.map21625map,
                                GeneratedMutableMap.map21626map,
                                GeneratedMutableMap.map21627map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21451"
                            ), listOf(
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21446",
                            "#_21451"
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
                                "#_21446",
                                "#_21451"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "#_21455"
                            ), listOf(
                                GeneratedMutableMap.map21641map,
                                GeneratedMutableMap.map21642map,
                                GeneratedMutableMap.map21643map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21446",
                            "#_21451",
                            "#_21455"
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
                                "#_21446",
                                "#_21451",
                                "#_21455",
                                "#_21460",
                                "#_21465",
                                "#_21471"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21446"
                            ), listOf(
                                GeneratedMutableMap.map21628map,
                                GeneratedMutableMap.map21629map,
                                GeneratedMutableMap.map21630map,
                                GeneratedMutableMap.map21631map,
                                GeneratedMutableMap.map21632map,
                                GeneratedMutableMap.map21633map,
                                GeneratedMutableMap.map21634map,
                                GeneratedMutableMap.map21635map,
                                GeneratedMutableMap.map21636map,
                                GeneratedMutableMap.map21637map,
                                GeneratedMutableMap.map21638map,
                                GeneratedMutableMap.map21639map,
                                GeneratedMutableMap.map21640map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21446",
                            "#_21451",
                            "#_21455",
                            "#_21460",
                            "#_21465",
                            "#_21471",
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
                                "#_21657",
                                "#_21665"
                            ), listOf(
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#_21657"
                            ), listOf(
                                GeneratedMutableMap.map21764map,
                                GeneratedMutableMap.map21765map,
                                GeneratedMutableMap.map21766map,
                                GeneratedMutableMap.map21767map,
                                GeneratedMutableMap.map21768map,
                                GeneratedMutableMap.map21769map,
                                GeneratedMutableMap.map21770map,
                                GeneratedMutableMap.map21771map,
                                GeneratedMutableMap.map21772map,
                                GeneratedMutableMap.map21773map,
                                GeneratedMutableMap.map21774map,
                                GeneratedMutableMap.map21775map,
                                GeneratedMutableMap.map21776map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_21657",
                            "#_21665",
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
                                GeneratedMutableMap.map24778map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map24780map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "str1",
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map24782map
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
                            GeneratedMutableMap.map24782map
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
                                GeneratedMutableMap.map24779map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map24781map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("str1"),
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map24783map
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
                            GeneratedMutableMap.map24783map
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
                                GeneratedMutableMap.map25392map,
                                GeneratedMutableMap.map25393map,
                                GeneratedMutableMap.map25394map,
                                GeneratedMutableMap.map25395map,
                                GeneratedMutableMap.map25396map,
                                GeneratedMutableMap.map25397map,
                                GeneratedMutableMap.map25398map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s2",
                                "str2"
                            ), listOf(
                                GeneratedMutableMap.map25378map,
                                GeneratedMutableMap.map25379map,
                                GeneratedMutableMap.map25380map,
                                GeneratedMutableMap.map25381map,
                                GeneratedMutableMap.map25382map,
                                GeneratedMutableMap.map25383map,
                                GeneratedMutableMap.map25384map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map,
                                GeneratedMutableMap.map25391map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s2"),
                                AOPVariable("str2")
                            ), listOf(
                                GeneratedMutableMap.map25385map,
                                GeneratedMutableMap.map25386map,
                                GeneratedMutableMap.map25387map,
                                GeneratedMutableMap.map25388map,
                                GeneratedMutableMap.map25389map,
                                GeneratedMutableMap.map25390map,
                                GeneratedMutableMap.map25391map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map27304map,
                                GeneratedMutableMap.map27305map,
                                GeneratedMutableMap.map27306map,
                                GeneratedMutableMap.map27307map,
                                GeneratedMutableMap.map27308map,
                                GeneratedMutableMap.map27309map,
                                GeneratedMutableMap.map27310map,
                                GeneratedMutableMap.map27311map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map27288map,
                                GeneratedMutableMap.map27289map,
                                GeneratedMutableMap.map27290map,
                                GeneratedMutableMap.map27291map,
                                GeneratedMutableMap.map27292map,
                                GeneratedMutableMap.map27293map,
                                GeneratedMutableMap.map27294map,
                                GeneratedMutableMap.map27295map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map27312map,
                                GeneratedMutableMap.map27313map,
                                GeneratedMutableMap.map27314map,
                                GeneratedMutableMap.map27315map,
                                GeneratedMutableMap.map27316map,
                                GeneratedMutableMap.map27317map,
                                GeneratedMutableMap.map27318map,
                                GeneratedMutableMap.map27319map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map27296map,
                                GeneratedMutableMap.map27297map,
                                GeneratedMutableMap.map27298map,
                                GeneratedMutableMap.map27299map,
                                GeneratedMutableMap.map27300map,
                                GeneratedMutableMap.map27301map,
                                GeneratedMutableMap.map27302map,
                                GeneratedMutableMap.map27303map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map31123map,
                                GeneratedMutableMap.map31124map,
                                GeneratedMutableMap.map31125map,
                                GeneratedMutableMap.map31126map,
                                GeneratedMutableMap.map31127map,
                                GeneratedMutableMap.map31128map,
                                GeneratedMutableMap.map31129map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "b",
                                "s2"
                            ), listOf(
                                GeneratedMutableMap.map31130map,
                                GeneratedMutableMap.map31131map,
                                GeneratedMutableMap.map31132map,
                                GeneratedMutableMap.map31133map,
                                GeneratedMutableMap.map31134map,
                                GeneratedMutableMap.map31135map,
                                GeneratedMutableMap.map31136map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map22842map,
                                GeneratedMutableMap.map22843map,
                                GeneratedMutableMap.map22844map,
                                GeneratedMutableMap.map22845map,
                                GeneratedMutableMap.map22846map,
                                GeneratedMutableMap.map22847map,
                                GeneratedMutableMap.map22848map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("b"),
                                AOPVariable("s2")
                            ), listOf(
                                GeneratedMutableMap.map22842map,
                                GeneratedMutableMap.map22843map,
                                GeneratedMutableMap.map22844map,
                                GeneratedMutableMap.map22845map,
                                GeneratedMutableMap.map22846map,
                                GeneratedMutableMap.map22847map,
                                GeneratedMutableMap.map22848map
                            )
                        ),
                        false                    ),
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
                                GeneratedMutableMap.map32599map,
                                GeneratedMutableMap.map32600map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32734map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32736map,
                            GeneratedMutableMap.map32737map
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
                            GeneratedMutableMap.map32736map,
                            GeneratedMutableMap.map32737map
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
                                GeneratedMutableMap.map32601map,
                                GeneratedMutableMap.map32602map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32735map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32738map,
                            GeneratedMutableMap.map32739map
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
                            GeneratedMutableMap.map32738map,
                            GeneratedMutableMap.map32739map
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
                                GeneratedMutableMap.map32599map,
                                GeneratedMutableMap.map32600map,
                                GeneratedMutableMap.map32881map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32734map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32736map,
                            GeneratedMutableMap.map32737map,
                            GeneratedMutableMap.map32883map
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
                            GeneratedMutableMap.map32736map,
                            GeneratedMutableMap.map32737map,
                            GeneratedMutableMap.map32883map
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
                                GeneratedMutableMap.map32601map,
                                GeneratedMutableMap.map32602map,
                                GeneratedMutableMap.map32882map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                            ), listOf(
                                GeneratedMutableMap.map32735map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32738map,
                            GeneratedMutableMap.map32739map,
                            GeneratedMutableMap.map32884map
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
                            GeneratedMutableMap.map32738map,
                            GeneratedMutableMap.map32739map,
                            GeneratedMutableMap.map32884map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map33159map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map33160map,
                                GeneratedMutableMap.map10293map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10508map,
                                GeneratedMutableMap.map10509map,
                                GeneratedMutableMap.map33323map,
                                GeneratedMutableMap.map10511map,
                                GeneratedMutableMap.map33324map,
                                GeneratedMutableMap.map10513map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map33161map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map33162map,
                                GeneratedMutableMap.map10375map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map33161map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map33162map,
                                GeneratedMutableMap.map10375map
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map
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
                                GeneratedMutableMap.map35131map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35133map,
                                GeneratedMutableMap.map35134map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35135map,
                            GeneratedMutableMap.map35136map
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
                            GeneratedMutableMap.map35135map,
                            GeneratedMutableMap.map35136map
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
                                GeneratedMutableMap.map35132map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map35132map,
                                GeneratedMutableMap.map35137map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35138map,
                            GeneratedMutableMap.map35139map
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
                            GeneratedMutableMap.map35138map,
                            GeneratedMutableMap.map35139map
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
                                GeneratedMutableMap.map35131map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35133map,
                                GeneratedMutableMap.map35258map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35135map,
                            GeneratedMutableMap.map35259map
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
                            GeneratedMutableMap.map35135map,
                            GeneratedMutableMap.map35259map
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
                                GeneratedMutableMap.map35132map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map35132map,
                                GeneratedMutableMap.map35260map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35138map,
                            GeneratedMutableMap.map35261map
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
                            GeneratedMutableMap.map35138map,
                            GeneratedMutableMap.map35261map
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
                                GeneratedMutableMap.map35131map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map35134map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35136map
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
                                GeneratedMutableMap.map35131map,
                                GeneratedMutableMap.map35759map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                            ), listOf(
                                GeneratedMutableMap.map35761map
                            )
                        ),
                        true                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map35762map,
                            GeneratedMutableMap.map35763map
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
                            GeneratedMutableMap.map35762map,
                            GeneratedMutableMap.map35763map
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
                                GeneratedMutableMap.map35132map,
                                GeneratedMutableMap.map35760map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                            ), listOf(
                                GeneratedMutableMap.map35137map
                            )
                        ),
                        true                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35139map,
                            GeneratedMutableMap.map35764map
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
                            GeneratedMutableMap.map35139map,
                            GeneratedMutableMap.map35764map
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
                                "#_37075",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37221map,
                                GeneratedMutableMap.map37222map,
                                GeneratedMutableMap.map37223map,
                                GeneratedMutableMap.map37224map,
                                GeneratedMutableMap.map37225map,
                                GeneratedMutableMap.map37226map,
                                GeneratedMutableMap.map37227map,
                                GeneratedMutableMap.map37228map,
                                GeneratedMutableMap.map37229map,
                                GeneratedMutableMap.map37230map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37075"
                            ), listOf(
                                GeneratedMutableMap.map37201map,
                                GeneratedMutableMap.map37202map,
                                GeneratedMutableMap.map37203map,
                                GeneratedMutableMap.map37204map,
                                GeneratedMutableMap.map37205map,
                                GeneratedMutableMap.map37206map,
                                GeneratedMutableMap.map37207map,
                                GeneratedMutableMap.map37208map,
                                GeneratedMutableMap.map37209map,
                                GeneratedMutableMap.map37210map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37243map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37246map,
                            GeneratedMutableMap.map37247map,
                            GeneratedMutableMap.map37248map,
                            GeneratedMutableMap.map37249map,
                            GeneratedMutableMap.map37250map
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
                                        graph.addData(1L,listOf("_:_37052","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37053","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37054","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37055","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37056","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37057","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37058","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37059","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37060","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37061","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37075","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37052"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37053"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37054"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37055"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37056"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37057"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37058"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37059"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37060"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37061"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37075",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37243map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37246map,
                            GeneratedMutableMap.map37247map,
                            GeneratedMutableMap.map37248map,
                            GeneratedMutableMap.map37249map,
                            GeneratedMutableMap.map37250map
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
                                AOPVariable("#_37075"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37231map,
                                GeneratedMutableMap.map37232map,
                                GeneratedMutableMap.map37233map,
                                GeneratedMutableMap.map37234map,
                                GeneratedMutableMap.map37235map,
                                GeneratedMutableMap.map37236map,
                                GeneratedMutableMap.map37237map,
                                GeneratedMutableMap.map37238map,
                                GeneratedMutableMap.map37239map,
                                GeneratedMutableMap.map37240map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37075")
                            ), listOf(
                                GeneratedMutableMap.map37211map,
                                GeneratedMutableMap.map37212map,
                                GeneratedMutableMap.map37213map,
                                GeneratedMutableMap.map37214map,
                                GeneratedMutableMap.map37215map,
                                GeneratedMutableMap.map37216map,
                                GeneratedMutableMap.map37217map,
                                GeneratedMutableMap.map37218map,
                                GeneratedMutableMap.map37219map,
                                GeneratedMutableMap.map37220map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37253map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37256map,
                            GeneratedMutableMap.map37257map,
                            GeneratedMutableMap.map37258map,
                            GeneratedMutableMap.map37259map,
                            GeneratedMutableMap.map37260map
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
                                        graph.addData(1L,listOf("_:_37052","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37053","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37054","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37055","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37056","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37057","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37058","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37059","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37060","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37061","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_37075"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37052"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37053"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37054"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37055"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37056"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37057"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37058"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37059"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37060"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37061"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37075"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37253map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37256map,
                            GeneratedMutableMap.map37257map,
                            GeneratedMutableMap.map37258map,
                            GeneratedMutableMap.map37259map,
                            GeneratedMutableMap.map37260map
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
                                "#_37075",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37241map,
                                GeneratedMutableMap.map37242map,
                                GeneratedMutableMap.map37243map,
                                GeneratedMutableMap.map37244map,
                                GeneratedMutableMap.map37245map,
                                GeneratedMutableMap.map37246map,
                                GeneratedMutableMap.map37247map,
                                GeneratedMutableMap.map37248map,
                                GeneratedMutableMap.map37249map,
                                GeneratedMutableMap.map37250map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37191map,
                                GeneratedMutableMap.map37192map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37243map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37246map
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
                                                        graph.addData(1L,listOf("_:_37052","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37053","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37054","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37055","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37056","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37057","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37058","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37059","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37060","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37061","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37075","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37052"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37053"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37054"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37055"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37056"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37057"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37058"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37059"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37060"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37061"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37075",false,true,false,EIndexPattern.SPO)
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
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37243map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37246map
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
                                AOPVariable("#_37075"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37251map,
                                GeneratedMutableMap.map37252map,
                                GeneratedMutableMap.map37253map,
                                GeneratedMutableMap.map37254map,
                                GeneratedMutableMap.map37255map,
                                GeneratedMutableMap.map37256map,
                                GeneratedMutableMap.map37257map,
                                GeneratedMutableMap.map37258map,
                                GeneratedMutableMap.map37259map,
                                GeneratedMutableMap.map37260map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37195map,
                                GeneratedMutableMap.map37196map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37253map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37256map
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
                                                        graph.addData(1L,listOf("_:_37052","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37053","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37054","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37055","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37056","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37057","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37058","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37059","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37060","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37061","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_37075"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37052"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37053"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37054"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37055"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37056"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37057"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37058"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37059"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37060"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37061"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37075"),graphName,false)                                                    }()
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
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37253map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37256map
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
                                GeneratedMutableMap.map37489map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37487map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "F",
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37491map
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
                            GeneratedMutableMap.map37491map
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
                                GeneratedMutableMap.map37490map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37488map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F"),
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37492map
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
                            GeneratedMutableMap.map37492map
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
                                "#_37526",
                                "L"
                            ), listOf(
                                GeneratedMutableMap.map37642map,
                                GeneratedMutableMap.map37643map,
                                GeneratedMutableMap.map37644map,
                                GeneratedMutableMap.map37645map,
                                GeneratedMutableMap.map37646map,
                                GeneratedMutableMap.map37647map,
                                GeneratedMutableMap.map37648map,
                                GeneratedMutableMap.map37649map,
                                GeneratedMutableMap.map37650map,
                                GeneratedMutableMap.map37651map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37526"
                            ), listOf(
                                GeneratedMutableMap.map37662map,
                                GeneratedMutableMap.map37663map,
                                GeneratedMutableMap.map37664map,
                                GeneratedMutableMap.map37665map,
                                GeneratedMutableMap.map37666map,
                                GeneratedMutableMap.map37667map,
                                GeneratedMutableMap.map37668map,
                                GeneratedMutableMap.map37669map,
                                GeneratedMutableMap.map37670map,
                                GeneratedMutableMap.map37671map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37674map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37677map,
                            GeneratedMutableMap.map37678map,
                            GeneratedMutableMap.map37679map,
                            GeneratedMutableMap.map37680map,
                            GeneratedMutableMap.map37681map
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
                                        graph.addData(1L,listOf("_:_37503","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37504","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37505","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37506","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37507","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37508","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37509","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37510","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37511","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37512","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37526","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37503"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37504"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37505"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37506"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37507"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37508"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37509"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37510"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37511"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37512"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37526",false,true,false,EIndexPattern.SPO)
                                    }()
,
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37674map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37677map,
                            GeneratedMutableMap.map37678map,
                            GeneratedMutableMap.map37679map,
                            GeneratedMutableMap.map37680map,
                            GeneratedMutableMap.map37681map
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
                                AOPVariable("#_37526"),
                                AOPVariable("L")
                            ), listOf(
                                GeneratedMutableMap.map37652map,
                                GeneratedMutableMap.map37653map,
                                GeneratedMutableMap.map37654map,
                                GeneratedMutableMap.map37655map,
                                GeneratedMutableMap.map37656map,
                                GeneratedMutableMap.map37657map,
                                GeneratedMutableMap.map37658map,
                                GeneratedMutableMap.map37659map,
                                GeneratedMutableMap.map37660map,
                                GeneratedMutableMap.map37661map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37526")
                            ), listOf(
                                GeneratedMutableMap.map37682map,
                                GeneratedMutableMap.map37683map,
                                GeneratedMutableMap.map37684map,
                                GeneratedMutableMap.map37685map,
                                GeneratedMutableMap.map37686map,
                                GeneratedMutableMap.map37687map,
                                GeneratedMutableMap.map37688map,
                                GeneratedMutableMap.map37689map,
                                GeneratedMutableMap.map37690map,
                                GeneratedMutableMap.map37691map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37694map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37697map,
                            GeneratedMutableMap.map37698map,
                            GeneratedMutableMap.map37699map,
                            GeneratedMutableMap.map37700map,
                            GeneratedMutableMap.map37701map
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
                                        graph.addData(1L,listOf("_:_37503","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37504","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                        graph.addData(1L,listOf("_:_37505","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                        graph.addData(1L,listOf("_:_37506","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                        graph.addData(1L,listOf("_:_37507","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                        graph.addData(1L,listOf("_:_37508","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37509","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                        graph.addData(1L,listOf("_:_37510","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        graph.addData(1L,listOf("_:_37511","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                        graph.addData(1L,listOf("_:_37512","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("#_37526"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                    }()
,
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37503"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37504"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37505"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37506"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37507"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37508"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37509"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37510"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37511"))
                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37512"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37526"),graphName,false)                                    }()
,
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37694map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37697map,
                            GeneratedMutableMap.map37698map,
                            GeneratedMutableMap.map37699map,
                            GeneratedMutableMap.map37700map,
                            GeneratedMutableMap.map37701map
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
                                "#_37526",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37672map,
                                GeneratedMutableMap.map37673map,
                                GeneratedMutableMap.map37674map,
                                GeneratedMutableMap.map37675map,
                                GeneratedMutableMap.map37676map,
                                GeneratedMutableMap.map37677map,
                                GeneratedMutableMap.map37678map,
                                GeneratedMutableMap.map37679map,
                                GeneratedMutableMap.map37680map,
                                GeneratedMutableMap.map37681map
                            )
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37191map,
                                GeneratedMutableMap.map37192map
                            )
                        ),
                        false                    ),
                    POPValues(dictionary, listOf(
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37674map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37677map
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
                                                        graph.addData(1L,listOf("_:_37503","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37504","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37505","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37506","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37507","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37508","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37509","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37510","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37511","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37512","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37526","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37503"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37504"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37505"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37506"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37507"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37508"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37509"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37510"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37511"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37512"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37526",false,true,false,EIndexPattern.SPO)
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
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37674map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37677map
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
                                AOPVariable("#_37526"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37692map,
                                GeneratedMutableMap.map37693map,
                                GeneratedMutableMap.map37694map,
                                GeneratedMutableMap.map37695map,
                                GeneratedMutableMap.map37696map,
                                GeneratedMutableMap.map37697map,
                                GeneratedMutableMap.map37698map,
                                GeneratedMutableMap.map37699map,
                                GeneratedMutableMap.map37700map,
                                GeneratedMutableMap.map37701map
                            )
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37195map,
                                GeneratedMutableMap.map37196map
                            )
                        ),
                        false                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37694map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37697map
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
                                                        graph.addData(1L,listOf("_:_37503","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37504","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                        graph.addData(1L,listOf("_:_37505","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                        graph.addData(1L,listOf("_:_37506","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                        graph.addData(1L,listOf("_:_37507","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                        graph.addData(1L,listOf("_:_37508","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37509","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                        graph.addData(1L,listOf("_:_37510","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        graph.addData(1L,listOf("_:_37511","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                        graph.addData(1L,listOf("_:_37512","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#_37526"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37503"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37504"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37505"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37506"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37507"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37508"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37509"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37510"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37511"))
                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37512"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37526"),graphName,false)                                                    }()
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
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37694map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37697map
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
