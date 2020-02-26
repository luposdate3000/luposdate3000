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


class GeneratedTripleStoreIteratorGlobalTest {
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
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/givenName>", "\"William\""))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bill@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map,
                                GeneratedMutableMap.map360map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/givenName>", "\"William\""))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bill@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map363map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/givenName>", "\"William\""))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bill@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map366map,
                                GeneratedMutableMap.map367map,
                                GeneratedMutableMap.map368map,
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map,
                                GeneratedMutableMap.map360map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/givenName>", "\"William\""))
                            graph.addData(1L, listOf("<http://example.org/william>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bill@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map369map,
                                GeneratedMutableMap.map370map,
                                GeneratedMutableMap.map371map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map363map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map366map,
                                GeneratedMutableMap.map367map,
                                GeneratedMutableMap.map368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map369map,
                                GeneratedMutableMap.map370map,
                                GeneratedMutableMap.map371map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Susan\""))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:sue@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map366map,
                                GeneratedMutableMap.map367map,
                                GeneratedMutableMap.map368map,
                                GeneratedMutableMap.map438map,
                                GeneratedMutableMap.map439map,
                                GeneratedMutableMap.map440map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Susan\""))
                            graph.addData(1L, listOf("<http://example.org/sue>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:sue@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map369map,
                                GeneratedMutableMap.map370map,
                                GeneratedMutableMap.map371map,
                                GeneratedMutableMap.map441map,
                                GeneratedMutableMap.map442map,
                                GeneratedMutableMap.map443map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-03.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/sue>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map366map,
                                GeneratedMutableMap.map367map,
                                GeneratedMutableMap.map516map,
                                GeneratedMutableMap.map368map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/givenName>", "\"John\""))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/sue>"))
                            graph.addData(1L, listOf("<http://example.org/john>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:johnny@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map369map,
                                GeneratedMutableMap.map370map,
                                GeneratedMutableMap.map517map,
                                GeneratedMutableMap.map371map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/add/add-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o1>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o2>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o3>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p2>", "<http://www.example.org/o1>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p2>", "<http://www.example.org/o2>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "P", "O", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map1004map,
                                GeneratedMutableMap.map1005map,
                                GeneratedMutableMap.map1006map,
                                GeneratedMutableMap.map1007map,
                                GeneratedMutableMap.map1008map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o1>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o2>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p1>", "<http://www.example.org/o3>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p2>", "<http://www.example.org/o1>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p2>", "<http://www.example.org/o2>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("S"), AOPVariable("P"), AOPVariable("O"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("P"),
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map1009map,
                                GeneratedMutableMap.map1010map,
                                GeneratedMutableMap.map1011map,
                                GeneratedMutableMap.map1012map,
                                GeneratedMutableMap.map1013map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/p>", "O1", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "S",
                                "O1"
                        ), listOf(
                                GeneratedMutableMap.map2789map,
                                GeneratedMutableMap.map2790map,
                                GeneratedMutableMap.map2791map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("S"), AOPVariable.calculate("<http://www.example.org/p>"), AOPVariable("O1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O1")
                        ), listOf(
                                GeneratedMutableMap.map2792map,
                                GeneratedMutableMap.map2793map,
                                GeneratedMutableMap.map2794map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "S", "<http://www.example.org/q>", "O2", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "S",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2796map,
                                GeneratedMutableMap.map2797map,
                                GeneratedMutableMap.map2798map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/s>", "<http://www.example.org/q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("S"), AOPVariable.calculate("<http://www.example.org/q>"), AOPVariable("O2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("S"),
                                AOPVariable("O2")
                        ), listOf(
                                GeneratedMutableMap.map2792map,
                                GeneratedMutableMap.map2793map,
                                GeneratedMutableMap.map2794map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://www.example.org/dec>", "o", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3336map,
                                GeneratedMutableMap.map3337map,
                                GeneratedMutableMap.map3338map,
                                GeneratedMutableMap.map3339map,
                                GeneratedMutableMap.map3340map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://www.example.org/dec>"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map3341map,
                                GeneratedMutableMap.map3342map,
                                GeneratedMutableMap.map3343map,
                                GeneratedMutableMap.map3344map,
                                GeneratedMutableMap.map3345map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/double>", "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3507map,
                                GeneratedMutableMap.map3508map,
                                GeneratedMutableMap.map3509map,
                                GeneratedMutableMap.map3510map,
                                GeneratedMutableMap.map3511map,
                                GeneratedMutableMap.map3512map,
                                GeneratedMutableMap.map3513map,
                                GeneratedMutableMap.map3514map,
                                GeneratedMutableMap.map3515map,
                                GeneratedMutableMap.map3516map,
                                GeneratedMutableMap.map3517map,
                                GeneratedMutableMap.map3518map,
                                GeneratedMutableMap.map3519map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"0.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/double>", "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map3520map,
                                GeneratedMutableMap.map3521map,
                                GeneratedMutableMap.map3522map,
                                GeneratedMutableMap.map3523map,
                                GeneratedMutableMap.map3524map,
                                GeneratedMutableMap.map3525map,
                                GeneratedMutableMap.map3526map,
                                GeneratedMutableMap.map3527map,
                                GeneratedMutableMap.map3528map,
                                GeneratedMutableMap.map3529map,
                                GeneratedMutableMap.map3530map,
                                GeneratedMutableMap.map3531map,
                                GeneratedMutableMap.map3532map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/double>", "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3507map,
                                GeneratedMutableMap.map3508map,
                                GeneratedMutableMap.map3509map,
                                GeneratedMutableMap.map3510map,
                                GeneratedMutableMap.map3511map,
                                GeneratedMutableMap.map3512map,
                                GeneratedMutableMap.map3513map,
                                GeneratedMutableMap.map3514map,
                                GeneratedMutableMap.map3515map,
                                GeneratedMutableMap.map3516map,
                                GeneratedMutableMap.map3517map,
                                GeneratedMutableMap.map4270map,
                                GeneratedMutableMap.map3519map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"1.0\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/decimals>", "<http://www.example.org/dec>", "\"3.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"1.0E2\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"2.0E3\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/doubles>", "<http://www.example.org/double>", "\"3.0E4\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/ints>", "<http://www.example.org/int>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed1>", "<http://www.example.org/int>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/dec>", "\"2.2\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                            graph.addData(1L, listOf("<http://www.example.org/mixed2>", "<http://www.example.org/double>", "\"2E-1\"^^<http://www.w3.org/2001/XMLSchema#double>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map3520map,
                                GeneratedMutableMap.map3521map,
                                GeneratedMutableMap.map3522map,
                                GeneratedMutableMap.map3523map,
                                GeneratedMutableMap.map3524map,
                                GeneratedMutableMap.map3525map,
                                GeneratedMutableMap.map3526map,
                                GeneratedMutableMap.map3527map,
                                GeneratedMutableMap.map3528map,
                                GeneratedMutableMap.map3529map,
                                GeneratedMutableMap.map3530map,
                                GeneratedMutableMap.map4271map,
                                GeneratedMutableMap.map3532map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map3336map,
                            GeneratedMutableMap.map3337map,
                            GeneratedMutableMap.map3338map,
                            GeneratedMutableMap.map3339map,
                            GeneratedMutableMap.map3340map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "p"
                        ), listOf(
                            GeneratedMutableMap.map5018map,
                            GeneratedMutableMap.map5019map,
                            GeneratedMutableMap.map5020map,
                            GeneratedMutableMap.map5021map,
                            GeneratedMutableMap.map5022map,
                            GeneratedMutableMap.map5023map,
                            GeneratedMutableMap.map5024map,
                            GeneratedMutableMap.map5025map,
                            GeneratedMutableMap.map5026map,
                            GeneratedMutableMap.map5027map,
                            GeneratedMutableMap.map5028map,
                            GeneratedMutableMap.map5029map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5379map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/update-silent/add-silent.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5380map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/update-silent/add-silent.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o2>"))
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5443map,
                                GeneratedMutableMap.map5379map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-data-named2.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o2>"))
                            graph.addData(1L, listOf("<http://example.org/ns#s>", "<http://example.org/ns#p>", "<http://example.org/ns#o>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5444map,
                                GeneratedMutableMap.map5380map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-data-named2.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"o\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5523map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"o\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5524map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"o\""))
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5523map,
                                GeneratedMutableMap.map5526map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"o\""))
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5524map,
                                GeneratedMutableMap.map5527map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5526map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5527map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://example.org/b>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5664map,
                                GeneratedMutableMap.map5526map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://example.org/b>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/s>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5665map,
                                GeneratedMutableMap.map5527map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/p>", "\"y\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5741map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/p>", "\"y\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5742map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/p>", "\"z\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5744map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/p>", "\"z\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5745map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://example.org/b>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/p>", "\"q\""))
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5664map,
                                GeneratedMutableMap.map5748map,
                                GeneratedMutableMap.map5749map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://example.org/b>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/p>", "\"q\""))
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/p>", "\"q\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map5665map,
                                GeneratedMutableMap.map5750map,
                                GeneratedMutableMap.map5751map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:b","<http://example.org/p>","<http://example.org/o>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"S","P","O",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "S",
                            "P",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map5964map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-05a.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","<http://example.org/o>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/p>","<http://example.org/o>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6399map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/q>","<http://example.org/r>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/q>","<http://example.org/r>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map6399map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/basic-update/insert-where-same-bnode.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map6788map,
                                GeneratedMutableMap.map6789map,
                                GeneratedMutableMap.map6790map,
                                GeneratedMutableMap.map6791map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map6784map,
                            GeneratedMutableMap.map6785map,
                            GeneratedMutableMap.map6786map,
                            GeneratedMutableMap.map6787map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/bind/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map7260map,
                            GeneratedMutableMap.map7261map,
                            GeneratedMutableMap.map7262map,
                            GeneratedMutableMap.map7263map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/bind/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/p>", "v", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map8216map,
                                GeneratedMutableMap.map8217map,
                                GeneratedMutableMap.map8218map,
                                GeneratedMutableMap.map8219map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/p>", "\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/p>"), AOPVariable("v"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map8220map,
                                GeneratedMutableMap.map8221map,
                                GeneratedMutableMap.map8222map,
                                GeneratedMutableMap.map8223map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://purl.org/dc/elements/1.1/title>", "title", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8578map,
                                GeneratedMutableMap.map8579map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("book"), AOPVariable.calculate("<http://purl.org/dc/elements/1.1/title>"), AOPVariable("title"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title")
                        ), listOf(
                                GeneratedMutableMap.map8580map,
                                GeneratedMutableMap.map8581map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://example.org/ns#price>", "price", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8583map,
                                GeneratedMutableMap.map8584map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("book"), AOPVariable.calculate("<http://example.org/ns#price>"), AOPVariable("price"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("price")
                        ), listOf(
                                GeneratedMutableMap.map8585map,
                                GeneratedMutableMap.map8586map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8764map,
                                GeneratedMutableMap.map8765map,
                                GeneratedMutableMap.map8766map,
                                GeneratedMutableMap.map8767map,
                                GeneratedMutableMap.map8768map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p1"), AOPVariable("o1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p2", "o2", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8770map,
                                GeneratedMutableMap.map8771map,
                                GeneratedMutableMap.map8772map,
                                GeneratedMutableMap.map8773map,
                                GeneratedMutableMap.map8774map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "o2", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map9143map,
                                GeneratedMutableMap.map9144map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("o2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map9145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8764map,
                                GeneratedMutableMap.map8765map,
                                GeneratedMutableMap.map8766map,
                                GeneratedMutableMap.map9147map,
                                GeneratedMutableMap.map8767map,
                                GeneratedMutableMap.map8768map,
                                GeneratedMutableMap.map9148map,
                                GeneratedMutableMap.map9149map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p1"), AOPVariable("o1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p1"),
                                AOPVariable("o1")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map9151map,
                                GeneratedMutableMap.map9152map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/description>", "\"Graph 1\""))
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/name>", "\"G1\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9514map,
                                GeneratedMutableMap.map9515map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-default-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/description>", "\"Graph 1\""))
                            graph.addData(1L, listOf("<http://example.org/g1>", "<http://example.org/name>", "\"G1\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9516map,
                                GeneratedMutableMap.map9517map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-default-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/name>", "\"G2\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9519map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-graph-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/g2>", "<http://example.org/name>", "\"G2\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9520map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-graph-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<>", "<http://example.org/name>", "\"Default Graph\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9551map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-named-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<>", "<http://example.org/name>", "\"Default Graph\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9552map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/drop/drop-named-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9723map,
                                GeneratedMutableMap.map9724map,
                                GeneratedMutableMap.map9725map,
                                GeneratedMutableMap.map9726map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map9727map,
                                GeneratedMutableMap.map9728map,
                                GeneratedMutableMap.map9729map,
                                GeneratedMutableMap.map9730map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p>","o",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map9860map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/construct/constructwhere02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s2","<http://example.org/p>","o",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s2",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map9861map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/construct/constructwhere02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map9974map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/construct/constructwhere03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9975map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/construct/constructwhere03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Ronnie\""))
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:ronnie@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10134map,
                                GeneratedMutableMap.map10135map,
                                GeneratedMutableMap.map10136map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/copy/copy-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Ronnie\""))
                            graph.addData(1L, listOf("<http://example.org/ron>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:ronnie@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10137map,
                                GeneratedMutableMap.map10138map,
                                GeneratedMutableMap.map10139map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/copy/copy-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Jerry\""))
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:jerry@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10205map,
                                GeneratedMutableMap.map10206map,
                                GeneratedMutableMap.map10207map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Jerry\""))
                            graph.addData(1L, listOf("<http://example.org/jerry>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:jerry@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10208map,
                                GeneratedMutableMap.map10209map,
                                GeneratedMutableMap.map10210map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10419map,
                            GeneratedMutableMap.map10420map,
                            GeneratedMutableMap.map10421map,
                            GeneratedMutableMap.map10422map,
                            GeneratedMutableMap.map10423map,
                            GeneratedMutableMap.map10424map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map10421map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map10423map,
                                GeneratedMutableMap.map10424map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10607map,
                            GeneratedMutableMap.map10608map,
                            GeneratedMutableMap.map10609map,
                            GeneratedMutableMap.map10610map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10607map,
                                GeneratedMutableMap.map10608map,
                                GeneratedMutableMap.map10609map,
                                GeneratedMutableMap.map10610map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map10504map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map10506map,
                                GeneratedMutableMap.map10507map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map10812map,
                            GeneratedMutableMap.map10813map,
                            GeneratedMutableMap.map10814map,
                            GeneratedMutableMap.map10815map,
                            GeneratedMutableMap.map10816map,
                            GeneratedMutableMap.map10817map,
                            GeneratedMutableMap.map10818map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10812map,
                                GeneratedMutableMap.map10813map,
                                GeneratedMutableMap.map10814map,
                                GeneratedMutableMap.map10815map,
                                GeneratedMutableMap.map10816map,
                                GeneratedMutableMap.map10817map,
                                GeneratedMutableMap.map10818map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10879map,
                                GeneratedMutableMap.map10880map,
                                GeneratedMutableMap.map10881map,
                                GeneratedMutableMap.map10882map,
                                GeneratedMutableMap.map10883map,
                                GeneratedMutableMap.map10884map,
                                GeneratedMutableMap.map10885map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-where/delete-where-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-where/delete-where-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map11151map,
                                GeneratedMutableMap.map11152map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map11153map,
                                GeneratedMutableMap.map11154map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/mbox>", "\"dan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/name>", "\"Dan\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11156map,
                                GeneratedMutableMap.map11151map,
                                GeneratedMutableMap.map11152map,
                                GeneratedMutableMap.map11157map,
                                GeneratedMutableMap.map11158map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/d>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "\"chris@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/mbox>", "\"dan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/d>", "<http://xmlns.com/foaf/0.1/name>", "\"Dan\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11159map,
                                GeneratedMutableMap.map11153map,
                                GeneratedMutableMap.map11154map,
                                GeneratedMutableMap.map11160map,
                                GeneratedMutableMap.map11161map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map8658map,
                            GeneratedMutableMap.map8659map,
                            GeneratedMutableMap.map8660map,
                            GeneratedMutableMap.map8661map,
                            GeneratedMutableMap.map8662map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map8658map,
                            GeneratedMutableMap.map11150map,
                            GeneratedMutableMap.map8661map,
                            GeneratedMutableMap.map8662map,
                            GeneratedMutableMap.map11151map,
                            GeneratedMutableMap.map11152map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-where/delete-where-06.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "b", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11400map,
                                GeneratedMutableMap.map11401map,
                                GeneratedMutableMap.map11402map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("b"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("b")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map,
                                GeneratedMutableMap.map9145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11405map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map11406map,
                                GeneratedMutableMap.map11407map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map11408map,
                                GeneratedMutableMap.map11409map,
                                GeneratedMutableMap.map11410map,
                                GeneratedMutableMap.map11411map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11412map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map11413map,
                                GeneratedMutableMap.map11414map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map11415map,
                                GeneratedMutableMap.map11416map,
                                GeneratedMutableMap.map11417map,
                                GeneratedMutableMap.map11418map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map11400map,
                            GeneratedMutableMap.map11401map,
                            GeneratedMutableMap.map11402map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map11405map,
                            GeneratedMutableMap.map8660map,
                            GeneratedMutableMap.map11406map,
                            GeneratedMutableMap.map11407map,
                            GeneratedMutableMap.map8662map,
                            GeneratedMutableMap.map11408map,
                            GeneratedMutableMap.map11409map,
                            GeneratedMutableMap.map11410map,
                            GeneratedMutableMap.map11411map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete-insert/delete-insert-01c.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\"", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "a"
                        ), listOf(
                                GeneratedMutableMap.map11674map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Alan\""), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("a")
                        ), listOf(
                                GeneratedMutableMap.map11675map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11405map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map11407map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map11410map,
                                GeneratedMutableMap.map11411map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map11412map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map11414map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map11417map,
                                GeneratedMutableMap.map11418map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11913map,
                                GeneratedMutableMap.map11914map,
                                GeneratedMutableMap.map11915map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("Var_B"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                                GeneratedMutableMap.map8668map,
                                GeneratedMutableMap.map11403map,
                                GeneratedMutableMap.map9145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map12052map,
                                GeneratedMutableMap.map11405map,
                                GeneratedMutableMap.map8660map,
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map11407map,
                                GeneratedMutableMap.map8662map,
                                GeneratedMutableMap.map11410map,
                                GeneratedMutableMap.map11411map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/a>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:alan@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:bob@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:claire@example.org>"))
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Claire\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map12053map,
                                GeneratedMutableMap.map11412map,
                                GeneratedMutableMap.map8665map,
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map11414map,
                                GeneratedMutableMap.map8667map,
                                GeneratedMutableMap.map11417map,
                                GeneratedMutableMap.map11418map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "a", "<http://xmlns.com/foaf/0.1/knows>", "Var_B", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11915map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("Var_B"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("Var_B")
                        ), listOf(
                                GeneratedMutableMap.map9145map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "s", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map13081map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/a>"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable("s"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map13082map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map8659map,
                                GeneratedMutableMap.map8660map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/mbox>", "\"alan@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/name>", "\"Alan\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map8664map,
                                GeneratedMutableMap.map8665map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\"", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map13502map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/c>", "<http://xmlns.com/foaf/0.1/name>", "\"Chris\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"Chris\""), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map13503map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8658map,
                                GeneratedMutableMap.map11150map,
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/c>"))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8663map,
                                GeneratedMutableMap.map9150map,
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","s",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map13081map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-07.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map8658map,
                            GeneratedMutableMap.map8659map,
                            GeneratedMutableMap.map8660map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-07.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map11156map,
                            GeneratedMutableMap.map11151map,
                            GeneratedMutableMap.map11152map,
                            GeneratedMutableMap.map11157map,
                            GeneratedMutableMap.map11158map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map13502map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map11157map,
                            GeneratedMutableMap.map11158map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-04.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map14273map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/a>", "<http://xmlns.com/foaf/0.1/knows>", "<http://example.org/b>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"), AOPVariable.calculate("<http://example.org/b>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map11675map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8661map,
                                GeneratedMutableMap.map8662map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/mbox>", "\"bob@example.org\""))
                            graph.addData(1L, listOf("<http://example.org/b>", "<http://xmlns.com/foaf/0.1/name>", "\"Bob\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map8666map,
                                GeneratedMutableMap.map8667map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/name>","\"Chris\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map13502map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map8658map,
                            GeneratedMutableMap.map11150map,
                            GeneratedMutableMap.map8661map,
                            GeneratedMutableMap.map8662map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/delete/delete-using-06.ru */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#myBanana>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15815map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#myBanana>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/ns#banana>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/ns#banana>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15816map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c1"))
                            graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c2"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://example.org/ns#b1>", "#c", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                        ), listOf(
                                GeneratedMutableMap.map15855map,
                                GeneratedMutableMap.map15856map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c1"))
                            graph.addData(1L, listOf("<http://example.org/ns#a1>", "<http://example.org/ns#b1>", "_:c2"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://example.org/ns#b1>"), AOPVariable("#c"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("#c")
                        ), listOf(
                                GeneratedMutableMap.map15857map,
                                GeneratedMutableMap.map15858map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15902map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/x/c>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map15903map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://example.org/ns#b1>","<http://example.org/ns#c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#a>","x","<http://example.org/ns#c>",true,false,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15941map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/c>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/x/d>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/x/d>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16118map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16119map,
                            GeneratedMutableMap.map16120map,
                            GeneratedMutableMap.map16121map,
                            GeneratedMutableMap.map16122map,
                            GeneratedMutableMap.map16123map,
                            GeneratedMutableMap.map16124map,
                            GeneratedMutableMap.map16125map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "x", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16247map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#d>", "<http://www.w3.org/2000/01/rdf-schema#range>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/ns#d>"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#range>"), AOPVariable("x"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map16248map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://example.org/ns#b>","<http://example.org/ns#c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#a>","x","<http://example.org/ns#c>",true,false,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16388map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16388map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs11.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "<http://example.org/ns#apple>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "f", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map16433map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/ns#favourite-fruit>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>", "<http://example.org/ns#apple>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/ns#favourite-fruit>"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>"), AOPVariable("f"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("f")
                        ), listOf(
                                GeneratedMutableMap.map16434map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/d>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "_:x"))
                            graph.addData(1L, listOf("<http://example.org/x/c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                            graph.addData(1L, listOf("<http://example.org/x/d>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                            graph.addData(1L, listOf("<http://example.org/x/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            graph.addData(1L, listOf("_:ont", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                            graph.addData(1L, listOf("_:x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Restriction>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "c", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                        ), listOf(
                                GeneratedMutableMap.map16584map,
                                GeneratedMutableMap.map16585map,
                                GeneratedMutableMap.map16586map,
                                GeneratedMutableMap.map16587map,
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16120map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16588map,
                                GeneratedMutableMap.map16589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/d>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                            graph.addData(1L, listOf("<http://example.org/x/a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "_:x"))
                            graph.addData(1L, listOf("<http://example.org/x/c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                            graph.addData(1L, listOf("<http://example.org/x/d>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Class>"))
                            graph.addData(1L, listOf("<http://example.org/x/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            graph.addData(1L, listOf("_:ont", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Ontology>"))
                            graph.addData(1L, listOf("_:x", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#Restriction>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable("c"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("c")
                        ), listOf(
                                GeneratedMutableMap.map16590map,
                                GeneratedMutableMap.map16591map,
                                GeneratedMutableMap.map16592map,
                                GeneratedMutableMap.map16593map,
                                GeneratedMutableMap.map16594map,
                                GeneratedMutableMap.map16595map,
                                GeneratedMutableMap.map16596map,
                                GeneratedMutableMap.map16597map,
                                GeneratedMutableMap.map16598map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/y>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            graph.addData(1L, listOf("_:y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16660map,
                                GeneratedMutableMap.map16661map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/y>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            graph.addData(1L, listOf("_:y", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/x/c>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("y"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/x/c>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map16662map,
                                GeneratedMutableMap.map16663map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "<http://example.org/x/y>"))
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "_:y"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://example.org/x/p>", "y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16665map,
                                GeneratedMutableMap.map16666map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "<http://example.org/x/y>"))
                            graph.addData(1L, listOf("<http://example.org/x/x>", "<http://example.org/x/p>", "_:y"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://example.org/x/p>"), AOPVariable("y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map16667map,
                                GeneratedMutableMap.map16668map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Student>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16711map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("_:_16753","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c"
                        ), listOf(
                            GeneratedMutableMap.map16879map,
                            GeneratedMutableMap.map16880map,
                            GeneratedMutableMap.map16881map,
                            GeneratedMutableMap.map16882map,
                            GeneratedMutableMap.map16883map,
                            GeneratedMutableMap.map16884map,
                            GeneratedMutableMap.map16885map,
                            GeneratedMutableMap.map16886map,
                            GeneratedMutableMap.map16887map,
                            GeneratedMutableMap.map16888map,
                            GeneratedMutableMap.map16889map,
                            GeneratedMutableMap.map16890map,
                            GeneratedMutableMap.map16891map,
                            GeneratedMutableMap.map16892map,
                            GeneratedMutableMap.map16893map,
                            GeneratedMutableMap.map16894map,
                            GeneratedMutableMap.map16895map,
                            GeneratedMutableMap.map16896map,
                            GeneratedMutableMap.map16897map,
                            GeneratedMutableMap.map16898map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/name>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16899map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16929","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16929"
                        ), listOf(
                            GeneratedMutableMap.map17028map,
                            GeneratedMutableMap.map17029map,
                            GeneratedMutableMap.map17030map,
                            GeneratedMutableMap.map17031map,
                            GeneratedMutableMap.map17032map,
                            GeneratedMutableMap.map17033map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16900","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16920","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16920"
                        ), listOf(
                            GeneratedMutableMap.map17034map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16900","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://example.org/Conference>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16920","<http://www.w3.org/2002/07/owl#someValuesFrom>","#_16929",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16920",
                            "#_16929"
                        ), listOf(
                            GeneratedMutableMap.map17035map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_16900","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16920","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16920"
                        ), listOf(
                            GeneratedMutableMap.map17034map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("_:_16900","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b0","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_16920",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b0",
                            "#_16920"
                        ), listOf(
                            GeneratedMutableMap.map17036map,
                            GeneratedMutableMap.map17037map,
                            GeneratedMutableMap.map17038map,
                            GeneratedMutableMap.map17039map,
                            GeneratedMutableMap.map17040map,
                            GeneratedMutableMap.map17041map,
                            GeneratedMutableMap.map17042map,
                            GeneratedMutableMap.map17043map,
                            GeneratedMutableMap.map17044map,
                            GeneratedMutableMap.map17045map,
                            GeneratedMutableMap.map17046map,
                            GeneratedMutableMap.map17047map,
                            GeneratedMutableMap.map17048map,
                            GeneratedMutableMap.map17049map,
                            GeneratedMutableMap.map17050map,
                            GeneratedMutableMap.map17051map,
                            GeneratedMutableMap.map17052map,
                            GeneratedMutableMap.map17053map,
                            GeneratedMutableMap.map17054map,
                            GeneratedMutableMap.map17055map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "#b0"
                        ), listOf(
                            GeneratedMutableMap.map17056map,
                            GeneratedMutableMap.map17057map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/Anite>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/Student>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map17146map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Employee>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"c","<http://www.w3.org/2000/01/rdf-schema#subClassOf>","<http://example.org/Employee>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16711map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("_:_17072","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map17147map,
                            GeneratedMutableMap.map17148map,
                            GeneratedMutableMap.map17149map,
                            GeneratedMutableMap.map17150map,
                            GeneratedMutableMap.map17151map,
                            GeneratedMutableMap.map17152map,
                            GeneratedMutableMap.map17153map,
                            GeneratedMutableMap.map17154map,
                            GeneratedMutableMap.map17155map,
                            GeneratedMutableMap.map17156map,
                            GeneratedMutableMap.map17157map,
                            GeneratedMutableMap.map17158map,
                            GeneratedMutableMap.map17159map,
                            GeneratedMutableMap.map17160map,
                            GeneratedMutableMap.map17161map,
                            GeneratedMutableMap.map17162map,
                            GeneratedMutableMap.map17163map,
                            GeneratedMutableMap.map17164map,
                            GeneratedMutableMap.map17165map,
                            GeneratedMutableMap.map17166map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/hasPublication>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            graph.addData(1L, listOf("<http://example.org/publishedAt>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map17231map,
                                GeneratedMutableMap.map17232map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/hasPublication>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            graph.addData(1L, listOf("<http://example.org/publishedAt>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("p"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.w3.org/2002/07/owl#ObjectProperty>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map17233map,
                                GeneratedMutableMap.map17234map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/hasPublication>", "<http://example.org/paper1>"))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/name>", "\"Johnnie\""))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/GraduateAssistant>"))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/John>", "p", "v", true, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map17236map,
                                GeneratedMutableMap.map17237map,
                                GeneratedMutableMap.map17238map,
                                GeneratedMutableMap.map17239map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/hasPublication>", "<http://example.org/paper1>"))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://example.org/name>", "\"Johnnie\""))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/GraduateAssistant>"))
                            graph.addData(1L, listOf("<http://example.org/John>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/John>"), AOPVariable("p"), AOPVariable("v"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map17240map,
                                GeneratedMutableMap.map17241map,
                                GeneratedMutableMap.map17242map,
                                GeneratedMutableMap.map17243map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map17286map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://xmlns.com/foaf/0.1/name>", "\"name\"@en"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"), AOPVariable.calculate("\"name\"@en"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map17287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "p", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map17392map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/p>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("p"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map17393map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map17395map,
                                GeneratedMutableMap.map6784map,
                                GeneratedMutableMap.map6785map,
                                GeneratedMutableMap.map6786map,
                                GeneratedMutableMap.map6787map,
                                GeneratedMutableMap.map17396map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map17397map,
                                GeneratedMutableMap.map6788map,
                                GeneratedMutableMap.map6789map,
                                GeneratedMutableMap.map6790map,
                                GeneratedMutableMap.map6791map,
                                GeneratedMutableMap.map17398map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s1","p1","z",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map17889map,
                            GeneratedMutableMap.map7260map,
                            GeneratedMutableMap.map7261map,
                            GeneratedMutableMap.map7262map,
                            GeneratedMutableMap.map7263map,
                            GeneratedMutableMap.map17890map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map17395map,
                            GeneratedMutableMap.map6784map,
                            GeneratedMutableMap.map6785map,
                            GeneratedMutableMap.map6786map,
                            GeneratedMutableMap.map6787map,
                            GeneratedMutableMap.map17396map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p1","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p1"
                        ), listOf(
                            GeneratedMutableMap.map17891map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p"
                        ), listOf(
                            GeneratedMutableMap.map17392map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16119map,
                            GeneratedMutableMap.map16121map,
                            GeneratedMutableMap.map16122map,
                            GeneratedMutableMap.map16588map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/a>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/x/p>","#y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#y"
                        ), listOf(
                            GeneratedMutableMap.map18895map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16584map,
                            GeneratedMutableMap.map16586map,
                            GeneratedMutableMap.map16119map,
                            GeneratedMutableMap.map16120map,
                            GeneratedMutableMap.map16121map,
                            GeneratedMutableMap.map16122map,
                            GeneratedMutableMap.map18896map,
                            GeneratedMutableMap.map16123map,
                            GeneratedMutableMap.map18897map,
                            GeneratedMutableMap.map18898map,
                            GeneratedMutableMap.map16589map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18972map,
                                GeneratedMutableMap.map18973map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#name>"), AOPVariable("Y1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1")
                        ), listOf(
                                GeneratedMutableMap.map18974map,
                                GeneratedMutableMap.map18975map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X"
                        ), listOf(
                                GeneratedMutableMap.map18977map,
                                GeneratedMutableMap.map18978map,
                                GeneratedMutableMap.map18979map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://example.org/test#Person>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X")
                        ), listOf(
                                GeneratedMutableMap.map18980map,
                                GeneratedMutableMap.map17287map,
                                GeneratedMutableMap.map18981map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#nick>", "\"Anick\""))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#nick>", "\"Bnick\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#nick>", "Y2", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18984map,
                                GeneratedMutableMap.map18985map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#nick>", "\"Anick\""))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#nick>", "\"Bnick\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#nick>"), AOPVariable("Y2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y2")
                        ), listOf(
                                GeneratedMutableMap.map18986map,
                                GeneratedMutableMap.map18987map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "#a"
                        ), listOf(
                            GeneratedMutableMap.map19035map,
                            GeneratedMutableMap.map19036map,
                            GeneratedMutableMap.map19037map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map19122map,
                            GeneratedMutableMap.map19123map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#dd","<http://example.org/test#t>","#bb",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#dd",
                            "#bb"
                        ), listOf(
                            GeneratedMutableMap.map19124map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#aa"
                        ), listOf(
                            GeneratedMutableMap.map19125map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-06.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                            graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#aa", "<http://example.org/test#r>", "Z", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map19210map,
                                GeneratedMutableMap.map19211map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                            graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#aa"), AOPVariable.calculate("<http://example.org/test#r>"), AOPVariable("Z"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("Z")
                        ), listOf(
                                GeneratedMutableMap.map19212map,
                                GeneratedMutableMap.map19213map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                        ), listOf(
                                GeneratedMutableMap.map19215map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("Y"), AOPVariable.calculate("<http://example.org/test#s>"), AOPVariable("#aa"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("Y"),
                                AOPVariable("#aa")
                        ), listOf(
                                GeneratedMutableMap.map19216map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#t>", "Y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19218map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#dd>", "<http://example.org/test#t>", "<http://example.org/test#bb>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#t>"), AOPVariable("Y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19219map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/test#a>", "<http://example.org/test#p>", "#aa", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#aa"
                        ), listOf(
                                GeneratedMutableMap.map19125map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#aa"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#aa")
                        ), listOf(
                                GeneratedMutableMap.map19221map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                        ), listOf(
                                GeneratedMutableMap.map19286map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#a"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a")
                        ), listOf(
                                GeneratedMutableMap.map19287map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                            graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#r>", "Y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19289map,
                                GeneratedMutableMap.map19290map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                            graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#a"), AOPVariable.calculate("<http://example.org/test#r>"), AOPVariable("Y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19212map,
                                GeneratedMutableMap.map19213map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#b>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#p>", "#a", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                        ), listOf(
                                GeneratedMutableMap.map19354map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#p>", "<http://example.org/test#b>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("X"), AOPVariable.calculate("<http://example.org/test#p>"), AOPVariable("#a"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a")
                        ), listOf(
                                GeneratedMutableMap.map19355map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#c>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#h>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#i>"))
                            graph.addData(1L, listOf("<http://example.org/test#x>", "<http://example.org/test#q>", "<http://example.org/test#x>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#a", "<http://example.org/test#q>", "Y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map19357map,
                                GeneratedMutableMap.map19358map,
                                GeneratedMutableMap.map19359map,
                                GeneratedMutableMap.map19360map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#c>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#h>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#q>", "<http://example.org/test#i>"))
                            graph.addData(1L, listOf("<http://example.org/test#x>", "<http://example.org/test#q>", "<http://example.org/test#x>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#a"), AOPVariable.calculate("<http://example.org/test#q>"), AOPVariable("Y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#a"),
                                AOPVariable("Y")
                        ), listOf(
                                GeneratedMutableMap.map19361map,
                                GeneratedMutableMap.map19362map,
                                GeneratedMutableMap.map19363map,
                                GeneratedMutableMap.map19364map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "a"
                        ), listOf(
                            GeneratedMutableMap.map19465map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map19466map,
                            GeneratedMutableMap.map19467map,
                            GeneratedMutableMap.map19468map,
                            GeneratedMutableMap.map19469map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<foo://bla/names#child>","<http://www.w3.org/2000/01/rdf-schema#domain>","<foo://bla/names#Parent>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<foo://bla/names#child>","<http://www.w3.org/2000/01/rdf-schema#domain>","C",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map19558map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-12.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/2002/07/owl#sameAs>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://www.w3.org/2002/07/owl#sameAs>","x",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19634map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19635map,
                            GeneratedMutableMap.map19636map,
                            GeneratedMutableMap.map19637map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://example.org/test#p>","b",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map19638map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-13.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#Bob>", "<http://example.org/test#hasChild>", "<http://example.org/test#Charlie>"))
                            graph.addData(1L, listOf("<http://example.org/test#Dudley>", "<http://example.org/test#hasChild>", "<http://example.org/test#Alice>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "parent", "<http://example.org/test#hasChild>", "child", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "parent",
                                "child"
                        ), listOf(
                                GeneratedMutableMap.map19729map,
                                GeneratedMutableMap.map19730map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/test#Bob>", "<http://example.org/test#hasChild>", "<http://example.org/test#Charlie>"))
                            graph.addData(1L, listOf("<http://example.org/test#Dudley>", "<http://example.org/test#hasChild>", "<http://example.org/test#Alice>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("parent"), AOPVariable.calculate("<http://example.org/test#hasChild>"), AOPVariable("child"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("parent"),
                                AOPVariable("child")
                        ), listOf(
                                GeneratedMutableMap.map19731map,
                                GeneratedMutableMap.map19732map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19743","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19744","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19758","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19758"
                        ), listOf(
                            GeneratedMutableMap.map19824map,
                            GeneratedMutableMap.map19825map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19743","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19758","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19758"
                        ), listOf(
                            GeneratedMutableMap.map19824map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19744"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19737","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19740","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19743","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19744","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19745","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19758",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19758"
                        ), listOf(
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
                            GeneratedMutableMap.map19844map,
                            GeneratedMutableMap.map19845map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19743","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19744","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19758","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19758"
                        ), listOf(
                            GeneratedMutableMap.map19824map,
                            GeneratedMutableMap.map19825map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19852","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19853","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19870","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19870"
                        ), listOf(
                            GeneratedMutableMap.map19941map,
                            GeneratedMutableMap.map19942map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19852","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19853","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19870","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19870"
                        ), listOf(
                            GeneratedMutableMap.map19941map,
                            GeneratedMutableMap.map19942map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19853"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19846","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19849","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19852","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19853","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19854","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19870",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19870"
                        ), listOf(
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
                            GeneratedMutableMap.map19961map,
                            GeneratedMutableMap.map19962map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19969","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19984","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19984"
                        ), listOf(
                            GeneratedMutableMap.map20050map,
                            GeneratedMutableMap.map20051map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19970"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19963","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19966","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19969","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19971","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19984",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19984"
                        ), listOf(
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
                            GeneratedMutableMap.map20070map,
                            GeneratedMutableMap.map20071map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19969","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19984","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19984"
                        ), listOf(
                            GeneratedMutableMap.map20050map,
                            GeneratedMutableMap.map20051map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20078","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20079","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20094","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20094"
                        ), listOf(
                            GeneratedMutableMap.map20176map,
                            GeneratedMutableMap.map20177map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20078","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20079","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20094","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20094"
                        ), listOf(
                            GeneratedMutableMap.map20176map,
                            GeneratedMutableMap.map20177map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_20079"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_20072","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20075","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20078","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20079","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20080","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20094",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_20094"
                        ), listOf(
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
                            GeneratedMutableMap.map20196map,
                            GeneratedMutableMap.map20197map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20204","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20205","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20220","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20220"
                        ), listOf(
                            GeneratedMutableMap.map20302map,
                            GeneratedMutableMap.map20303map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_20205"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_20198","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20201","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20204","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20205","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20206","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20220",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_20220"
                        ), listOf(
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
                            GeneratedMutableMap.map20320map,
                            GeneratedMutableMap.map20321map,
                            GeneratedMutableMap.map20322map,
                            GeneratedMutableMap.map20323map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20204","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20205","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20220","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20220"
                        ), listOf(
                            GeneratedMutableMap.map20302map,
                            GeneratedMutableMap.map20303map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20330","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20331","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20346","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20346"
                        ), listOf(
                            GeneratedMutableMap.map20428map,
                            GeneratedMutableMap.map20429map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_20331"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_20324","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20327","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20330","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20331","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20332","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20346",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_20346"
                        ), listOf(
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
                            GeneratedMutableMap.map20446map,
                            GeneratedMutableMap.map20447map,
                            GeneratedMutableMap.map20448map,
                            GeneratedMutableMap.map20449map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20330","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20331","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20346","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20346"
                        ), listOf(
                            GeneratedMutableMap.map20428map,
                            GeneratedMutableMap.map20429map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent8.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20456","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20457","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20473","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20473"
                        ), listOf(
                            GeneratedMutableMap.map20541map,
                            GeneratedMutableMap.map20542map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20456","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20457","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20473","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20473"
                        ), listOf(
                            GeneratedMutableMap.map20541map,
                            GeneratedMutableMap.map20542map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20456","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20473","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20473"
                        ), listOf(
                            GeneratedMutableMap.map20541map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent9.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20549","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20550","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20647map,
                            GeneratedMutableMap.map20648map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20549","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20550","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20647map,
                            GeneratedMutableMap.map20648map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20549","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20647map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/parent10.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20662",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20662"
                        ), listOf(
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
                            GeneratedMutableMap.map20765map,
                            GeneratedMutableMap.map20766map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple1.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20780",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20780"
                        ), listOf(
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
                            GeneratedMutableMap.map20922map,
                            GeneratedMutableMap.map20923map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple2.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20937",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20937"
                        ), listOf(
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
                            GeneratedMutableMap.map21079map,
                            GeneratedMutableMap.map21080map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple3.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21094",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21094"
                        ), listOf(
                            GeneratedMutableMap.map21198map,
                            GeneratedMutableMap.map21199map,
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
                            GeneratedMutableMap.map21210map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21094","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21094"
                        ), listOf(
                            GeneratedMutableMap.map21211map,
                            GeneratedMutableMap.map21212map,
                            GeneratedMutableMap.map21213map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple4.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21235","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21235"
                        ), listOf(
                            GeneratedMutableMap.map21370map,
                            GeneratedMutableMap.map21371map,
                            GeneratedMutableMap.map21372map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21227",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21227"
                        ), listOf(
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
                            GeneratedMutableMap.map21384map,
                            GeneratedMutableMap.map21385map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple5.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21407","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21407"
                        ), listOf(
                            GeneratedMutableMap.map21567map,
                            GeneratedMutableMap.map21568map,
                            GeneratedMutableMap.map21569map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21399",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21399"
                        ), listOf(
                            GeneratedMutableMap.map21570map,
                            GeneratedMutableMap.map21571map,
                            GeneratedMutableMap.map21572map,
                            GeneratedMutableMap.map21573map,
                            GeneratedMutableMap.map21574map,
                            GeneratedMutableMap.map21575map,
                            GeneratedMutableMap.map21576map,
                            GeneratedMutableMap.map21577map,
                            GeneratedMutableMap.map21578map,
                            GeneratedMutableMap.map21579map,
                            GeneratedMutableMap.map21580map,
                            GeneratedMutableMap.map21581map,
                            GeneratedMutableMap.map21582map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple6.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21596","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21596"
                        ), listOf(
                            GeneratedMutableMap.map21775map,
                            GeneratedMutableMap.map21776map,
                            GeneratedMutableMap.map21777map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#A>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#B>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#C>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21605","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21605"
                        ), listOf(
                            GeneratedMutableMap.map21778map,
                            GeneratedMutableMap.map21779map,
                            GeneratedMutableMap.map21780map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21596",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21596"
                        ), listOf(
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
                            GeneratedMutableMap.map21792map,
                            GeneratedMutableMap.map21793map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple7.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21807",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21807"
                        ), listOf(
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
                            GeneratedMutableMap.map21925map,
                            GeneratedMutableMap.map21926map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/str>", "str", false, true, false, EIndexPattern.SPO)
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str"), graphName, false)
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map23453map,
                                GeneratedMutableMap.map23454map,
                                GeneratedMutableMap.map23455map,
                                GeneratedMutableMap.map23456map,
                                GeneratedMutableMap.map23457map,
                                GeneratedMutableMap.map23458map,
                                GeneratedMutableMap.map23459map,
                                GeneratedMutableMap.map23460map,
                                GeneratedMutableMap.map23461map,
                                GeneratedMutableMap.map23462map,
                                GeneratedMutableMap.map23463map,
                                GeneratedMutableMap.map23464map,
                                GeneratedMutableMap.map23465map,
                                GeneratedMutableMap.map23466map,
                                GeneratedMutableMap.map23467map,
                                GeneratedMutableMap.map23468map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
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
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s7>", "<http://example.org/str>", "str2", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24944map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s7>", "<http://example.org/str>", "\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s7>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map24945map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s6>", "<http://example.org/str>", "str1", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map24947map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s6>", "<http://example.org/str>", "\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s6>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map24948map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map25550map,
                                GeneratedMutableMap.map25551map,
                                GeneratedMutableMap.map25552map,
                                GeneratedMutableMap.map25553map,
                                GeneratedMutableMap.map25554map,
                                GeneratedMutableMap.map25555map,
                                GeneratedMutableMap.map25556map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s1"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1")
                        ), listOf(
                                GeneratedMutableMap.map25557map,
                                GeneratedMutableMap.map25558map,
                                GeneratedMutableMap.map25559map,
                                GeneratedMutableMap.map25560map,
                                GeneratedMutableMap.map25561map,
                                GeneratedMutableMap.map25562map,
                                GeneratedMutableMap.map25563map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s2", "<http://example.org/str>", "str2", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map25565map,
                                GeneratedMutableMap.map25566map,
                                GeneratedMutableMap.map25567map,
                                GeneratedMutableMap.map25568map,
                                GeneratedMutableMap.map25569map,
                                GeneratedMutableMap.map25570map,
                                GeneratedMutableMap.map25571map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s2"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("str2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s2"),
                                AOPVariable("str2")
                        ), listOf(
                                GeneratedMutableMap.map25557map,
                                GeneratedMutableMap.map25558map,
                                GeneratedMutableMap.map25559map,
                                GeneratedMutableMap.map25560map,
                                GeneratedMutableMap.map25561map,
                                GeneratedMutableMap.map25562map,
                                GeneratedMutableMap.map25563map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map27472map,
                                GeneratedMutableMap.map27473map,
                                GeneratedMutableMap.map27474map,
                                GeneratedMutableMap.map27475map,
                                GeneratedMutableMap.map27476map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27478map,
                                GeneratedMutableMap.map27479map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("x"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x")
                        ), listOf(
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27483map,
                                GeneratedMutableMap.map27484map,
                                GeneratedMutableMap.map27485map,
                                GeneratedMutableMap.map27486map,
                                GeneratedMutableMap.map27487map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map27489map,
                                GeneratedMutableMap.map27490map,
                                GeneratedMutableMap.map27491map,
                                GeneratedMutableMap.map27492map,
                                GeneratedMutableMap.map27493map,
                                GeneratedMutableMap.map27494map,
                                GeneratedMutableMap.map27495map,
                                GeneratedMutableMap.map27496map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map27497map,
                                GeneratedMutableMap.map27498map,
                                GeneratedMutableMap.map27499map,
                                GeneratedMutableMap.map27500map,
                                GeneratedMutableMap.map27501map,
                                GeneratedMutableMap.map27502map,
                                GeneratedMutableMap.map27503map,
                                GeneratedMutableMap.map27504map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map27882map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/str>", "\"foo\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map27883map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s4>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map27958map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s4>", "<http://example.org/str>", "\"食べ物\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s4>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map27959map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s8>", "<http://example.org/str>", "l", true, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map28108map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s8>", "<http://example.org/str>", "\"食\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s8>"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map28109map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example.org/date>", "date", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "date"
                        ), listOf(
                                GeneratedMutableMap.map28429map,
                                GeneratedMutableMap.map28430map,
                                GeneratedMutableMap.map28431map,
                                GeneratedMutableMap.map28432map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/d1>", "<http://example.org/date>", "\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d2>", "<http://example.org/date>", "\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d3>", "<http://example.org/date>", "\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            graph.addData(1L, listOf("<http://example.org/d4>", "<http://example.org/date>", "\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example.org/date>"), AOPVariable("date"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date")
                        ), listOf(
                                GeneratedMutableMap.map28433map,
                                GeneratedMutableMap.map28434map,
                                GeneratedMutableMap.map28435map,
                                GeneratedMutableMap.map28436map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "date"
                        ), listOf(
                            GeneratedMutableMap.map28429map,
                            GeneratedMutableMap.map28430map,
                            GeneratedMutableMap.map28431map,
                            GeneratedMutableMap.map28432map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1"
                        ), listOf(
                                GeneratedMutableMap.map31301map,
                                GeneratedMutableMap.map31302map,
                                GeneratedMutableMap.map31303map,
                                GeneratedMutableMap.map31304map,
                                GeneratedMutableMap.map31305map,
                                GeneratedMutableMap.map31306map,
                                GeneratedMutableMap.map31307map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("a"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("s1"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1")
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "b", "<http://example.org/str>", "s2", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map31309map,
                                GeneratedMutableMap.map31310map,
                                GeneratedMutableMap.map31311map,
                                GeneratedMutableMap.map31312map,
                                GeneratedMutableMap.map31313map,
                                GeneratedMutableMap.map31314map,
                                GeneratedMutableMap.map31315map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("b"), AOPVariable.calculate("<http://example.org/str>"), AOPVariable("s2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("b"),
                                AOPVariable("s2")
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
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map32095map,
                                GeneratedMutableMap.map32096map,
                                GeneratedMutableMap.map32097map,
                                GeneratedMutableMap.map32098map,
                                GeneratedMutableMap.map32099map,
                                GeneratedMutableMap.map32100map,
                                GeneratedMutableMap.map32101map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map32102map,
                                GeneratedMutableMap.map32103map,
                                GeneratedMutableMap.map32104map,
                                GeneratedMutableMap.map32105map,
                                GeneratedMutableMap.map32106map,
                                GeneratedMutableMap.map32107map,
                                GeneratedMutableMap.map32108map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map32781map,
                                GeneratedMutableMap.map32782map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("v"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map32783map,
                                GeneratedMutableMap.map32784map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/q>", "w", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map32918map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/q>", "\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/q>"), AOPVariable("w"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("w")
                        ), listOf(
                                GeneratedMutableMap.map32919map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s3>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "<http://example/p>", "v", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map32781map,
                                GeneratedMutableMap.map32782map,
                                GeneratedMutableMap.map33072map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example/s1>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s2>", "<http://example/p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example/s3>", "<http://example/p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable.calculate("<http://example/p>"), AOPVariable("v"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v")
                        ), listOf(
                                GeneratedMutableMap.map32783map,
                                GeneratedMutableMap.map32784map,
                                GeneratedMutableMap.map33073map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10419map,
                                GeneratedMutableMap.map10420map,
                                GeneratedMutableMap.map33355map,
                                GeneratedMutableMap.map10422map,
                                GeneratedMutableMap.map33356map,
                                GeneratedMutableMap.map10424map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "o", "p2", "o2", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map33485map,
                                GeneratedMutableMap.map10608map,
                                GeneratedMutableMap.map33486map,
                                GeneratedMutableMap.map10610map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("o"), AOPVariable("p2"), AOPVariable("o2"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                        ), listOf(
                                GeneratedMutableMap.map10502map,
                                GeneratedMutableMap.map10503map,
                                GeneratedMutableMap.map33357map,
                                GeneratedMutableMap.map10505map,
                                GeneratedMutableMap.map33358map,
                                GeneratedMutableMap.map10507map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>", true, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5965map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p1>", "<http://example.org/s2>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable.calculate("<http://example.org/s1>"), AOPVariable.calculate("<http://example.org/p1>"), AOPVariable.calculate("<http://example.org/s2>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                        ), listOf(
                                GeneratedMutableMap.map33543map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Tom\""))
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:tom@example.org>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map33702map,
                                GeneratedMutableMap.map33703map,
                                GeneratedMutableMap.map33704map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://xmlns.com/foaf/0.1/Person>"))
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://xmlns.com/foaf/0.1/givenName>", "\"Tom\""))
                            graph.addData(1L, listOf("<http://example.org/tom>", "<http://xmlns.com/foaf/0.1/mbox>", "<mailto:tom@example.org>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map33705map,
                                GeneratedMutableMap.map33706map,
                                GeneratedMutableMap.map33707map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map35235map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map35236map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35238map,
                                GeneratedMutableMap.map35239map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("z"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35236map,
                                GeneratedMutableMap.map35242map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"foobar\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "z", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map35238map,
                                GeneratedMutableMap.map35369map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"foobar\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("z"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("z")
                        ), listOf(
                                GeneratedMutableMap.map35236map,
                                GeneratedMutableMap.map35370map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map35235map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","z",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35239map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map35235map,
                                GeneratedMutableMap.map35595map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map35236map,
                                GeneratedMutableMap.map35242map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "<http://www.example.org/schema#a>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "l", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35692map,
                                GeneratedMutableMap.map35693map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "<http://www.example.org/schema#a>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map35236map,
                                GeneratedMutableMap.map35694map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "l", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35692map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map35236map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map35235map,
                                GeneratedMutableMap.map35873map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#p>"), AOPVariable("y"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                        ), listOf(
                                GeneratedMutableMap.map35236map,
                                GeneratedMutableMap.map35874map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "l", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map35876map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("x"), AOPVariable.calculate("<http://www.example.org/schema#q>"), AOPVariable("l"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l")
                        ), listOf(
                                GeneratedMutableMap.map35242map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        }(),
                        POPValues(dictionary, listOf(
                                "#_37191",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37308map,
                                GeneratedMutableMap.map37309map,
                                GeneratedMutableMap.map37310map,
                                GeneratedMutableMap.map37311map,
                                GeneratedMutableMap.map37312map,
                                GeneratedMutableMap.map37313map,
                                GeneratedMutableMap.map37314map,
                                GeneratedMutableMap.map37315map,
                                GeneratedMutableMap.map37316map,
                                GeneratedMutableMap.map37317map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
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
                            LOPTriple(AOPVariable("#_37191"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#_37191"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37318map,
                                GeneratedMutableMap.map37319map,
                                GeneratedMutableMap.map37320map,
                                GeneratedMutableMap.map37321map,
                                GeneratedMutableMap.map37322map,
                                GeneratedMutableMap.map37323map,
                                GeneratedMutableMap.map37324map,
                                GeneratedMutableMap.map37325map,
                                GeneratedMutableMap.map37326map,
                                GeneratedMutableMap.map37327map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37191"
                        ), listOf(
                                GeneratedMutableMap.map37329map,
                                GeneratedMutableMap.map37330map,
                                GeneratedMutableMap.map37331map,
                                GeneratedMutableMap.map37332map,
                                GeneratedMutableMap.map37333map,
                                GeneratedMutableMap.map37334map,
                                GeneratedMutableMap.map37335map,
                                GeneratedMutableMap.map37336map,
                                GeneratedMutableMap.map37337map,
                                GeneratedMutableMap.map37338map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                        }(),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37191")
                        ), listOf(
                                GeneratedMutableMap.map37349map,
                                GeneratedMutableMap.map37350map,
                                GeneratedMutableMap.map37351map,
                                GeneratedMutableMap.map37352map,
                                GeneratedMutableMap.map37353map,
                                GeneratedMutableMap.map37354map,
                                GeneratedMutableMap.map37355map,
                                GeneratedMutableMap.map37356map,
                                GeneratedMutableMap.map37357map,
                                GeneratedMutableMap.map37358map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map37371map,
                                GeneratedMutableMap.map37372map,
                                GeneratedMutableMap.map37373map,
                                GeneratedMutableMap.map37374map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://www.example.orgOrder>"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"), AOPVariable.calculate("<http://www.example.orgOrder>"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("O")
                        ), listOf(
                                GeneratedMutableMap.map37375map,
                                GeneratedMutableMap.map37376map,
                                GeneratedMutableMap.map37377map,
                                GeneratedMutableMap.map37378map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37576map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"), AOPVariable("L"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37577map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "P",
                                "F"
                        ), listOf(
                                GeneratedMutableMap.map37579map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"), AOPVariable("F"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F")
                        ), listOf(
                                GeneratedMutableMap.map37580map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("_:_37597", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_37598", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_37599", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_37600", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_37601", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_37602", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_37603", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_37604", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_37605", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_37606", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_37620", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#_37620",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map37737map,
                                GeneratedMutableMap.map37738map,
                                GeneratedMutableMap.map37739map,
                                GeneratedMutableMap.map37740map,
                                GeneratedMutableMap.map37741map,
                                GeneratedMutableMap.map37742map,
                                GeneratedMutableMap.map37743map,
                                GeneratedMutableMap.map37744map,
                                GeneratedMutableMap.map37745map,
                                GeneratedMutableMap.map37746map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("_:_37597", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_37598", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_37599", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_37600", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_37601", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_37602", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_37603", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_37604", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_37605", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_37606", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#_37620"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("#_37620"),
                                AOPVariable("L")
                        ), listOf(
                                GeneratedMutableMap.map37747map,
                                GeneratedMutableMap.map37748map,
                                GeneratedMutableMap.map37749map,
                                GeneratedMutableMap.map37750map,
                                GeneratedMutableMap.map37751map,
                                GeneratedMutableMap.map37752map,
                                GeneratedMutableMap.map37753map,
                                GeneratedMutableMap.map37754map,
                                GeneratedMutableMap.map37755map,
                                GeneratedMutableMap.map37756map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37597"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37598"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37599"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37600"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37601"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37602"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37603"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37604"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37605"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37606"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_37620", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_37620"
                        ), listOf(
                                GeneratedMutableMap.map37758map,
                                GeneratedMutableMap.map37759map,
                                GeneratedMutableMap.map37760map,
                                GeneratedMutableMap.map37761map,
                                GeneratedMutableMap.map37762map,
                                GeneratedMutableMap.map37763map,
                                GeneratedMutableMap.map37764map,
                                GeneratedMutableMap.map37765map,
                                GeneratedMutableMap.map37766map,
                                GeneratedMutableMap.map37767map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        {
                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                            val graph = DistributedTripleStore.createGraph(graphName)
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37597"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37598"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_37599"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37600"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37601"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_37602"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37603"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_37604"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37605"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_37606"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_37620"), graphName, false)
                        }(),
                        LOPValues(listOf(
                                AOPVariable("O"),
                                AOPVariable("#_37620")
                        ), listOf(
                                GeneratedMutableMap.map37778map,
                                GeneratedMutableMap.map37779map,
                                GeneratedMutableMap.map37780map,
                                GeneratedMutableMap.map37781map,
                                GeneratedMutableMap.map37782map,
                                GeneratedMutableMap.map37783map,
                                GeneratedMutableMap.map37784map,
                                GeneratedMutableMap.map37785map,
                                GeneratedMutableMap.map37786map,
                                GeneratedMutableMap.map37787map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person"
                        ), listOf(
                            GeneratedMutableMap.map38064map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:ben@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/mbox>","mbox",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "mbox"
                        ), listOf(
                            GeneratedMutableMap.map38065map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://xmlns.com/foaf/0.1/homepage>","<http://example.org/alan>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/homepage>","homepage",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "homepage"
                        ), listOf(
                            GeneratedMutableMap.map38066map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/subquery/sq14.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/name>","name",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "name"
                        ), listOf(
                            GeneratedMutableMap.map38067map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/subquery/sq14.rq */
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
