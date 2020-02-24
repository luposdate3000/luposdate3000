package lupos

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


class GeneratedTripleStoreIteratorGlobalTest {
    constructor(){
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
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/william>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/givenName>","\"William\""))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bill@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"William\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:bill@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/givenName>","\"William\""))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bill@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"John\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:johnny@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"William\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/william>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:bill@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"John\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:johnny@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/sue>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/sue>","<http://xmlns.com/foaf/0.1/givenName>","\"Susan\""))
                        graph.addData(1L,listOf("<http://example.org/sue>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:sue@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"John\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:johnny@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/sue>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/sue>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"Susan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/sue>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:sue@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-03.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/sue>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"John\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/sue>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/john>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:johnny@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o1>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o2>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o3>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p2>","<http://www.example.org/o1>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p2>","<http://www.example.org/o2>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","P","O",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "P" to "<http://www.example.org/p1>",
                                "O" to "<http://www.example.org/o1>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "P" to "<http://www.example.org/p1>",
                                "O" to "<http://www.example.org/o2>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "P" to "<http://www.example.org/p1>",
                                "O" to "<http://www.example.org/o3>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "P" to "<http://www.example.org/p2>",
                                "O" to "<http://www.example.org/o1>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "P" to "<http://www.example.org/p2>",
                                "O" to "<http://www.example.org/o2>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/q>","O2",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "#p2397",
                            "O2"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2397" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2397" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2397" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/p>","O1",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "#p2396",
                            "O1"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2396" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/q>","O2",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "#p2659",
                            "O2"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2659" to null,
                                "O2" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2659" to null,
                                "O2" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2659" to null,
                                "O2" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","<http://www.example.org/p>","O1",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "#p2658",
                            "O1"
                        ), listOf(
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "S" to "<http://www.example.org/s>",
                                "#p2658" to null,
                                "O1" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p3395",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3395" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3395" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3395" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p3395" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p3395" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/int>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/double>","\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p3655",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3655" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3655" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3655" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p3655" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p3655" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p3708",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3708" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3708" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p3708" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p3708" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p3708" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p4133",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4133" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4133" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4133" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p4133" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p4133" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p4181",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4181" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4181" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4181" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p4181" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p4181" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/doubles>","<http://www.example.org/double>","\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/ints>","<http://www.example.org/int>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/int>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/double>","\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/doubles>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/ints>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "p" to "<http://www.example.org/int>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "p" to "<http://www.example.org/dec>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "p" to "<http://www.example.org/double>",
                                "o" to "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/decimals>","<http://www.example.org/dec>","\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed1>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://www.example.org/mixed2>","<http://www.example.org/dec>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://www.example.org/dec>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p4777",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4777" to null,
                                "o" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4777" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/decimals>",
                                "#p4777" to null,
                                "o" to "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed1>",
                                "#p4777" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://www.example.org/mixed2>",
                                "#p4777" to null,
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.com/data/#x>","<http://example.com/data/#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#x>","<http://example.com/data/#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#x>","<http://example.com/data/#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#x>","<http://example.com/data/#p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#y>","<http://example.com/data/#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#y>","<http://example.com/data/#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#y>","<http://example.com/data/#p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.com/data/#y>","<http://example.com/data/#p>","_:b2"))
                        graph.addData(1L,listOf("<http://example.com/data/#z>","<http://example.com/data/#p>","\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.com/data/#z>","<http://example.com/data/#p>","\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.com/data/#z>","<http://example.com/data/#p>","\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.com/data/#z>","<http://example.com/data/#p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"g","<http://example.com/data/#p>","p",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "g",
                            "#p4925",
                            "p"
                        ), listOf(
                            mutableMapOf(
                                "g" to "<http://example.com/data/#x>",
                                "#p4925" to null,
                                "p" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#x>",
                                "#p4925" to null,
                                "p" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#x>",
                                "#p4925" to null,
                                "p" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#x>",
                                "#p4925" to null,
                                "p" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#y>",
                                "#p4925" to null,
                                "p" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#y>",
                                "#p4925" to null,
                                "p" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#y>",
                                "#p4925" to null,
                                "p" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#y>",
                                "#p4925" to null,
                                "p" to "_:b2"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#z>",
                                "#p4925" to null,
                                "p" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#z>",
                                "#p4925" to null,
                                "p" to "\"2.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#z>",
                                "#p4925" to null,
                                "p" to "\"3.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "g" to "<http://example.com/data/#z>",
                                "#p4925" to null,
                                "p" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/ns#s>",
                                "p" to "<http://example.org/ns#p>",
                                "o" to "<http://example.org/ns#o>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/update-silent/add-silent.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o2>"))
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/ns#s>",
                                "p" to "<http://example.org/ns#p>",
                                "o" to "<http://example.org/ns#o2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/ns#s>",
                                "p" to "<http://example.org/ns#p>",
                                "o" to "<http://example.org/ns#o>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-data-named2.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"o\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"o\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"o\""))
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"o\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"q\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"q\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://example.org/b>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://example.org/b>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"q\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/p>","\"z\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/g2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"z\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/p>","\"y\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/g1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"y\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://example.org/b>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/p>","\"q\""))
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://example.org/b>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/g1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"q\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/g2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"q\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s1","p1","z",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p7865",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p7865" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p7865" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p7865" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p7865" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p7934",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p7934" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p7934" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p7934" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p7934" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind10.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p8059",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p8059" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p8059" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p8059" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p8059" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p8123",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p8123" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p8123" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p8123" to null,
                                "v" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p8123" to null,
                                "v" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8342",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8342" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8342" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8341",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8341" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8341" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8352",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8352" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8352" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8351",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8351" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8351" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p2" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o2" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o2" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                "o2" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p2" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o2" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p2" to "<http://xmlns.com/foaf/0.1/name>",
                                "o2" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","o2",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p8782",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "#p8782" to null,
                                "o2" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "#p8782" to null,
                                "o2" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p1",
                            "o1"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p1" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o1" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p1" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o1" to "\"alice@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p1" to "<http://xmlns.com/foaf/0.1/name>",
                                "o1" to "\"Alice\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8870",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8870" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8870" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8871",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8871" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8871" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8881",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8881" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8881" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8880",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8880" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8880" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8960",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8960" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8960" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8958",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8958" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8958" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://purl.org/dc/elements/1.1/title>","title",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8968",
                            "title"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8968" to null,
                                "title" to "\"SPARQL Tutorial\""
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8968" to null,
                                "title" to "\"The Semantic Web\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"book","<http://example.org/ns#price>","price",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "book",
                            "#p8970",
                            "price"
                        ), listOf(
                            mutableMapOf(
                                "book" to "<http://example.org/book/book1>",
                                "#p8970" to null,
                                "price" to "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "book" to "<http://example.org/book/book2>",
                                "#p8970" to null,
                                "price" to "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/description>","\"Graph 1\""))
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/name>","\"G1\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/g1>",
                                "p" to "<http://example.org/description>",
                                "o" to "\"Graph 1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/g1>",
                                "p" to "<http://example.org/name>",
                                "o" to "\"G1\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-default-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/name>","\"G2\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/g2>",
                                "p" to "<http://example.org/name>",
                                "o" to "\"G2\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-graph-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<>","<http://example.org/name>","\"Default Graph\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<>",
                                "p" to "<http://example.org/name>",
                                "o" to "\"Default Graph\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-named-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o2>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","<http://example.org/o3>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "<http://example.org/o1>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "<http://example.org/o1>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "<http://example.org/o2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "<http://example.org/o3>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p>","o",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s9403",
                            "#p9403",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "#s9403" to null,
                                "#p9403" to null,
                                "o" to "<http://example.org/o1>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/construct/constructwhere02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s2","<http://example.org/p>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s2",
                            "#p9404",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s2" to "<http://example.org/s1>",
                                "#p9404" to null,
                                "o" to "<http://example.org/o1>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/construct/constructwhere02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s9530",
                            "#p9530",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "#s9530" to null,
                                "#p9530" to null,
                                "o2" to "<http://example.org/o1>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/construct/constructwhere03.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s9529",
                            "#p9529",
                            "o1"
                        ), listOf(
                            mutableMapOf(
                                "#s9529" to null,
                                "#p9529" to null,
                                "o1" to "<http://example.org/o1>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/construct/constructwhere03.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://xmlns.com/foaf/0.1/givenName>","\"Ronnie\""))
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:ronnie@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/ron>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/ron>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"Ronnie\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/ron>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:ronnie@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/copy/copy-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://xmlns.com/foaf/0.1/givenName>","\"Jerry\""))
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:jerry@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/jerry>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/jerry>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"Jerry\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/jerry>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:jerry@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "o" to "<http://example.org/s1>",
                                "p2" to "<http://example.org/p1>",
                                "o2" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s3>",
                                "p2" to "<http://example.org/p3>",
                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s4>",
                                "p2" to "<http://example.org/p4>",
                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s5>",
                                "p2" to "<http://example.org/p5>",
                                "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s6>",
                                "p2" to "<http://example.org/p6>",
                                "o2" to "_:o6"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "o" to "<http://example.org/s1>",
                                "p2" to "<http://example.org/p1>",
                                "o2" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s3>",
                                "p2" to "<http://example.org/p3>",
                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s4>",
                                "p2" to "<http://example.org/p4>",
                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s5>",
                                "p2" to "<http://example.org/p5>",
                                "o2" to "\"5.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s6>",
                                "p2" to "<http://example.org/p6>",
                                "o2" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5,5\"^^<http://example.org/myCustomDatatype>"))
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/p7>","\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p2>","\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p3>","\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p4>","\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        graph.addData(1L,listOf("<http://example.org/s5>","<http://example.org/p5>","\"5,5\"^^<http://example.org/myCustomDatatype>"))
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/p6>","\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/p7>","\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p3>",
                                "o" to "\"-3\"^^<http://www.w3.org/2001/XMLSchema#negativeInteger>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4,4\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5,5\"^^<http://example.org/myCustomDatatype>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "\"1.0E6\"^^<http://www.w3.org/2001/XMLSchema#double>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/p7>",
                                "o" to "\"a7\"^^<http://www.w3.org/2001/XMLSchema#hexBinary>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-where/delete-where-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"dan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Dan\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete-where/delete-where-06.ru */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","b",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p10871",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p10871" to null,
                                "b" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p10871" to null,
                                "b" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/b>",
                                "#p10871" to null,
                                "b" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:alan@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/a>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bob@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/a>"))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:claire@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Claire\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:alan@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:bob@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:claire@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Claire\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11103",
                            "#o11103"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11103" to null,
                                "#o11103" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","b",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11104",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11104" to null,
                                "b" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11104" to null,
                                "b" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/b>",
                                "#p11104" to null,
                                "b" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:alan@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bob@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:claire@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Claire\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:alan@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:bob@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:claire@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Claire\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11347",
                            "#o11347"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11347" to null,
                                "#o11347" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-04b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11348",
                            "Var_B"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11348" to null,
                                "Var_B" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11348" to null,
                                "Var_B" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/b>",
                                "#p11348" to null,
                                "Var_B" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-04b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11485",
                            "#o11485"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11485" to null,
                                "#o11485" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11486",
                            "Var_B"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11486" to null,
                                "Var_B" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11486" to null,
                                "Var_B" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/b>",
                                "#p11486" to null,
                                "Var_B" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/a>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:alan@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bob@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:claire@example.org>"))
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Claire\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/a>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:alan@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:bob@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:claire@example.org>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Claire\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11639",
                            "#o11639"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/a>",
                                "#p11639" to null,
                                "#o11639" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p11640",
                            "Var_B"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/b>",
                                "#p11640" to null,
                                "Var_B" to "<http://example.org/c>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s12526",
                            "#p12526",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12526" to null,
                                "#p12526" to null,
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"alan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Alan\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s12623",
                            "#p12623",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12623" to null,
                                "#p12623" to null,
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s12852",
                            "#p12852",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s12852" to null,
                                "#p12852" to null,
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/name>","\"Chris\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p12957",
                            "#o12957"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "#p12957" to null,
                                "#o12957" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s13028",
                            "#p13028",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s13028" to null,
                                "#p13028" to null,
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s13368",
                            "#p13368",
                            "s"
                        ), listOf(
                            mutableMapOf(
                                "#s13368" to null,
                                "#p13368" to null,
                                "s" to "<http://example.org/b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p13648",
                            "#o13648"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "#p13648" to null,
                                "#o13648" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/d>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"chris@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Chris\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"dan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Dan\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/mbox>","\"dan@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/d>","<http://xmlns.com/foaf/0.1/name>","\"Dan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"dan@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Dan\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p13754",
                            "#o13754"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "#p13754" to null,
                                "#o13754" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/name>","\"Chris\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p13875",
                            "#o13875"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/c>",
                                "#p13875" to null,
                                "#o13875" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/a>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/b>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/knows>",
                                "o" to "<http://example.org/c>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "\"bob@example.org\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/b>",
                                "p" to "<http://xmlns.com/foaf/0.1/name>",
                                "o" to "\"Bob\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#myBanana>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15338",
                            "#o15338"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#myBanana>",
                                "#p15338" to null,
                                "#o15338" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c1"))
                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c2"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/ns#b1>","#c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15375",
                            "#c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#a1>",
                                "#p15375" to null,
                                "#c" to "_:c1"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/ns#a1>",
                                "#p15375" to null,
                                "#c" to "_:c2"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15415",
                            "#o15415"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15415" to null,
                                "#o15415" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf04.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://example.org/ns#b1>","<http://example.org/ns#c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#a>","x","<http://example.org/ns#c>",true,false,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s15452",
                            "x",
                            "#o15452"
                        ), listOf(
                            mutableMapOf(
                                "#s15452" to null,
                                "x" to "<http://example.org/ns#b1>",
                                "#o15452" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs01.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/x/d>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/x/d>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c",
                            "#p15626",
                            "#o15626"
                        ), listOf(
                            mutableMapOf(
                                "c" to "<http://example.org/x/c>",
                                "#p15626" to null,
                                "#o15626" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/x/d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/x/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("_:rdfs05","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15625",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/c>",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/d>",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/p>",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15625" to null,
                                "c" to "<http://example.org/x/c>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/y>",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "_:rdfs05",
                                "#p15625" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","x",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s15748",
                            "#p15748",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "#s15748" to null,
                                "#p15748" to null,
                                "x" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p15885",
                            "#o15885"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/ns#b>",
                                "#p15885" to null,
                                "#o15885" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://example.org/ns#b>","<http://example.org/ns#c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#a>","x","<http://example.org/ns#c>",true,false,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s15884",
                            "x",
                            "#o15884"
                        ), listOf(
                            mutableMapOf(
                                "#s15884" to null,
                                "x" to "<http://example.org/ns#b>",
                                "#o15884" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","<http://example.org/ns#apple>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","f",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s15930",
                            "#p15930",
                            "f"
                        ), listOf(
                            mutableMapOf(
                                "#s15930" to null,
                                "#p15930" to null,
                                "f" to "<http://example.org/ns#apple>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16072",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p16072" to null,
                                "c" to "<http://example.org/x/c>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p16072" to null,
                                "c" to "<http://example.org/x/d>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p16072" to null,
                                "c" to "_:x"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/c>",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/d>",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/p>",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:ont",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "x" to "_:x",
                                "#p16072" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "y",
                            "#p16135",
                            "#o16135"
                        ), listOf(
                            mutableMapOf(
                                "y" to "<http://example.org/x/y>",
                                "#p16135" to null,
                                "#o16135" to null
                            ),
                            mutableMapOf(
                                "y" to "_:y",
                                "#p16135" to null,
                                "#o16135" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/y>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","_:y"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/x/p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16134",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16134" to null,
                                "y" to "<http://example.org/x/y>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p16134" to null,
                                "y" to "_:y"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c",
                            "#p16178",
                            "#o16178"
                        ), listOf(
                            mutableMapOf(
                                "c" to "<http://example.org/GraduateAssistant>",
                                "#p16178" to null,
                                "#o16178" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c",
                            "#p16219",
                            "#o16219"
                        ), listOf(
                            mutableMapOf(
                                "c" to "<http://example.org/GraduateAssistant>",
                                "#p16219" to null,
                                "#o16219" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>"))
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Conference>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/ConferencePaper>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Employee>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Employee>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Student>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Workshop>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/name>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ConferencePaper>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/person1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:SPARQLDAWGTestOntology","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        graph.addData(1L,listOf("_:_16223","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16327",
                            "#c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "#p16327" to null,
                                "#c" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Conference>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/ConferencePaper>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Employee>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/George>",
                                "#p16327" to null,
                                "#c" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/George>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/GraduateAssistant>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16327" to null,
                                "#c" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Student>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Workshop>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/hasPublication>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/name>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/paper1>",
                                "#p16327" to null,
                                "#c" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/paper1>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/publishedAt>",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:SPARQLDAWGTestOntology",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "x" to "_:_16223",
                                "#p16327" to null,
                                "#c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/name>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16338",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16338" to null,
                                "y" to "\"Johnnie\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        graph.addData(1L,listOf("<http://example.org/person1>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/hasPublication>","#b0",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16455",
                            "#b0"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16455" to null,
                                "#b0" to "<http://example.org/paper1>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Conference>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/ConferencePaper>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Employee>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Student>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Workshop>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16378","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16378",
                            "#p16460",
                            "#o16460"
                        ), listOf(
                            mutableMapOf(
                                "#_16378" to "<http://example.org/Conference>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "#_16378" to "<http://example.org/ConferencePaper>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "#_16378" to "<http://example.org/Employee>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "#_16378" to "<http://example.org/GraduateAssistant>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "#_16378" to "<http://example.org/Student>",
                                "#p16460" to null,
                                "#o16460" to null
                            ),
                            mutableMapOf(
                                "#_16378" to "<http://example.org/Workshop>",
                                "#p16460" to null,
                                "#o16460" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16349","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16369","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16369",
                            "#p16458",
                            "#o16458"
                        ), listOf(
                            mutableMapOf(
                                "#_16369" to "_:_16349",
                                "#p16458" to null,
                                "#o16458" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16349","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16369","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16369",
                            "#p16456",
                            "#o16456"
                        ), listOf(
                            mutableMapOf(
                                "#_16369" to "_:_16349",
                                "#p16456" to null,
                                "#o16456" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16349","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://example.org/Conference>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16369","<http://www.w3.org/2002/07/owl#someValuesFrom>","#_16378",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16369",
                            "#p16464",
                            "#_16378"
                        ), listOf(
                            mutableMapOf(
                                "#_16369" to "_:_16349",
                                "#p16464" to null,
                                "#_16378" to "<http://example.org/Conference>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>"))
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Conference>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/ConferencePaper>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Employee>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Employee>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Student>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Workshop>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/name>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ConferencePaper>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/person1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:SPARQLDAWGTestOntology","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        graph.addData(1L,listOf("_:_16349","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b0","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_16369",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b0",
                            "#p16466",
                            "#_16369"
                        ), listOf(
                            mutableMapOf(
                                "#b0" to "<http://example.org/Anite>",
                                "#p16466" to null,
                                "#_16369" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/Anite>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/Conference>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/ConferencePaper>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/Employee>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/George>",
                                "#p16466" to null,
                                "#_16369" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/George>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/GraduateAssistant>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/John>",
                                "#p16466" to null,
                                "#_16369" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/John>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/Student>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/Workshop>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/hasPublication>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/name>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/paper1>",
                                "#p16466" to null,
                                "#_16369" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/paper1>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/person1>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "#b0" to "<http://example.org/publishedAt>",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "#b0" to "_:SPARQLDAWGTestOntology",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "#b0" to "_:_16349",
                                "#p16466" to null,
                                "#_16369" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Employee>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Employee>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c",
                            "#p16544",
                            "#o16544"
                        ), listOf(
                            mutableMapOf(
                                "c" to "<http://example.org/GraduateAssistant>",
                                "#p16544" to null,
                                "#o16544" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16541",
                            "#o16541"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "#p16541" to null,
                                "#o16541" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>"))
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Conference>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/ConferencePaper>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Employee>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Employee>"))
                        graph.addData(1L,listOf("<http://example.org/George>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/Student>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Workshop>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/name>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ConferencePaper>"))
                        graph.addData(1L,listOf("<http://example.org/paper1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/person1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:SPARQLDAWGTestOntology","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        graph.addData(1L,listOf("_:_16477","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16542",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "#p16542" to null,
                                "c" to "<http://example.org/Student>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Anite>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Conference>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/ConferencePaper>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Employee>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/George>",
                                "#p16542" to null,
                                "c" to "<http://example.org/Employee>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/George>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/GraduateAssistant>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16542" to null,
                                "c" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/John>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Student>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/Workshop>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/hasPublication>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/name>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/paper1>",
                                "#p16542" to null,
                                "c" to "<http://example.org/ConferencePaper>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/paper1>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/person1>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/publishedAt>",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:SPARQLDAWGTestOntology",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "x" to "_:_16477",
                                "#p16542" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p16607",
                            "#o16607"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/hasPublication>",
                                "#p16607" to null,
                                "#o16607" to null
                            ),
                            mutableMapOf(
                                "p" to "<http://example.org/publishedAt>",
                                "#p16607" to null,
                                "#o16607" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/John>","p","v",true,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s16608",
                            "p",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "#s16608" to null,
                                "p" to "<http://example.org/hasPublication>",
                                "v" to "<http://example.org/paper1>"
                            ),
                            mutableMapOf(
                                "#s16608" to null,
                                "p" to "<http://example.org/name>",
                                "v" to "\"Johnnie\""
                            ),
                            mutableMapOf(
                                "#s16608" to null,
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "v" to "<http://example.org/GraduateAssistant>"
                            ),
                            mutableMapOf(
                                "#s16608" to null,
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "v" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://xmlns.com/foaf/0.1/name>","\"name\"@en",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p16650",
                            "#o16650"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p16650" to null,
                                "#o16650" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/plainLit.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p16722",
                            "#o16722"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p16722" to null,
                                "#o16722" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/p>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "_:1",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p16852",
                            "#o16852"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p16852" to null,
                                "#o16852" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17025",
                            "#o17025"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17025" to null,
                                "#o17025" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p1","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p1",
                            "#p17029",
                            "#o17029"
                        ), listOf(
                            mutableMapOf(
                                "p1" to "<http://example.org/p>",
                                "#p17029" to null,
                                "#o17029" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s1","p1","z",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/p>",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#DatatypeProperty>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "p1" to "<http://example.org/p>",
                                "z" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s1" to "_:1",
                                "p1" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "z" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17138",
                            "#o17138"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17138" to null,
                                "#o17138" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17243",
                            "#o17243"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17243" to null,
                                "#o17243" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17360",
                            "#o17360"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17360" to null,
                                "#o17360" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17533",
                            "#o17533"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17533" to null,
                                "#o17533" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17674",
                            "#o17674"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17674" to null,
                                "#o17674" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17793",
                            "#o17793"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17793" to null,
                                "#o17793" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p",
                            "#p17910",
                            "#o17910"
                        ), listOf(
                            mutableMapOf(
                                "p" to "<http://example.org/p>",
                                "#p17910" to null,
                                "#o17910" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18049",
                            "#o18049"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18049" to null,
                                "#o18049" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/x/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18105",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/c>",
                                "#p18105" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/p>",
                                "#p18105" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18105" to null,
                                "c" to "<http://example.org/x/c>"
                            ),
                            mutableMapOf(
                                "x" to "_:ont",
                                "#p18105" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/a>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/x/p>","#y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18178",
                            "#y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18178" to null,
                                "#y" to "<http://example.org/x/a>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("<http://example.org/x/a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/x/d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/x/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/d>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:x"))
                        graph.addData(1L,listOf("_:sparql-dl","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        graph.addData(1L,listOf("_:x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18175",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p18175" to null,
                                "c" to "<http://example.org/x/c>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/a>",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/c>",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/d>",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/p>",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18175" to null,
                                "c" to "<http://example.org/x/c>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18175" to null,
                                "c" to "<http://example.org/x/d>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/x/x>",
                                "#p18175" to null,
                                "c" to "_:x"
                            ),
                            mutableMapOf(
                                "x" to "_:sparql-dl",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            ),
                            mutableMapOf(
                                "x" to "_:x",
                                "#p18175" to null,
                                "c" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18247",
                            "#o18247"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18247" to null,
                                "#o18247" to null
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#b>",
                                "#p18247" to null,
                                "#o18247" to null
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#c>",
                                "#p18247" to null,
                                "#o18247" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#name>","Y1",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18248",
                            "Y1"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18248" to null,
                                "Y1" to "\"A\""
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#b>",
                                "#p18248" to null,
                                "Y1" to "\"B\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#nick>","\"Anick\""))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#nick>","\"Bnick\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#nick>","Y2",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18250",
                            "Y2"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18250" to null,
                                "Y2" to "\"Anick\""
                            ),
                            mutableMapOf(
                                "X" to "<http://example.org/test#b>",
                                "#p18250" to null,
                                "Y2" to "\"Bnick\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#a",
                            "#p18293",
                            "#o18293"
                        ), listOf(
                            mutableMapOf(
                                "#a" to "<http://example.org/test#a>",
                                "#p18293" to null,
                                "#o18293" to null
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#b>",
                                "#p18293" to null,
                                "#o18293" to null
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#c>",
                                "#p18293" to null,
                                "#o18293" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#dd","<http://example.org/test#t>","#bb",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#dd",
                            "#p18371",
                            "#bb"
                        ), listOf(
                            mutableMapOf(
                                "#dd" to "<http://example.org/test#dd>",
                                "#p18371" to null,
                                "#bb" to "<http://example.org/test#bb>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s18368",
                            "#p18368",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18368" to null,
                                "#p18368" to null,
                                "#aa" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#aa","<http://example.org/test#r>","#dd",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#p18369",
                            "#dd"
                        ), listOf(
                            mutableMapOf(
                                "#aa" to "<http://example.org/test#aa>",
                                "#p18369" to null,
                                "#dd" to "<http://example.org/test#ee>"
                            ),
                            mutableMapOf(
                                "#aa" to "<http://example.org/test#cc>",
                                "#p18369" to null,
                                "#dd" to "<http://example.org/test#dd>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#s>","#aa",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "Y",
                            "#p18456",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "Y" to "<http://example.org/test#bb>",
                                "#p18456" to null,
                                "#aa" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#aa","<http://example.org/test#r>","Z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#p18458",
                            "Z"
                        ), listOf(
                            mutableMapOf(
                                "#aa" to "<http://example.org/test#aa>",
                                "#p18458" to null,
                                "Z" to "<http://example.org/test#ee>"
                            ),
                            mutableMapOf(
                                "#aa" to "<http://example.org/test#cc>",
                                "#p18458" to null,
                                "Z" to "<http://example.org/test#dd>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#t>","Y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18454",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#dd>",
                                "#p18454" to null,
                                "Y" to "<http://example.org/test#bb>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s18453",
                            "#p18453",
                            "#aa"
                        ), listOf(
                            mutableMapOf(
                                "#s18453" to null,
                                "#p18453" to null,
                                "#aa" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://example.org/test#r>","Y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#a",
                            "#p18514",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#a" to "<http://example.org/test#aa>",
                                "#p18514" to null,
                                "Y" to "<http://example.org/test#ee>"
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#cc>",
                                "#p18514" to null,
                                "Y" to "<http://example.org/test#dd>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18513",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18513" to null,
                                "#a" to "<http://example.org/test#aa>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18570",
                            "#a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18570" to null,
                                "#a" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://example.org/test#q>","Y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#a",
                            "#p18571",
                            "Y"
                        ), listOf(
                            mutableMapOf(
                                "#a" to "<http://example.org/test#b>",
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#c>"
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#b>",
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#h>"
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#b>",
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#i>"
                            ),
                            mutableMapOf(
                                "#a" to "<http://example.org/test#x>",
                                "#p18571" to null,
                                "Y" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#p18651",
                            "a"
                        ), listOf(
                            mutableMapOf(
                                "X" to "<http://example.org/test#a>",
                                "#p18651" to null,
                                "a" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#q>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "Y",
                            "#p18656",
                            "c"
                        ), listOf(
                            mutableMapOf(
                                "Y" to "<http://example.org/test#b>",
                                "#p18656" to null,
                                "c" to "<http://example.org/test#c>"
                            ),
                            mutableMapOf(
                                "Y" to "<http://example.org/test#b>",
                                "#p18656" to null,
                                "c" to "<http://example.org/test#h>"
                            ),
                            mutableMapOf(
                                "Y" to "<http://example.org/test#b>",
                                "#p18656" to null,
                                "c" to "<http://example.org/test#i>"
                            ),
                            mutableMapOf(
                                "Y" to "<http://example.org/test#x>",
                                "#p18656" to null,
                                "c" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<foo://bla/names#child>","<http://www.w3.org/2000/01/rdf-schema#domain>","<foo://bla/names#Parent>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<foo://bla/names#child>","<http://www.w3.org/2000/01/rdf-schema#domain>","C",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s18748",
                            "#p18748",
                            "C"
                        ), listOf(
                            mutableMapOf(
                                "#s18748" to null,
                                "#p18748" to null,
                                "C" to "<foo://bla/names#Parent>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-12.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://example.org/test#q>","<http://example.org/test#c>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://example.org/test#q>","<http://example.org/test#d>"))
                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/test#q>","x",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18820",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p18820" to null,
                                "x" to "<http://example.org/test#c>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p18820" to null,
                                "x" to "<http://example.org/test#d>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#x>",
                                "#p18820" to null,
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/2002/07/owl#sameAs>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://www.w3.org/2002/07/owl#sameAs>","x",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "b",
                            "#p18818",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "b" to "<http://example.org/test#b>",
                                "#p18818" to null,
                                "x" to "<http://example.org/test#x>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://example.org/test#p>","b",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p18817",
                            "b"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/test#a>",
                                "#p18817" to null,
                                "b" to "<http://example.org/test#b>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://xmlns.com/foaf/0.1/name>","\"name\"@en",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p18863",
                            "#o18863"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p18863" to null,
                                "#o18863" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://example.org/test#hasChild>","<http://example.org/test#Charlie>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://example.org/test#hasChild>","<http://example.org/test#Alice>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://example.org/test#hasChild>","child",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p18910",
                            "child"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p18910" to null,
                                "child" to "<http://example.org/test#Charlie>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p18910" to null,
                                "child" to "<http://example.org/test#Alice>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_18921"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_18914","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_18917","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_18920","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_18921","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_18922","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_18935",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p18993",
                            "#_18935"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p18993" to null,
                                "#_18935" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p18993" to null,
                                "#_18935" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p18993" to null,
                                "#_18935" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p18993" to null,
                                "#_18935" to "_:_18921"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_18914",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_18917",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_18920",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_18921",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_18922",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p18993" to null,
                                "#_18935" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_18920","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_18935","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_18935",
                            "#p18991",
                            "#o18991"
                        ), listOf(
                            mutableMapOf(
                                "#_18935" to "_:_18920",
                                "#p18991" to null,
                                "#o18991" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_18920","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_18921","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_18935","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_18935",
                            "#p18988",
                            "#o18988"
                        ), listOf(
                            mutableMapOf(
                                "#_18935" to "_:_18920",
                                "#p18988" to null,
                                "#o18988" to null
                            ),
                            mutableMapOf(
                                "#_18935" to "_:_18921",
                                "#p18988" to null,
                                "#o18988" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_18920","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_18921","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_18935","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_18935",
                            "#p18989",
                            "#o18989"
                        ), listOf(
                            mutableMapOf(
                                "#_18935" to "_:_18920",
                                "#p18989" to null,
                                "#o18989" to null
                            ),
                            mutableMapOf(
                                "#_18935" to "_:_18921",
                                "#p18989" to null,
                                "#o18989" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19007","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19008","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19025","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19025",
                            "#p19084",
                            "#o19084"
                        ), listOf(
                            mutableMapOf(
                                "#_19025" to "_:_19007",
                                "#p19084" to null,
                                "#o19084" to null
                            ),
                            mutableMapOf(
                                "#_19025" to "_:_19008",
                                "#p19084" to null,
                                "#o19084" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19007","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19008","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19025","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19025",
                            "#p19083",
                            "#o19083"
                        ), listOf(
                            mutableMapOf(
                                "#_19025" to "_:_19007",
                                "#p19083" to null,
                                "#o19083" to null
                            ),
                            mutableMapOf(
                                "#_19025" to "_:_19008",
                                "#p19083" to null,
                                "#o19083" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19008"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19001","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19004","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19007","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19008","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19009","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19025",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p19088",
                            "#_19025"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19088" to null,
                                "#_19025" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19088" to null,
                                "#_19025" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19088" to null,
                                "#_19025" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19088" to null,
                                "#_19025" to "_:_19008"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19001",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19004",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19007",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19008",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19009",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p19088" to null,
                                "#_19025" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19102","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19103","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19117","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19117",
                            "#p19171",
                            "#o19171"
                        ), listOf(
                            mutableMapOf(
                                "#_19117" to "_:_19102",
                                "#p19171" to null,
                                "#o19171" to null
                            ),
                            mutableMapOf(
                                "#_19117" to "_:_19103",
                                "#p19171" to null,
                                "#o19171" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19102","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19103","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19117","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19117",
                            "#p19170",
                            "#o19170"
                        ), listOf(
                            mutableMapOf(
                                "#_19117" to "_:_19102",
                                "#p19170" to null,
                                "#o19170" to null
                            ),
                            mutableMapOf(
                                "#_19117" to "_:_19103",
                                "#p19170" to null,
                                "#o19170" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19103"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19096","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19099","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19102","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19103","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19104","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19117",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p19175",
                            "#_19117"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19175" to null,
                                "#_19117" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19175" to null,
                                "#_19117" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19175" to null,
                                "#_19117" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19175" to null,
                                "#_19117" to "_:_19103"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19096",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19099",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19102",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19103",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19104",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p19175" to null,
                                "#_19117" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19189","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19190","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19205","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19205",
                            "#p19271",
                            "#o19271"
                        ), listOf(
                            mutableMapOf(
                                "#_19205" to "_:_19189",
                                "#p19271" to null,
                                "#o19271" to null
                            ),
                            mutableMapOf(
                                "#_19205" to "_:_19190",
                                "#p19271" to null,
                                "#o19271" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19190"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19183","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19186","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19189","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19190","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19191","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19205",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p19278",
                            "#_19205"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19278" to null,
                                "#_19205" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19278" to null,
                                "#_19205" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19278" to null,
                                "#_19205" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19278" to null,
                                "#_19205" to "_:_19190"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19183",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19186",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19189",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19190",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19191",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p19278" to null,
                                "#_19205" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19189","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19190","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19205","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19205",
                            "#p19272",
                            "#o19272"
                        ), listOf(
                            mutableMapOf(
                                "#_19205" to "_:_19189",
                                "#p19272" to null,
                                "#o19272" to null
                            ),
                            mutableMapOf(
                                "#_19205" to "_:_19190",
                                "#p19272" to null,
                                "#o19272" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19293","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19294","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19309","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19309",
                            "#p19376",
                            "#o19376"
                        ), listOf(
                            mutableMapOf(
                                "#_19309" to "_:_19293",
                                "#p19376" to null,
                                "#o19376" to null
                            ),
                            mutableMapOf(
                                "#_19309" to "_:_19294",
                                "#p19376" to null,
                                "#o19376" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19294"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19287","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19290","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19293","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19294","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19295","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19309",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p19382",
                            "#_19309"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19382" to null,
                                "#_19309" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19382" to null,
                                "#_19309" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19382" to null,
                                "#_19309" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19382" to null,
                                "#_19309" to "_:_19294"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19287",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19290",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19293",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19294",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19295",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p19382" to null,
                                "#_19309" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19293","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19294","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19309","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19309",
                            "#p19375",
                            "#o19375"
                        ), listOf(
                            mutableMapOf(
                                "#_19309" to "_:_19293",
                                "#p19375" to null,
                                "#o19375" to null
                            ),
                            mutableMapOf(
                                "#_19309" to "_:_19294",
                                "#p19375" to null,
                                "#o19375" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19397","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19398","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19413","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19413",
                            "#p19479",
                            "#o19479"
                        ), listOf(
                            mutableMapOf(
                                "#_19413" to "_:_19397",
                                "#p19479" to null,
                                "#o19479" to null
                            ),
                            mutableMapOf(
                                "#_19413" to "_:_19398",
                                "#p19479" to null,
                                "#o19479" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Female>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Parent>"))
                        graph.addData(1L,listOf("<http://example.org/test#Alice>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Male>"))
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Charlie>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19398"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19391","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19394","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19397","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19398","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19399","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19413",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#p19486",
                            "#_19413"
                        ), listOf(
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19486" to null,
                                "#_19413" to "<http://example.org/test#Female>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19486" to null,
                                "#_19413" to "<http://example.org/test#Parent>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Alice>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19486" to null,
                                "#_19413" to "<http://example.org/test#Male>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Bob>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Charlie>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#NamedIndividual>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Dudley>",
                                "#p19486" to null,
                                "#_19413" to "_:_19398"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Father>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Female>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Male>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Mother>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#Parent>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "<http://example.org/test#hasChild>",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19391",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19394",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19397",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19398",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Restriction>"
                            ),
                            mutableMapOf(
                                "parent" to "_:_19399",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "parent" to "_:ont",
                                "#p19486" to null,
                                "#_19413" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19397","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19398","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19413","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19413",
                            "#p19480",
                            "#o19480"
                        ), listOf(
                            mutableMapOf(
                                "#_19413" to "_:_19397",
                                "#p19480" to null,
                                "#o19480" to null
                            ),
                            mutableMapOf(
                                "#_19413" to "_:_19398",
                                "#p19480" to null,
                                "#o19480" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19501","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19518","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19518",
                            "#p19576",
                            "#o19576"
                        ), listOf(
                            mutableMapOf(
                                "#_19518" to "_:_19501",
                                "#p19576" to null,
                                "#o19576" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19501","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19502","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19518","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19518",
                            "#p19574",
                            "#o19574"
                        ), listOf(
                            mutableMapOf(
                                "#_19518" to "_:_19501",
                                "#p19574" to null,
                                "#o19574" to null
                            ),
                            mutableMapOf(
                                "#_19518" to "_:_19502",
                                "#p19574" to null,
                                "#o19574" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19501","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19502","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19518","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19518",
                            "#p19573",
                            "#o19573"
                        ), listOf(
                            mutableMapOf(
                                "#_19518" to "_:_19501",
                                "#p19573" to null,
                                "#o19573" to null
                            ),
                            mutableMapOf(
                                "#_19518" to "_:_19502",
                                "#p19573" to null,
                                "#o19573" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19592","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b",
                            "#p19676",
                            "#o19676"
                        ), listOf(
                            mutableMapOf(
                                "#b" to "_:_19592",
                                "#p19676" to null,
                                "#o19676" to null
                            ),
                            mutableMapOf(
                                "#b" to "_:_19593",
                                "#p19676" to null,
                                "#o19676" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19592","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b",
                            "#p19680",
                            "#o19680"
                        ), listOf(
                            mutableMapOf(
                                "#b" to "_:_19592",
                                "#p19680" to null,
                                "#o19680" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19592","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b",
                            "#p19678",
                            "#o19678"
                        ), listOf(
                            mutableMapOf(
                                "#b" to "_:_19592",
                                "#p19678" to null,
                                "#o19678" to null
                            ),
                            mutableMapOf(
                                "#b" to "_:_19593",
                                "#p19678" to null,
                                "#o19678" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19706",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p19788",
                            "#_19706"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19788" to null,
                                "#_19706" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p19788" to null,
                                "#_19706" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19811",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p19929",
                            "#_19811"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p19929" to null,
                                "#_19811" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p19929" to null,
                                "#_19811" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19955",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20073",
                            "#_19955"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20073" to null,
                                "#_19955" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20073" to null,
                                "#_19955" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20099","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20099",
                            "#p20181",
                            "#o20181"
                        ), listOf(
                            mutableMapOf(
                                "#_20099" to "<http://example.org/test#A>",
                                "#p20181" to null,
                                "#o20181" to null
                            ),
                            mutableMapOf(
                                "#_20099" to "<http://example.org/test#B>",
                                "#p20181" to null,
                                "#o20181" to null
                            ),
                            mutableMapOf(
                                "#_20099" to "<http://example.org/test#C>",
                                "#p20181" to null,
                                "#o20181" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20099",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20192",
                            "#_20099"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20192" to null,
                                "#_20099" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20192" to null,
                                "#_20099" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20224","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20224",
                            "#p20331",
                            "#o20331"
                        ), listOf(
                            mutableMapOf(
                                "#_20224" to "<http://example.org/test#A>",
                                "#p20331" to null,
                                "#o20331" to null
                            ),
                            mutableMapOf(
                                "#_20224" to "<http://example.org/test#B>",
                                "#p20331" to null,
                                "#o20331" to null
                            ),
                            mutableMapOf(
                                "#_20224" to "<http://example.org/test#C>",
                                "#p20331" to null,
                                "#o20331" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20216",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20345",
                            "#_20216"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20345" to null,
                                "#_20216" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20345" to null,
                                "#_20216" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20380","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20380",
                            "#p20506",
                            "#o20506"
                        ), listOf(
                            mutableMapOf(
                                "#_20380" to "<http://example.org/test#A>",
                                "#p20506" to null,
                                "#o20506" to null
                            ),
                            mutableMapOf(
                                "#_20380" to "<http://example.org/test#B>",
                                "#p20506" to null,
                                "#o20506" to null
                            ),
                            mutableMapOf(
                                "#_20380" to "<http://example.org/test#C>",
                                "#p20506" to null,
                                "#o20506" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20372",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20524",
                            "#_20372"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20524" to null,
                                "#_20372" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20524" to null,
                                "#_20372" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20562","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20562",
                            "#p20695",
                            "#o20695"
                        ), listOf(
                            mutableMapOf(
                                "#_20562" to "<http://example.org/test#A>",
                                "#p20695" to null,
                                "#o20695" to null
                            ),
                            mutableMapOf(
                                "#_20562" to "<http://example.org/test#B>",
                                "#p20695" to null,
                                "#o20695" to null
                            ),
                            mutableMapOf(
                                "#_20562" to "<http://example.org/test#C>",
                                "#p20695" to null,
                                "#o20695" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20553",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20715",
                            "#_20553"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20715" to null,
                                "#_20553" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20715" to null,
                                "#_20553" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20553","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20553",
                            "#p20692",
                            "#o20692"
                        ), listOf(
                            mutableMapOf(
                                "#_20553" to "<http://example.org/test#A>",
                                "#p20692" to null,
                                "#o20692" to null
                            ),
                            mutableMapOf(
                                "#_20553" to "<http://example.org/test#B>",
                                "#p20692" to null,
                                "#o20692" to null
                            ),
                            mutableMapOf(
                                "#_20553" to "<http://example.org/test#C>",
                                "#p20692" to null,
                                "#o20692" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#A>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#B>"))
                        graph.addData(1L,listOf("<http://example.org/test#d>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#C>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#FunctionalProperty>"))
                        graph.addData(1L,listOf("<http://example.org/test#p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:simple","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20745",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p20841",
                            "#_20745"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://example.org/test#A>",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#B>",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#C>",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#Class>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#a>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#b>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#c>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#A>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#B>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#d>",
                                "#p20841" to null,
                                "#_20745" to "<http://example.org/test#C>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#FunctionalProperty>"
                            ),
                            mutableMapOf(
                                "x" to "<http://example.org/test#p>",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#ObjectProperty>"
                            ),
                            mutableMapOf(
                                "x" to "_:simple",
                                "#p20841" to null,
                                "#_20745" to "<http://www.w3.org/2002/07/owl#Ontology>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p21717",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p21717" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p21717" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p21717" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p21717" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p21717" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p21717" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p21717" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p21850",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p21850" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p21850" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p21850" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p21850" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p21850" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p21850" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p21850" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22044",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p22044" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22044" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p22044" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p22044" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p22044" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p22044" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p22044" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22185",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p22185" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22185" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p22185" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p22185" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p22185" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p22185" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p22185" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "o" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "o" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22557",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p22557" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22557" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p22557" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p22557" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p22557" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p22557" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p22557" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22690",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p22690" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22690" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p22690" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p22690" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p22690" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p22690" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p22690" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p22881",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p22881" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p22881" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p22881" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p22881" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p22881" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p22881" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p22881" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23022",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p23022" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p23022" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p23022" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p23022" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p23022" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p23022" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p23022" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "num" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "num" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "num" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "num" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "num" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23559",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "#p23559" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "#p23559" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "#p23559" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "#p23559" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "#p23559" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23692",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "#p23692" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "#p23692" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "#p23692" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "#p23692" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "#p23692" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23788",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "#p23788" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "#p23788" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "#p23788" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "#p23788" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "#p23788" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p23884",
                            "num"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "#p23884" to null,
                                "num" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "#p23884" to null,
                                "num" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "#p23884" to null,
                                "num" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "#p23884" to null,
                                "num" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "#p23884" to null,
                                "num" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s6>","<http://example.org/str>","str1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s23986",
                            "#p23986",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "#s23986" to null,
                                "#p23986" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s7>","<http://example.org/str>","str2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s23987",
                            "#p23987",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "#s23987" to null,
                                "#p23987" to null,
                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s7>","<http://example.org/str>","str2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s24015",
                            "#p24015",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "#s24015" to null,
                                "#p24015" to null,
                                "str2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s6>","<http://example.org/str>","str1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s24014",
                            "#p24014",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "#s24014" to null,
                                "#p24014" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s1",
                            "#p24101",
                            "str1"
                        ), listOf(
                            mutableMapOf(
                                "s1" to "<http://example.org/s1>",
                                "#p24101" to null,
                                "str1" to "\"123\""
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s2>",
                                "#p24101" to null,
                                "str1" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s3>",
                                "#p24101" to null,
                                "str1" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s4>",
                                "#p24101" to null,
                                "str1" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s5>",
                                "#p24101" to null,
                                "str1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s6>",
                                "#p24101" to null,
                                "str1" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s1" to "<http://example.org/s7>",
                                "#p24101" to null,
                                "str1" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s2",
                            "#p24102",
                            "str2"
                        ), listOf(
                            mutableMapOf(
                                "s2" to "<http://example.org/s1>",
                                "#p24102" to null,
                                "str2" to "\"123\""
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s2>",
                                "#p24102" to null,
                                "str2" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s3>",
                                "#p24102" to null,
                                "str2" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s4>",
                                "#p24102" to null,
                                "str2" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s5>",
                                "#p24102" to null,
                                "str2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s6>",
                                "#p24102" to null,
                                "str2" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s2" to "<http://example.org/s7>",
                                "#p24102" to null,
                                "str2" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p24947",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p24947" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p24947" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p24947" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p24947" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p24947" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p24947" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p24947" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p25056",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p25056" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p25056" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p25056" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p25056" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p25056" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p25056" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p25056" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p25165",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p25165" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p25165" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p25165" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p25165" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p25165" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p25165" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p25165" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p25313",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p25313" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p25313" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p25313" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p25313" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p25313" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p25313" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p25313" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p25384",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "#p25384" to null,
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "#p25384" to null,
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "#p25384" to null,
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "#p25384" to null,
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "#p25384" to null,
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "#p25384" to null,
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "#p25384" to null,
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "str"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "p" to "<http://example.org/date>",
                                "str" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "p" to "<http://example.org/date>",
                                "str" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "p" to "<http://example.org/date>",
                                "str" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "p" to "<http://example.org/date>",
                                "str" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n1>",
                                "p" to "<http://example.org/num>",
                                "str" to "\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n2>",
                                "p" to "<http://example.org/num>",
                                "str" to "\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n3>",
                                "p" to "<http://example.org/num>",
                                "str" to "\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n4>",
                                "p" to "<http://example.org/num>",
                                "str" to "\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/n5>",
                                "p" to "<http://example.org/num>",
                                "str" to "\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"100%\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "str" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p26389",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "#p26389" to null,
                                "x" to "\"a\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "#p26389" to null,
                                "x" to "_:b"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "#p26389" to null,
                                "x" to "<http://example/a>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "#p26389" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "#p26389" to null,
                                "x" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "#p26389" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p26390",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "#p26390" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "#p26390" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "#p26390" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "#p26390" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p26562",
                            "x"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "#p26562" to null,
                                "x" to "\"a\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "#p26562" to null,
                                "x" to "_:b"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "#p26562" to null,
                                "x" to "<http://example/a>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "#p26562" to null,
                                "x" to "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "#p26562" to null,
                                "x" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "#p26562" to null,
                                "x" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p26563",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/x1>",
                                "#p26563" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x2>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x3>",
                                "#p26563" to null,
                                "y" to "\"1\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x4>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x5>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x6>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x7>",
                                "#p26563" to null,
                                "y" to "\"2\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example/x8>",
                                "#p26563" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s26862",
                            "#p26862",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s26862" to null,
                                "#p26862" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s26880",
                            "#p26880",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s26880" to null,
                                "#p26880" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s4>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s26943",
                            "#p26943",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s26943" to null,
                                "#p26943" to null,
                                "l" to "\"食べ物\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s4>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s26961",
                            "#p26961",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s26961" to null,
                                "#p26961" to null,
                                "l" to "\"食べ物\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27024",
                            "#p27024",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27024" to null,
                                "#p27024" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27042",
                            "#p27042",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27042" to null,
                                "#p27042" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27105",
                            "#p27105",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27105" to null,
                                "#p27105" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27123",
                            "#p27123",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27123" to null,
                                "#p27123" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27186",
                            "#p27186",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27186" to null,
                                "#p27186" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27204",
                            "#p27204",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27204" to null,
                                "#p27204" to null,
                                "l" to "\"foo\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27267",
                            "#p27267",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27267" to null,
                                "#p27267" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s27285",
                            "#p27285",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "#s27285" to null,
                                "#p27285" to null,
                                "l" to "\"食\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27404",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27404" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27404" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27404" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27404" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27446",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27446" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27446" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27446" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27446" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27536",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27536" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27536" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27536" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27536" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27621",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27621" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27621" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27621" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27621" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27663",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27663" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27663" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27663" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27663" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27753",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27753" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27753" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27753" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27753" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27795",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27795" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27795" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27795" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27795" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27885",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27885" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27885" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27885" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27885" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p27927",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p27927" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p27927" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p27927" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p27927" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p28017",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p28017" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p28017" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p28017" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p28017" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p28059",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p28059" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p28059" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p28059" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p28059" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p28149",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p28149" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p28149" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p28149" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p28149" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p28234",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p28234" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p28234" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p28234" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p28234" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p28276",
                            "date"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/d1>",
                                "#p28276" to null,
                                "date" to "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d2>",
                                "#p28276" to null,
                                "date" to "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d3>",
                                "#p28276" to null,
                                "date" to "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/d4>",
                                "#p28276" to null,
                                "date" to "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "#p28431",
                            "s1"
                        ), listOf(
                            mutableMapOf(
                                "a" to "<http://example.org/s1>",
                                "#p28431" to null,
                                "s1" to "\"foo\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s2>",
                                "#p28431" to null,
                                "s1" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s3>",
                                "#p28431" to null,
                                "s1" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s4>",
                                "#p28431" to null,
                                "s1" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s5>",
                                "#p28431" to null,
                                "s1" to "\"100%\""
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s6>",
                                "#p28431" to null,
                                "s1" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "a" to "<http://example.org/s7>",
                                "#p28431" to null,
                                "s1" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "b",
                            "#p28432",
                            "s2"
                        ), listOf(
                            mutableMapOf(
                                "b" to "<http://example.org/s1>",
                                "#p28432" to null,
                                "s2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s2>",
                                "#p28432" to null,
                                "s2" to "\"bar\"@en"
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s3>",
                                "#p28432" to null,
                                "s2" to "\"BAZ\""
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s4>",
                                "#p28432" to null,
                                "s2" to "\"食べ物\""
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s5>",
                                "#p28432" to null,
                                "s2" to "\"100%\""
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s6>",
                                "#p28432" to null,
                                "s2" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "b" to "<http://example.org/s7>",
                                "#p28432" to null,
                                "s2" to "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"123\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"日本語\"@ja"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"english\"@en"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"français\"@fr"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"def\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s7>",
                                "p" to "<http://example.org/str>",
                                "o" to "\"7\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32477",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32477" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32477" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32485",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32485" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32485" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32564",
                            "w"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32564" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32563",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32563" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32563" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32601",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32601" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32601" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32602",
                            "w"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32602" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32759",
                            "w"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32759" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s3>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32758",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32758" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32758" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s3>",
                                "#p32758" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32770",
                            "w"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32770" to null,
                                "w" to "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s3>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/p>","v",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "#p32769",
                            "v"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example/s1>",
                                "#p32769" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s2>",
                                "#p32769" to null,
                                "v" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example/s3>",
                                "#p32769" to null,
                                "v" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/s1>",
                                "p" to "<http://example.org/p1>",
                                "o" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s2>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"foo\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s3>",
                                "p" to "<http://example.org/p2>",
                                "o" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s4>",
                                "p" to "<http://example.org/p4>",
                                "o" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s5>",
                                "p" to "<http://example.org/p5>",
                                "o" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/s6>",
                                "p" to "<http://example.org/p6>",
                                "o" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            mutableMapOf(
                                "o" to "<http://example.org/s1>",
                                "p2" to "<http://example.org/p1>",
                                "o2" to "<http://example.org/s2>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s2>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"foo\""
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s3>",
                                "p2" to "<http://example.org/p2>",
                                "o2" to "\"bar\"^^<http://www.w3.org/2001/XMLSchema#string>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s4>",
                                "p2" to "<http://example.org/p4>",
                                "o2" to "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s5>",
                                "p2" to "<http://example.org/p5>",
                                "o2" to "\"5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"
                            ),
                            mutableMapOf(
                                "o" to "<http://example.org/s6>",
                                "p2" to "<http://example.org/p6>",
                                "o2" to "_:o6"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>",true,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s33212",
                            "#p33212",
                            "#o33212"
                        ), listOf(
                            mutableMapOf(
                                "#s33212" to null,
                                "#p33212" to null,
                                "#o33212" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>",true,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#s33219",
                            "#p33219",
                            "#o33219"
                        ), listOf(
                            mutableMapOf(
                                "#s33219" to null,
                                "#p33219" to null,
                                "#o33219" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://xmlns.com/foaf/0.1/givenName>","\"Tom\""))
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:tom@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            mutableMapOf(
                                "s" to "<http://example.org/tom>",
                                "p" to "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>",
                                "o" to "<http://xmlns.com/foaf/0.1/Person>"
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/tom>",
                                "p" to "<http://xmlns.com/foaf/0.1/givenName>",
                                "o" to "\"Tom\""
                            ),
                            mutableMapOf(
                                "s" to "<http://example.org/tom>",
                                "p" to "<http://xmlns.com/foaf/0.1/mbox>",
                                "o" to "<mailto:tom@example.org>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34833",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34833" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34834",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34834" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34834" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34872",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34872" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34873",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34873" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34873" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34982",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34982" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p34983",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34983" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p34983" to null,
                                "z" to "\"foobar\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35010",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35010" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35011",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35011" to null,
                                "z" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35011" to null,
                                "z" to "\"foobar\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35123",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35123" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35122",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35122" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35168",
                            "z"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35168" to null,
                                "z" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35167",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35167" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35280",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35280" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35310",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35310" to null,
                                "y" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35396",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35396" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35396" to null,
                                "l" to "<http://www.example.org/schema#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35421",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35421" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35421" to null,
                                "l" to "<http://www.example.org/schema#a>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35506",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35506" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35530",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35530" to null,
                                "l" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35627",
                            "l"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35627" to null,
                                "l" to "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#p35626",
                            "y"
                        ), listOf(
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#a>",
                                "#p35626" to null,
                                "y" to "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            ),
                            mutableMapOf(
                                "x" to "<http://www.example.org/instance#b>",
                                "#p35626" to null,
                                "y" to "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37037",
                            "#o37037"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37037" to null,
                                "#o37037" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37037" to null,
                                "#o37037" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_36940","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_36941","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                        graph.addData(1L,listOf("_:_36942","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                        graph.addData(1L,listOf("_:_36943","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_36944","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                        graph.addData(1L,listOf("_:_36945","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_36946","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                        graph.addData(1L,listOf("_:_36947","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_36948","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                        graph.addData(1L,listOf("_:_36949","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_36963","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_36963",
                            "#p37034",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37034" to null,
                                "L" to "\"Pizza\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37034" to null,
                                "L" to "\"Wine\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37034" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37034" to null,
                                "L" to "\"Pasta\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37034" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36946",
                                "#p37034" to null,
                                "L" to "\"Sandwich\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36947",
                                "#p37034" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36948",
                                "#p37034" to null,
                                "L" to "\"Bagel\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36949",
                                "#p37034" to null,
                                "L" to "\"Soft Drink\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36940"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36941"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36942"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36943"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36944"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36945"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36946"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36947"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36948"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36949"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_36963",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37035",
                            "#_36963"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36940"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36941"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37035" to null,
                                "#_36963" to "_:_36942"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36943"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36944"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37035" to null,
                                "#_36963" to "_:_36945"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37035" to null,
                                "#_36963" to "_:_36946"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37035" to null,
                                "#_36963" to "_:_36947"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37035" to null,
                                "#_36963" to "_:_36948"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37035" to null,
                                "#_36963" to "_:_36949"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36940"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36941"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36942"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36943"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36944"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36945"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36946"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36947"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36948"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36949"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_36963",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37056",
                            "#_36963"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36940"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36941"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37056" to null,
                                "#_36963" to "_:_36942"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36943"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36944"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37056" to null,
                                "#_36963" to "_:_36945"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37056" to null,
                                "#_36963" to "_:_36946"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37056" to null,
                                "#_36963" to "_:_36947"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37056" to null,
                                "#_36963" to "_:_36948"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37056" to null,
                                "#_36963" to "_:_36949"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37058",
                            "#o37058"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37058" to null,
                                "#o37058" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37058" to null,
                                "#o37058" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_36940","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_36941","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                        graph.addData(1L,listOf("_:_36942","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                        graph.addData(1L,listOf("_:_36943","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_36944","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                        graph.addData(1L,listOf("_:_36945","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_36946","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                        graph.addData(1L,listOf("_:_36947","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_36948","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                        graph.addData(1L,listOf("_:_36949","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_36963","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_36963",
                            "#p37055",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "#_36963" to "_:_36940",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36941",
                                "#p37055" to null,
                                "L" to "\"Pizza\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36942",
                                "#p37055" to null,
                                "L" to "\"Wine\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36943",
                                "#p37055" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36944",
                                "#p37055" to null,
                                "L" to "\"Pasta\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36945",
                                "#p37055" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36946",
                                "#p37055" to null,
                                "L" to "\"Sandwich\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36947",
                                "#p37055" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36948",
                                "#p37055" to null,
                                "L" to "\"Bagel\""
                            ),
                            mutableMapOf(
                                "#_36963" to "_:_36949",
                                "#p37055" to null,
                                "L" to "\"Soft Drink\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/firstName>","F",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "#p37177",
                            "F"
                        ), listOf(
                            mutableMapOf(
                                "P" to "<http://p1>",
                                "#p37177" to null,
                                "F" to "\"John\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/lastName>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "#p37178",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "P" to "<http://p1>",
                                "#p37178" to null,
                                "L" to "\"Doe\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/firstName>","F",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "#p37232",
                            "F"
                        ), listOf(
                            mutableMapOf(
                                "P" to "<http://p1>",
                                "#p37232" to null,
                                "F" to "\"John\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/lastName>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "#p37233",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "P" to "<http://p1>",
                                "#p37233" to null,
                                "L" to "\"Doe\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37305"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37306"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37307"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37308"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37309"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37310"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37311"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37312"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37313"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37314"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37328",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37400",
                            "#_37328"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37305"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37306"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37400" to null,
                                "#_37328" to "_:_37307"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37308"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37309"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37400" to null,
                                "#_37328" to "_:_37310"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37400" to null,
                                "#_37328" to "_:_37311"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37400" to null,
                                "#_37328" to "_:_37312"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37400" to null,
                                "#_37328" to "_:_37313"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37400" to null,
                                "#_37328" to "_:_37314"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37402",
                            "#o37402"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37402" to null,
                                "#o37402" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37402" to null,
                                "#o37402" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_37305","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_37306","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                        graph.addData(1L,listOf("_:_37307","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                        graph.addData(1L,listOf("_:_37308","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_37309","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                        graph.addData(1L,listOf("_:_37310","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_37311","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                        graph.addData(1L,listOf("_:_37312","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_37313","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                        graph.addData(1L,listOf("_:_37314","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37328","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_37328",
                            "#p37399",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37399" to null,
                                "L" to "\"Pizza\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37399" to null,
                                "L" to "\"Wine\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37399" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37399" to null,
                                "L" to "\"Pasta\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37399" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37311",
                                "#p37399" to null,
                                "L" to "\"Sandwich\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37312",
                                "#p37399" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37313",
                                "#p37399" to null,
                                "L" to "\"Bagel\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37314",
                                "#p37399" to null,
                                "L" to "\"Soft Drink\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_37305","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_37306","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                        graph.addData(1L,listOf("_:_37307","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                        graph.addData(1L,listOf("_:_37308","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                        graph.addData(1L,listOf("_:_37309","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                        graph.addData(1L,listOf("_:_37310","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_37311","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                        graph.addData(1L,listOf("_:_37312","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        graph.addData(1L,listOf("_:_37313","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                        graph.addData(1L,listOf("_:_37314","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37328","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_37328",
                            "#p37420",
                            "L"
                        ), listOf(
                            mutableMapOf(
                                "#_37328" to "_:_37305",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37306",
                                "#p37420" to null,
                                "L" to "\"Pizza\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37307",
                                "#p37420" to null,
                                "L" to "\"Wine\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37308",
                                "#p37420" to null,
                                "L" to "\"Ice Cream\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37309",
                                "#p37420" to null,
                                "L" to "\"Pasta\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37310",
                                "#p37420" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37311",
                                "#p37420" to null,
                                "L" to "\"Sandwich\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37312",
                                "#p37420" to null,
                                "L" to "\"Soft Drink\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37313",
                                "#p37420" to null,
                                "L" to "\"Bagel\""
                            ),
                            mutableMapOf(
                                "#_37328" to "_:_37314",
                                "#p37420" to null,
                                "L" to "\"Soft Drink\""
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37423",
                            "#o37423"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37423" to null,
                                "#o37423" to null
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37423" to null,
                                "#o37423" to null
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37305"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37306"))
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37307"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37308"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37309"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37310"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37311"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37312"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37313"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37314"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37328",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "O",
                            "#p37421",
                            "#_37328"
                        ), listOf(
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37305"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37306"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder1>",
                                "#p37421" to null,
                                "#_37328" to "_:_37307"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37308"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37309"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder2>",
                                "#p37421" to null,
                                "#_37328" to "_:_37310"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37421" to null,
                                "#_37328" to "_:_37311"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder3>",
                                "#p37421" to null,
                                "#_37328" to "_:_37312"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37421" to null,
                                "#_37328" to "_:_37313"
                            ),
                            mutableMapOf(
                                "O" to "<http://www.example.orgorder4>",
                                "#p37421" to null,
                                "#_37328" to "_:_37314"
                            )
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/name>","name",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "#p37666",
                            "name"
                        ), listOf(
                            mutableMapOf(
                                "person" to "<http://example.org/ns#a>",
                                "#p37666" to null,
                                "name" to "\"Alan\""
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "#p37665",
                            "#o37665"
                        ), listOf(
                            mutableMapOf(
                                "person" to "<http://example.org/ns#a>",
                                "#p37665" to null,
                                "#o37665" to null
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:ben@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/mbox>","mbox",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "#p37676",
                            "mbox"
                        ), listOf(
                            mutableMapOf(
                                "person" to "<http://example.org/ns#b>",
                                "#p37676" to null,
                                "mbox" to "<mailto:ben@example.org>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /*{
                val dictionary=ResultSetDictionary()
                MicroTestPN(
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://xmlns.com/foaf/0.1/homepage>","<http://example.org/alan>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/homepage>","homepage",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "#p37674",
                            "homepage"
                        ), listOf(
                            mutableMapOf(
                                "person" to "<http://example.org/ns#a>",
                                "#p37674" to null,
                                "homepage" to "<http://example.org/alan>"
                            )
                        )
                    )
                )
            }()*/ /* resources/sparql11-test-suite/subquery/sq14.rq */
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
                } else if (data.input is POPBase) {
                    val input = data.input as POPBase
                    assertTrue(data.expected is POPValues)
                    val output = QueryResultToXML.toXML(input).first()
                    val expected = QueryResultToXML.toXML(data.expected as POPValues).first()
                    if (!expected.myEquals(output)){
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
