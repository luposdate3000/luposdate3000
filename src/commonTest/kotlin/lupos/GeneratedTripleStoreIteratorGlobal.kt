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
                                GeneratedMutableMap.map357map,
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map357map,
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map
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
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map357map,
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map357map,
                                GeneratedMutableMap.map358map,
                                GeneratedMutableMap.map359map
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
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map
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
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map422map,
                                GeneratedMutableMap.map423map,
                                GeneratedMutableMap.map424map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map362map,
                                GeneratedMutableMap.map422map,
                                GeneratedMutableMap.map423map,
                                GeneratedMutableMap.map424map
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
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map489map,
                                GeneratedMutableMap.map362map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map360map,
                                GeneratedMutableMap.map361map,
                                GeneratedMutableMap.map489map,
                                GeneratedMutableMap.map362map
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
                                GeneratedMutableMap.map968map,
                                GeneratedMutableMap.map969map,
                                GeneratedMutableMap.map970map,
                                GeneratedMutableMap.map971map,
                                GeneratedMutableMap.map972map
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
                        POPValues(dictionary, listOf(
                                "S",
                                "P",
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map968map,
                                GeneratedMutableMap.map969map,
                                GeneratedMutableMap.map970map,
                                GeneratedMutableMap.map971map,
                                GeneratedMutableMap.map972map
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
                                GeneratedMutableMap.map2734map,
                                GeneratedMutableMap.map2735map,
                                GeneratedMutableMap.map2736map
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
                        POPValues(dictionary, listOf(
                                "S",
                                "O2"
                        ), listOf(
                                GeneratedMutableMap.map2734map,
                                GeneratedMutableMap.map2735map,
                                GeneratedMutableMap.map2736map
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
                                GeneratedMutableMap.map2737map,
                                GeneratedMutableMap.map2738map,
                                GeneratedMutableMap.map2739map
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
                        POPValues(dictionary, listOf(
                                "S",
                                "O1"
                        ), listOf(
                                GeneratedMutableMap.map2737map,
                                GeneratedMutableMap.map2738map,
                                GeneratedMutableMap.map2739map
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
                                GeneratedMutableMap.map3254map,
                                GeneratedMutableMap.map3255map,
                                GeneratedMutableMap.map3256map,
                                GeneratedMutableMap.map3257map,
                                GeneratedMutableMap.map3258map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3254map,
                                GeneratedMutableMap.map3255map,
                                GeneratedMutableMap.map3256map,
                                GeneratedMutableMap.map3257map,
                                GeneratedMutableMap.map3258map
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
                                GeneratedMutableMap.map3419map,
                                GeneratedMutableMap.map3420map,
                                GeneratedMutableMap.map3421map,
                                GeneratedMutableMap.map3422map,
                                GeneratedMutableMap.map3423map,
                                GeneratedMutableMap.map3424map,
                                GeneratedMutableMap.map3425map,
                                GeneratedMutableMap.map3426map,
                                GeneratedMutableMap.map3427map,
                                GeneratedMutableMap.map3428map,
                                GeneratedMutableMap.map3429map,
                                GeneratedMutableMap.map3430map,
                                GeneratedMutableMap.map3431map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3419map,
                                GeneratedMutableMap.map3420map,
                                GeneratedMutableMap.map3421map,
                                GeneratedMutableMap.map3422map,
                                GeneratedMutableMap.map3423map,
                                GeneratedMutableMap.map3424map,
                                GeneratedMutableMap.map3425map,
                                GeneratedMutableMap.map3426map,
                                GeneratedMutableMap.map3427map,
                                GeneratedMutableMap.map3428map,
                                GeneratedMutableMap.map3429map,
                                GeneratedMutableMap.map3430map,
                                GeneratedMutableMap.map3431map
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
                                GeneratedMutableMap.map3419map,
                                GeneratedMutableMap.map3420map,
                                GeneratedMutableMap.map3421map,
                                GeneratedMutableMap.map3422map,
                                GeneratedMutableMap.map3423map,
                                GeneratedMutableMap.map3424map,
                                GeneratedMutableMap.map3425map,
                                GeneratedMutableMap.map3426map,
                                GeneratedMutableMap.map3427map,
                                GeneratedMutableMap.map3428map,
                                GeneratedMutableMap.map3429map,
                                GeneratedMutableMap.map4163map,
                                GeneratedMutableMap.map3431map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map3419map,
                                GeneratedMutableMap.map3420map,
                                GeneratedMutableMap.map3421map,
                                GeneratedMutableMap.map3422map,
                                GeneratedMutableMap.map3423map,
                                GeneratedMutableMap.map3424map,
                                GeneratedMutableMap.map3425map,
                                GeneratedMutableMap.map3426map,
                                GeneratedMutableMap.map3427map,
                                GeneratedMutableMap.map3428map,
                                GeneratedMutableMap.map3429map,
                                GeneratedMutableMap.map4163map,
                                GeneratedMutableMap.map3431map
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
                            GeneratedMutableMap.map3254map,
                            GeneratedMutableMap.map3255map,
                            GeneratedMutableMap.map3256map,
                            GeneratedMutableMap.map3257map,
                            GeneratedMutableMap.map3258map
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
                            GeneratedMutableMap.map4908map,
                            GeneratedMutableMap.map4909map,
                            GeneratedMutableMap.map4910map,
                            GeneratedMutableMap.map4911map,
                            GeneratedMutableMap.map4912map,
                            GeneratedMutableMap.map4913map,
                            GeneratedMutableMap.map4914map,
                            GeneratedMutableMap.map4915map,
                            GeneratedMutableMap.map4916map,
                            GeneratedMutableMap.map4917map,
                            GeneratedMutableMap.map4918map,
                            GeneratedMutableMap.map4919map
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
                                GeneratedMutableMap.map5268map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5268map
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
                                GeneratedMutableMap.map5329map,
                                GeneratedMutableMap.map5268map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5329map,
                                GeneratedMutableMap.map5268map
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
                                GeneratedMutableMap.map5406map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5406map
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
                                GeneratedMutableMap.map5406map,
                                GeneratedMutableMap.map5407map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5406map,
                                GeneratedMutableMap.map5407map
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
                                GeneratedMutableMap.map5407map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5407map
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
                                GeneratedMutableMap.map5539map,
                                GeneratedMutableMap.map5407map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5539map,
                                GeneratedMutableMap.map5407map
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
                                GeneratedMutableMap.map5613map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5613map
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
                                GeneratedMutableMap.map5614map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5614map
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
                                GeneratedMutableMap.map5539map,
                                GeneratedMutableMap.map5615map,
                                GeneratedMutableMap.map5616map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map5539map,
                                GeneratedMutableMap.map5615map,
                                GeneratedMutableMap.map5616map
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
                            GeneratedMutableMap.map5827map
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
                            GeneratedMutableMap.map6262map
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
                            GeneratedMutableMap.map6262map
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
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map
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
                            GeneratedMutableMap.map6649map,
                            GeneratedMutableMap.map6650map,
                            GeneratedMutableMap.map6651map,
                            GeneratedMutableMap.map6652map
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
                            GeneratedMutableMap.map7104map,
                            GeneratedMutableMap.map7105map,
                            GeneratedMutableMap.map7106map,
                            GeneratedMutableMap.map7107map
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
                                GeneratedMutableMap.map8044map,
                                GeneratedMutableMap.map8045map,
                                GeneratedMutableMap.map8046map,
                                GeneratedMutableMap.map8047map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map8044map,
                                GeneratedMutableMap.map8045map,
                                GeneratedMutableMap.map8046map,
                                GeneratedMutableMap.map8047map
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
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://example.org/ns#price>", "\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://example.org/ns#price>", "\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://example.org/ns#price>", "price", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8390map,
                                GeneratedMutableMap.map8391map
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
                        POPValues(dictionary, listOf(
                                "book",
                                "price"
                        ), listOf(
                                GeneratedMutableMap.map8390map,
                                GeneratedMutableMap.map8391map
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
                            graph.addData(1L, listOf("<http://example.org/book/book1>", "<http://purl.org/dc/elements/1.1/title>", "\"SPARQL Tutorial\""))
                            graph.addData(1L, listOf("<http://example.org/book/book2>", "<http://purl.org/dc/elements/1.1/title>", "\"The Semantic Web\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "book", "<http://purl.org/dc/elements/1.1/title>", "title", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8392map,
                                GeneratedMutableMap.map8393map
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
                        POPValues(dictionary, listOf(
                                "book",
                                "title"
                        ), listOf(
                                GeneratedMutableMap.map8392map,
                                GeneratedMutableMap.map8393map
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
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                GeneratedMutableMap.map8553map,
                                GeneratedMutableMap.map8554map,
                                GeneratedMutableMap.map8555map,
                                GeneratedMutableMap.map8556map,
                                GeneratedMutableMap.map8557map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8553map,
                                GeneratedMutableMap.map8554map,
                                GeneratedMutableMap.map8555map,
                                GeneratedMutableMap.map8556map,
                                GeneratedMutableMap.map8557map
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
                                GeneratedMutableMap.map8558map,
                                GeneratedMutableMap.map8559map,
                                GeneratedMutableMap.map8560map,
                                GeneratedMutableMap.map8561map,
                                GeneratedMutableMap.map8562map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8558map,
                                GeneratedMutableMap.map8559map,
                                GeneratedMutableMap.map8560map,
                                GeneratedMutableMap.map8561map,
                                GeneratedMutableMap.map8562map
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
                                GeneratedMutableMap.map8896map,
                                GeneratedMutableMap.map8897map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map8896map,
                                GeneratedMutableMap.map8897map
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
                                GeneratedMutableMap.map8553map,
                                GeneratedMutableMap.map8554map,
                                GeneratedMutableMap.map8555map,
                                GeneratedMutableMap.map8898map,
                                GeneratedMutableMap.map8556map,
                                GeneratedMutableMap.map8557map,
                                GeneratedMutableMap.map8899map,
                                GeneratedMutableMap.map8900map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p1",
                                "o1"
                        ), listOf(
                                GeneratedMutableMap.map8553map,
                                GeneratedMutableMap.map8554map,
                                GeneratedMutableMap.map8555map,
                                GeneratedMutableMap.map8898map,
                                GeneratedMutableMap.map8556map,
                                GeneratedMutableMap.map8557map,
                                GeneratedMutableMap.map8899map,
                                GeneratedMutableMap.map8900map
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
                                GeneratedMutableMap.map9237map,
                                GeneratedMutableMap.map9238map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9237map,
                                GeneratedMutableMap.map9238map
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
                                GeneratedMutableMap.map9239map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9239map
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
                                GeneratedMutableMap.map9269map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9269map
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
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9437map,
                                GeneratedMutableMap.map9438map,
                                GeneratedMutableMap.map9439map,
                                GeneratedMutableMap.map9440map
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
                            GeneratedMutableMap.map9562map
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
                            GeneratedMutableMap.map9563map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9676map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s2>","<http://example.org/p>","o1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "o1"
                        ), listOf(
                            GeneratedMutableMap.map9677map
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
                                GeneratedMutableMap.map9835map,
                                GeneratedMutableMap.map9836map,
                                GeneratedMutableMap.map9837map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9835map,
                                GeneratedMutableMap.map9836map,
                                GeneratedMutableMap.map9837map
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
                                GeneratedMutableMap.map9897map,
                                GeneratedMutableMap.map9898map,
                                GeneratedMutableMap.map9899map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map9897map,
                                GeneratedMutableMap.map9898map,
                                GeneratedMutableMap.map9899map
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
                            GeneratedMutableMap.map10096map,
                            GeneratedMutableMap.map10097map,
                            GeneratedMutableMap.map10098map,
                            GeneratedMutableMap.map10099map,
                            GeneratedMutableMap.map10100map,
                            GeneratedMutableMap.map10101map
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
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map10098map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map10100map,
                                GeneratedMutableMap.map10101map
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
                            GeneratedMutableMap.map10274map,
                            GeneratedMutableMap.map10275map,
                            GeneratedMutableMap.map10276map,
                            GeneratedMutableMap.map10277map,
                            GeneratedMutableMap.map10278map,
                            GeneratedMutableMap.map10279map
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
                                GeneratedMutableMap.map10274map,
                                GeneratedMutableMap.map10275map,
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map10278map,
                                GeneratedMutableMap.map10279map
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
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10274map,
                                GeneratedMutableMap.map10275map,
                                GeneratedMutableMap.map10276map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map10278map,
                                GeneratedMutableMap.map10279map
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
                            GeneratedMutableMap.map10469map,
                            GeneratedMutableMap.map10470map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map
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
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10469map,
                                GeneratedMutableMap.map10470map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map
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
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
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
                                GeneratedMutableMap.map10796map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map,
                                GeneratedMutableMap.map10797map,
                                GeneratedMutableMap.map10798map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10796map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map,
                                GeneratedMutableMap.map10797map,
                                GeneratedMutableMap.map10798map
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
                            GeneratedMutableMap.map8458map,
                            GeneratedMutableMap.map8459map,
                            GeneratedMutableMap.map8460map,
                            GeneratedMutableMap.map8461map,
                            GeneratedMutableMap.map8462map
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
                            GeneratedMutableMap.map8458map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map8461map,
                            GeneratedMutableMap.map8462map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10795map
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
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map,
                                GeneratedMutableMap.map11038map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "b"
                        ), listOf(
                                GeneratedMutableMap.map11036map,
                                GeneratedMutableMap.map11037map,
                                GeneratedMutableMap.map11038map
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
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map11040map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11042map,
                                GeneratedMutableMap.map11043map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map11040map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11042map,
                                GeneratedMutableMap.map11043map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                            GeneratedMutableMap.map11036map,
                            GeneratedMutableMap.map11037map,
                            GeneratedMutableMap.map11038map
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
                            GeneratedMutableMap.map11039map,
                            GeneratedMutableMap.map8460map,
                            GeneratedMutableMap.map11040map,
                            GeneratedMutableMap.map11041map,
                            GeneratedMutableMap.map8462map,
                            GeneratedMutableMap.map11042map,
                            GeneratedMutableMap.map11043map,
                            GeneratedMutableMap.map11044map,
                            GeneratedMutableMap.map11045map
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
                                GeneratedMutableMap.map11299map
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
                        POPValues(dictionary, listOf(
                                "a"
                        ), listOf(
                                GeneratedMutableMap.map11299map
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
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map,
                                GeneratedMutableMap.map11534map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11532map,
                                GeneratedMutableMap.map11533map,
                                GeneratedMutableMap.map11534map
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
                                GeneratedMutableMap.map11665map,
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map11665map,
                                GeneratedMutableMap.map11039map,
                                GeneratedMutableMap.map8460map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map11041map,
                                GeneratedMutableMap.map8462map,
                                GeneratedMutableMap.map11044map,
                                GeneratedMutableMap.map11045map
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
                                GeneratedMutableMap.map11534map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "Var_B"
                        ), listOf(
                                GeneratedMutableMap.map11534map
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
                                GeneratedMutableMap.map12681map
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
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map12681map
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
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map8459map,
                                GeneratedMutableMap.map8460map
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
                                GeneratedMutableMap.map13081map
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
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map13081map
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
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8458map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                            GeneratedMutableMap.map12681map
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
                            GeneratedMutableMap.map8458map,
                            GeneratedMutableMap.map8459map,
                            GeneratedMutableMap.map8460map
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
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/d>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map13081map
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
                            GeneratedMutableMap.map10796map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10795map,
                            GeneratedMutableMap.map10797map,
                            GeneratedMutableMap.map10798map
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
                            GeneratedMutableMap.map10797map,
                            GeneratedMutableMap.map10798map
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
                                GeneratedMutableMap.map13831map
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
                        POPValues(dictionary, listOf(
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map13831map
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
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map8461map,
                                GeneratedMutableMap.map8462map
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
                            GeneratedMutableMap.map13081map
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
                            GeneratedMutableMap.map8458map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map8461map,
                            GeneratedMutableMap.map8462map
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
                                GeneratedMutableMap.map15362map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15362map
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
                                GeneratedMutableMap.map15400map,
                                GeneratedMutableMap.map15401map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                        ), listOf(
                                GeneratedMutableMap.map15400map,
                                GeneratedMutableMap.map15401map
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
                                GeneratedMutableMap.map15443map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15443map
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
                            GeneratedMutableMap.map15481map
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
                            GeneratedMutableMap.map15658map
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
                            GeneratedMutableMap.map15659map,
                            GeneratedMutableMap.map15660map,
                            GeneratedMutableMap.map15661map,
                            GeneratedMutableMap.map15662map,
                            GeneratedMutableMap.map15663map,
                            GeneratedMutableMap.map15664map,
                            GeneratedMutableMap.map15665map
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
                                GeneratedMutableMap.map15786map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map15786map
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
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/2000/01/rdf-schema#subPropertyOf>","<http://example.org/ns#p>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15926map
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
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://example.org/ns#b>","<http://example.org/ns#c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#a>","x","<http://example.org/ns#c>",true,false,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15926map
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
                                GeneratedMutableMap.map15970map
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
                        POPValues(dictionary, listOf(
                                "f"
                        ), listOf(
                                GeneratedMutableMap.map15970map
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
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16120map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16122map,
                                GeneratedMutableMap.map15659map,
                                GeneratedMutableMap.map15660map,
                                GeneratedMutableMap.map15661map,
                                GeneratedMutableMap.map16123map,
                                GeneratedMutableMap.map16124map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                        ), listOf(
                                GeneratedMutableMap.map16119map,
                                GeneratedMutableMap.map16120map,
                                GeneratedMutableMap.map16121map,
                                GeneratedMutableMap.map16122map,
                                GeneratedMutableMap.map15659map,
                                GeneratedMutableMap.map15660map,
                                GeneratedMutableMap.map15661map,
                                GeneratedMutableMap.map16123map,
                                GeneratedMutableMap.map16124map
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
                                GeneratedMutableMap.map16184map,
                                GeneratedMutableMap.map16185map
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
                        POPValues(dictionary, listOf(
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16184map,
                                GeneratedMutableMap.map16185map
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
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map16186map,
                                GeneratedMutableMap.map16187map
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
                            GeneratedMutableMap.map16229map
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
                        graph.addData(1L,listOf("_:_16271","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#c"
                        ), listOf(
                            GeneratedMutableMap.map16397map,
                            GeneratedMutableMap.map16398map,
                            GeneratedMutableMap.map16399map,
                            GeneratedMutableMap.map16400map,
                            GeneratedMutableMap.map16401map,
                            GeneratedMutableMap.map16402map,
                            GeneratedMutableMap.map16403map,
                            GeneratedMutableMap.map16404map,
                            GeneratedMutableMap.map16405map,
                            GeneratedMutableMap.map16406map,
                            GeneratedMutableMap.map16407map,
                            GeneratedMutableMap.map16408map,
                            GeneratedMutableMap.map16409map,
                            GeneratedMutableMap.map16410map,
                            GeneratedMutableMap.map16411map,
                            GeneratedMutableMap.map16412map,
                            GeneratedMutableMap.map16413map,
                            GeneratedMutableMap.map16414map,
                            GeneratedMutableMap.map16415map,
                            GeneratedMutableMap.map16416map
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
                            GeneratedMutableMap.map16417map
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
                        graph.addData(1L,listOf("_:_16418","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16438","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16438"
                        ), listOf(
                            GeneratedMutableMap.map16546map
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
                        graph.addData(1L,listOf("_:_16418","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://example.org/Conference>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16438","<http://www.w3.org/2002/07/owl#someValuesFrom>","#_16447",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16438",
                            "#_16447"
                        ), listOf(
                            GeneratedMutableMap.map16547map
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
                        graph.addData(1L,listOf("<http://example.org/Conference>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/ConferencePaper>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Employee>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/GraduateAssistant>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Student>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/Workshop>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16447","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16447"
                        ), listOf(
                            GeneratedMutableMap.map16548map,
                            GeneratedMutableMap.map16549map,
                            GeneratedMutableMap.map16550map,
                            GeneratedMutableMap.map16551map,
                            GeneratedMutableMap.map16552map,
                            GeneratedMutableMap.map16553map
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
                        graph.addData(1L,listOf("_:_16418","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16438","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16438"
                        ), listOf(
                            GeneratedMutableMap.map16546map
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
                        graph.addData(1L,listOf("_:_16418","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b0","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_16438",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b0",
                            "#_16438"
                        ), listOf(
                            GeneratedMutableMap.map16554map,
                            GeneratedMutableMap.map16555map,
                            GeneratedMutableMap.map16556map,
                            GeneratedMutableMap.map16557map,
                            GeneratedMutableMap.map16558map,
                            GeneratedMutableMap.map16559map,
                            GeneratedMutableMap.map16560map,
                            GeneratedMutableMap.map16561map,
                            GeneratedMutableMap.map16562map,
                            GeneratedMutableMap.map16563map,
                            GeneratedMutableMap.map16564map,
                            GeneratedMutableMap.map16565map,
                            GeneratedMutableMap.map16566map,
                            GeneratedMutableMap.map16567map,
                            GeneratedMutableMap.map16568map,
                            GeneratedMutableMap.map16569map,
                            GeneratedMutableMap.map16570map,
                            GeneratedMutableMap.map16571map,
                            GeneratedMutableMap.map16572map,
                            GeneratedMutableMap.map16573map
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
                            GeneratedMutableMap.map16574map,
                            GeneratedMutableMap.map16575map
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
                            GeneratedMutableMap.map16664map
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
                            GeneratedMutableMap.map16229map
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
                        graph.addData(1L,listOf("_:_16590","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16665map,
                            GeneratedMutableMap.map16666map,
                            GeneratedMutableMap.map16667map,
                            GeneratedMutableMap.map16668map,
                            GeneratedMutableMap.map16669map,
                            GeneratedMutableMap.map16670map,
                            GeneratedMutableMap.map16671map,
                            GeneratedMutableMap.map16672map,
                            GeneratedMutableMap.map16673map,
                            GeneratedMutableMap.map16674map,
                            GeneratedMutableMap.map16675map,
                            GeneratedMutableMap.map16676map,
                            GeneratedMutableMap.map16677map,
                            GeneratedMutableMap.map16678map,
                            GeneratedMutableMap.map16679map,
                            GeneratedMutableMap.map16680map,
                            GeneratedMutableMap.map16681map,
                            GeneratedMutableMap.map16682map,
                            GeneratedMutableMap.map16683map,
                            GeneratedMutableMap.map16684map
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
                                GeneratedMutableMap.map16748map,
                                GeneratedMutableMap.map16749map
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
                        POPValues(dictionary, listOf(
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map16748map,
                                GeneratedMutableMap.map16749map
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
                                GeneratedMutableMap.map16750map,
                                GeneratedMutableMap.map16751map,
                                GeneratedMutableMap.map16752map,
                                GeneratedMutableMap.map16753map
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
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map16750map,
                                GeneratedMutableMap.map16751map,
                                GeneratedMutableMap.map16752map,
                                GeneratedMutableMap.map16753map
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
                                GeneratedMutableMap.map16794map
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
                        POPValues(dictionary, listOf(
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map16794map
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
                                GeneratedMutableMap.map16898map
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
                        POPValues(dictionary, listOf(
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map16898map
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
                                GeneratedMutableMap.map16899map,
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map,
                                GeneratedMutableMap.map16900map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map16899map,
                                GeneratedMutableMap.map6649map,
                                GeneratedMutableMap.map6650map,
                                GeneratedMutableMap.map6651map,
                                GeneratedMutableMap.map6652map,
                                GeneratedMutableMap.map16900map
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
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p1","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p1"
                        ), listOf(
                            GeneratedMutableMap.map17387map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s1","p1","z",false,false,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s1",
                            "p1",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map17388map,
                            GeneratedMutableMap.map7104map,
                            GeneratedMutableMap.map7105map,
                            GeneratedMutableMap.map7106map,
                            GeneratedMutableMap.map7107map,
                            GeneratedMutableMap.map17389map
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
                            GeneratedMutableMap.map16898map
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
                            GeneratedMutableMap.map16899map,
                            GeneratedMutableMap.map6649map,
                            GeneratedMutableMap.map6650map,
                            GeneratedMutableMap.map6651map,
                            GeneratedMutableMap.map6652map,
                            GeneratedMutableMap.map16900map
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
                            GeneratedMutableMap.map15659map,
                            GeneratedMutableMap.map15661map,
                            GeneratedMutableMap.map15662map,
                            GeneratedMutableMap.map16123map
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
                            GeneratedMutableMap.map18370map
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
                            GeneratedMutableMap.map16119map,
                            GeneratedMutableMap.map16121map,
                            GeneratedMutableMap.map15659map,
                            GeneratedMutableMap.map15660map,
                            GeneratedMutableMap.map15661map,
                            GeneratedMutableMap.map15662map,
                            GeneratedMutableMap.map18371map,
                            GeneratedMutableMap.map15663map,
                            GeneratedMutableMap.map18372map,
                            GeneratedMutableMap.map18373map,
                            GeneratedMutableMap.map16124map
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
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            graph.addData(1L, listOf("<http://example.org/test#c>", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>", "<http://example.org/test#Person>", false, true, true, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X"
                        ), listOf(
                                GeneratedMutableMap.map18446map,
                                GeneratedMutableMap.map18447map,
                                GeneratedMutableMap.map18448map
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
                        POPValues(dictionary, listOf(
                                "X"
                        ), listOf(
                                GeneratedMutableMap.map18446map,
                                GeneratedMutableMap.map18447map,
                                GeneratedMutableMap.map18448map
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
                            graph.addData(1L, listOf("<http://example.org/test#a>", "<http://example.org/test#name>", "\"A\""))
                            graph.addData(1L, listOf("<http://example.org/test#b>", "<http://example.org/test#name>", "\"B\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "X", "<http://example.org/test#name>", "Y1", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1"
                        ), listOf(
                                GeneratedMutableMap.map18449map,
                                GeneratedMutableMap.map18450map
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
                                GeneratedMutableMap.map18451map,
                                GeneratedMutableMap.map18452map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y2"
                        ), listOf(
                                GeneratedMutableMap.map18451map,
                                GeneratedMutableMap.map18452map
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
                            GeneratedMutableMap.map18497map,
                            GeneratedMutableMap.map18498map,
                            GeneratedMutableMap.map18499map
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
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/test#a>","<http://example.org/test#p>","#aa",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#aa"
                        ), listOf(
                            GeneratedMutableMap.map18584map
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
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#aa","<http://example.org/test#r>","#dd",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#aa",
                            "#dd"
                        ), listOf(
                            GeneratedMutableMap.map18585map,
                            GeneratedMutableMap.map18586map
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
                            GeneratedMutableMap.map18587map
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
                            graph.addData(1L, listOf("<http://example.org/test#bb>", "<http://example.org/test#s>", "<http://example.org/test#aa>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "Y", "<http://example.org/test#s>", "#aa", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                        ), listOf(
                                GeneratedMutableMap.map18671map
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
                        POPValues(dictionary, listOf(
                                "Y",
                                "#aa"
                        ), listOf(
                                GeneratedMutableMap.map18671map
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
                            graph.addData(1L, listOf("<http://example.org/test#aa>", "<http://example.org/test#r>", "<http://example.org/test#ee>"))
                            graph.addData(1L, listOf("<http://example.org/test#cc>", "<http://example.org/test#r>", "<http://example.org/test#dd>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#aa", "<http://example.org/test#r>", "Z", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18672map,
                                GeneratedMutableMap.map18673map
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
                        POPValues(dictionary, listOf(
                                "#aa",
                                "Z"
                        ), listOf(
                                GeneratedMutableMap.map18672map,
                                GeneratedMutableMap.map18673map
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
                                GeneratedMutableMap.map18584map
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
                        POPValues(dictionary, listOf(
                                "#aa"
                        ), listOf(
                                GeneratedMutableMap.map18584map
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
                                GeneratedMutableMap.map18674map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18674map
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
                                GeneratedMutableMap.map18732map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                        ), listOf(
                                GeneratedMutableMap.map18732map
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
                                GeneratedMutableMap.map18733map,
                                GeneratedMutableMap.map18734map
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
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18733map,
                                GeneratedMutableMap.map18734map
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
                                GeneratedMutableMap.map18794map
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
                        POPValues(dictionary, listOf(
                                "X",
                                "#a"
                        ), listOf(
                                GeneratedMutableMap.map18794map
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
                                GeneratedMutableMap.map18795map,
                                GeneratedMutableMap.map18796map,
                                GeneratedMutableMap.map18797map,
                                GeneratedMutableMap.map18798map
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
                        POPValues(dictionary, listOf(
                                "#a",
                                "Y"
                        ), listOf(
                                GeneratedMutableMap.map18795map,
                                GeneratedMutableMap.map18796map,
                                GeneratedMutableMap.map18797map,
                                GeneratedMutableMap.map18798map
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
                            GeneratedMutableMap.map18892map
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
                            GeneratedMutableMap.map18893map,
                            GeneratedMutableMap.map18894map,
                            GeneratedMutableMap.map18895map,
                            GeneratedMutableMap.map18896map
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
                            GeneratedMutableMap.map18985map
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
                            GeneratedMutableMap.map19061map
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
                            GeneratedMutableMap.map19062map
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
                            GeneratedMutableMap.map19063map,
                            GeneratedMutableMap.map19064map,
                            GeneratedMutableMap.map19065map
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
                                GeneratedMutableMap.map19154map,
                                GeneratedMutableMap.map19155map
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
                        POPValues(dictionary, listOf(
                                "parent",
                                "child"
                        ), listOf(
                                GeneratedMutableMap.map19154map,
                                GeneratedMutableMap.map19155map
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
                        graph.addData(1L,listOf("_:_19164","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19165","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19179","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19245map,
                            GeneratedMutableMap.map19246map
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
                        graph.addData(1L,listOf("_:_19164","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19165","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19179","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19245map,
                            GeneratedMutableMap.map19246map
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
                        graph.addData(1L,listOf("_:_19164","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19179","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19245map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19165"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19158","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19161","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19164","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19165","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19166","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19179",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19179"
                        ), listOf(
                            GeneratedMutableMap.map19247map,
                            GeneratedMutableMap.map19248map,
                            GeneratedMutableMap.map19249map,
                            GeneratedMutableMap.map19250map,
                            GeneratedMutableMap.map19251map,
                            GeneratedMutableMap.map19252map,
                            GeneratedMutableMap.map19253map,
                            GeneratedMutableMap.map19254map,
                            GeneratedMutableMap.map19255map,
                            GeneratedMutableMap.map19256map,
                            GeneratedMutableMap.map19257map,
                            GeneratedMutableMap.map19258map,
                            GeneratedMutableMap.map19259map,
                            GeneratedMutableMap.map19260map,
                            GeneratedMutableMap.map19261map,
                            GeneratedMutableMap.map19262map,
                            GeneratedMutableMap.map19263map,
                            GeneratedMutableMap.map19264map,
                            GeneratedMutableMap.map19265map,
                            GeneratedMutableMap.map19266map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19274"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19267","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19270","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19273","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19274","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19275","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19291",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19291"
                        ), listOf(
                            GeneratedMutableMap.map19362map,
                            GeneratedMutableMap.map19363map,
                            GeneratedMutableMap.map19364map,
                            GeneratedMutableMap.map19365map,
                            GeneratedMutableMap.map19366map,
                            GeneratedMutableMap.map19367map,
                            GeneratedMutableMap.map19368map,
                            GeneratedMutableMap.map19369map,
                            GeneratedMutableMap.map19370map,
                            GeneratedMutableMap.map19371map,
                            GeneratedMutableMap.map19372map,
                            GeneratedMutableMap.map19373map,
                            GeneratedMutableMap.map19374map,
                            GeneratedMutableMap.map19375map,
                            GeneratedMutableMap.map19376map,
                            GeneratedMutableMap.map19377map,
                            GeneratedMutableMap.map19378map,
                            GeneratedMutableMap.map19379map,
                            GeneratedMutableMap.map19380map,
                            GeneratedMutableMap.map19381map
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
                        graph.addData(1L,listOf("_:_19273","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19274","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19291","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19291"
                        ), listOf(
                            GeneratedMutableMap.map19382map,
                            GeneratedMutableMap.map19383map
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
                        graph.addData(1L,listOf("_:_19273","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19274","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19291","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19291"
                        ), listOf(
                            GeneratedMutableMap.map19382map,
                            GeneratedMutableMap.map19383map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19391"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19384","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19387","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19390","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19391","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19392","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19405",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19405"
                        ), listOf(
                            GeneratedMutableMap.map19471map,
                            GeneratedMutableMap.map19472map,
                            GeneratedMutableMap.map19473map,
                            GeneratedMutableMap.map19474map,
                            GeneratedMutableMap.map19475map,
                            GeneratedMutableMap.map19476map,
                            GeneratedMutableMap.map19477map,
                            GeneratedMutableMap.map19478map,
                            GeneratedMutableMap.map19479map,
                            GeneratedMutableMap.map19480map,
                            GeneratedMutableMap.map19481map,
                            GeneratedMutableMap.map19482map,
                            GeneratedMutableMap.map19483map,
                            GeneratedMutableMap.map19484map,
                            GeneratedMutableMap.map19485map,
                            GeneratedMutableMap.map19486map,
                            GeneratedMutableMap.map19487map,
                            GeneratedMutableMap.map19488map,
                            GeneratedMutableMap.map19489map,
                            GeneratedMutableMap.map19490map
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
                        graph.addData(1L,listOf("_:_19390","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19391","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19405","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19405"
                        ), listOf(
                            GeneratedMutableMap.map19491map,
                            GeneratedMutableMap.map19492map
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
                        graph.addData(1L,listOf("_:_19390","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19391","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19405","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19405"
                        ), listOf(
                            GeneratedMutableMap.map19491map,
                            GeneratedMutableMap.map19492map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19500"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19493","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19496","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19499","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19500","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19501","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19515",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19515"
                        ), listOf(
                            GeneratedMutableMap.map19597map,
                            GeneratedMutableMap.map19598map,
                            GeneratedMutableMap.map19599map,
                            GeneratedMutableMap.map19600map,
                            GeneratedMutableMap.map19601map,
                            GeneratedMutableMap.map19602map,
                            GeneratedMutableMap.map19603map,
                            GeneratedMutableMap.map19604map,
                            GeneratedMutableMap.map19605map,
                            GeneratedMutableMap.map19606map,
                            GeneratedMutableMap.map19607map,
                            GeneratedMutableMap.map19608map,
                            GeneratedMutableMap.map19609map,
                            GeneratedMutableMap.map19610map,
                            GeneratedMutableMap.map19611map,
                            GeneratedMutableMap.map19612map,
                            GeneratedMutableMap.map19613map,
                            GeneratedMutableMap.map19614map,
                            GeneratedMutableMap.map19615map,
                            GeneratedMutableMap.map19616map
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
                        graph.addData(1L,listOf("_:_19499","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19500","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19515","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19515"
                        ), listOf(
                            GeneratedMutableMap.map19617map,
                            GeneratedMutableMap.map19618map
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
                        graph.addData(1L,listOf("_:_19499","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19500","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19515","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19515"
                        ), listOf(
                            GeneratedMutableMap.map19617map,
                            GeneratedMutableMap.map19618map
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
                        graph.addData(1L,listOf("_:_19625","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19626","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19641","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19641"
                        ), listOf(
                            GeneratedMutableMap.map19723map,
                            GeneratedMutableMap.map19724map
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
                        graph.addData(1L,listOf("_:_19625","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19626","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19641","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19641"
                        ), listOf(
                            GeneratedMutableMap.map19723map,
                            GeneratedMutableMap.map19724map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19626"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19619","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19622","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19625","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19626","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19627","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19641",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19641"
                        ), listOf(
                            GeneratedMutableMap.map19725map,
                            GeneratedMutableMap.map19726map,
                            GeneratedMutableMap.map19727map,
                            GeneratedMutableMap.map19728map,
                            GeneratedMutableMap.map19729map,
                            GeneratedMutableMap.map19730map,
                            GeneratedMutableMap.map19731map,
                            GeneratedMutableMap.map19732map,
                            GeneratedMutableMap.map19733map,
                            GeneratedMutableMap.map19734map,
                            GeneratedMutableMap.map19735map,
                            GeneratedMutableMap.map19736map,
                            GeneratedMutableMap.map19737map,
                            GeneratedMutableMap.map19738map,
                            GeneratedMutableMap.map19739map,
                            GeneratedMutableMap.map19740map,
                            GeneratedMutableMap.map19741map,
                            GeneratedMutableMap.map19742map,
                            GeneratedMutableMap.map19743map,
                            GeneratedMutableMap.map19744map
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
                        graph.addData(1L,listOf("_:_19751","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19752","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19767","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19767"
                        ), listOf(
                            GeneratedMutableMap.map19849map,
                            GeneratedMutableMap.map19850map
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19752"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19745","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19748","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19751","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19752","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19753","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19767",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "#_19767"
                        ), listOf(
                            GeneratedMutableMap.map19851map,
                            GeneratedMutableMap.map19852map,
                            GeneratedMutableMap.map19853map,
                            GeneratedMutableMap.map19854map,
                            GeneratedMutableMap.map19855map,
                            GeneratedMutableMap.map19856map,
                            GeneratedMutableMap.map19857map,
                            GeneratedMutableMap.map19858map,
                            GeneratedMutableMap.map19859map,
                            GeneratedMutableMap.map19860map,
                            GeneratedMutableMap.map19861map,
                            GeneratedMutableMap.map19862map,
                            GeneratedMutableMap.map19863map,
                            GeneratedMutableMap.map19864map,
                            GeneratedMutableMap.map19865map,
                            GeneratedMutableMap.map19866map,
                            GeneratedMutableMap.map19867map,
                            GeneratedMutableMap.map19868map,
                            GeneratedMutableMap.map19869map,
                            GeneratedMutableMap.map19870map
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
                        graph.addData(1L,listOf("_:_19751","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19752","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19767","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19767"
                        ), listOf(
                            GeneratedMutableMap.map19849map,
                            GeneratedMutableMap.map19850map
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
                        graph.addData(1L,listOf("_:_19877","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19894","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19894"
                        ), listOf(
                            GeneratedMutableMap.map19962map
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
                        graph.addData(1L,listOf("_:_19877","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19878","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19894","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19894"
                        ), listOf(
                            GeneratedMutableMap.map19962map,
                            GeneratedMutableMap.map19963map
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
                        graph.addData(1L,listOf("_:_19877","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19878","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19894","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_19894"
                        ), listOf(
                            GeneratedMutableMap.map19962map,
                            GeneratedMutableMap.map19963map
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
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19971","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20068map,
                            GeneratedMutableMap.map20069map
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
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19971","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20068map,
                            GeneratedMutableMap.map20069map
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
                        graph.addData(1L,listOf("_:_19970","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20068map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20083",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20083"
                        ), listOf(
                            GeneratedMutableMap.map20175map,
                            GeneratedMutableMap.map20176map,
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
                            GeneratedMutableMap.map20187map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20201",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20201"
                        ), listOf(
                            GeneratedMutableMap.map20332map,
                            GeneratedMutableMap.map20333map,
                            GeneratedMutableMap.map20334map,
                            GeneratedMutableMap.map20335map,
                            GeneratedMutableMap.map20336map,
                            GeneratedMutableMap.map20337map,
                            GeneratedMutableMap.map20338map,
                            GeneratedMutableMap.map20339map,
                            GeneratedMutableMap.map20340map,
                            GeneratedMutableMap.map20341map,
                            GeneratedMutableMap.map20342map,
                            GeneratedMutableMap.map20343map,
                            GeneratedMutableMap.map20344map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20358",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20358"
                        ), listOf(
                            GeneratedMutableMap.map20489map,
                            GeneratedMutableMap.map20490map,
                            GeneratedMutableMap.map20491map,
                            GeneratedMutableMap.map20492map,
                            GeneratedMutableMap.map20493map,
                            GeneratedMutableMap.map20494map,
                            GeneratedMutableMap.map20495map,
                            GeneratedMutableMap.map20496map,
                            GeneratedMutableMap.map20497map,
                            GeneratedMutableMap.map20498map,
                            GeneratedMutableMap.map20499map,
                            GeneratedMutableMap.map20500map,
                            GeneratedMutableMap.map20501map
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
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20515","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20515"
                        ), listOf(
                            GeneratedMutableMap.map20619map,
                            GeneratedMutableMap.map20620map,
                            GeneratedMutableMap.map20621map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20515",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20515"
                        ), listOf(
                            GeneratedMutableMap.map20622map,
                            GeneratedMutableMap.map20623map,
                            GeneratedMutableMap.map20624map,
                            GeneratedMutableMap.map20625map,
                            GeneratedMutableMap.map20626map,
                            GeneratedMutableMap.map20627map,
                            GeneratedMutableMap.map20628map,
                            GeneratedMutableMap.map20629map,
                            GeneratedMutableMap.map20630map,
                            GeneratedMutableMap.map20631map,
                            GeneratedMutableMap.map20632map,
                            GeneratedMutableMap.map20633map,
                            GeneratedMutableMap.map20634map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20656","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20656"
                        ), listOf(
                            GeneratedMutableMap.map20791map,
                            GeneratedMutableMap.map20792map,
                            GeneratedMutableMap.map20793map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20648",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20648"
                        ), listOf(
                            GeneratedMutableMap.map20794map,
                            GeneratedMutableMap.map20795map,
                            GeneratedMutableMap.map20796map,
                            GeneratedMutableMap.map20797map,
                            GeneratedMutableMap.map20798map,
                            GeneratedMutableMap.map20799map,
                            GeneratedMutableMap.map20800map,
                            GeneratedMutableMap.map20801map,
                            GeneratedMutableMap.map20802map,
                            GeneratedMutableMap.map20803map,
                            GeneratedMutableMap.map20804map,
                            GeneratedMutableMap.map20805map,
                            GeneratedMutableMap.map20806map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20828","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20828"
                        ), listOf(
                            GeneratedMutableMap.map20988map,
                            GeneratedMutableMap.map20989map,
                            GeneratedMutableMap.map20990map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20820",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_20820"
                        ), listOf(
                            GeneratedMutableMap.map20991map,
                            GeneratedMutableMap.map20992map,
                            GeneratedMutableMap.map20993map,
                            GeneratedMutableMap.map20994map,
                            GeneratedMutableMap.map20995map,
                            GeneratedMutableMap.map20996map,
                            GeneratedMutableMap.map20997map,
                            GeneratedMutableMap.map20998map,
                            GeneratedMutableMap.map20999map,
                            GeneratedMutableMap.map21000map,
                            GeneratedMutableMap.map21001map,
                            GeneratedMutableMap.map21002map,
                            GeneratedMutableMap.map21003map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21026","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21026"
                        ), listOf(
                            GeneratedMutableMap.map21196map,
                            GeneratedMutableMap.map21197map,
                            GeneratedMutableMap.map21198map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21017",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21017"
                        ), listOf(
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
                            GeneratedMutableMap.map21210map,
                            GeneratedMutableMap.map21211map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21017","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21017"
                        ), listOf(
                            GeneratedMutableMap.map21212map,
                            GeneratedMutableMap.map21213map,
                            GeneratedMutableMap.map21214map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21228",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#_21228"
                        ), listOf(
                            GeneratedMutableMap.map21335map,
                            GeneratedMutableMap.map21336map,
                            GeneratedMutableMap.map21337map,
                            GeneratedMutableMap.map21338map,
                            GeneratedMutableMap.map21339map,
                            GeneratedMutableMap.map21340map,
                            GeneratedMutableMap.map21341map,
                            GeneratedMutableMap.map21342map,
                            GeneratedMutableMap.map21343map,
                            GeneratedMutableMap.map21344map,
                            GeneratedMutableMap.map21345map,
                            GeneratedMutableMap.map21346map,
                            GeneratedMutableMap.map21347map
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
                                GeneratedMutableMap.map22406map,
                                GeneratedMutableMap.map22407map,
                                GeneratedMutableMap.map22408map,
                                GeneratedMutableMap.map22409map,
                                GeneratedMutableMap.map22410map,
                                GeneratedMutableMap.map22411map,
                                GeneratedMutableMap.map22412map
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
                                GeneratedMutableMap.map22858map,
                                GeneratedMutableMap.map22859map,
                                GeneratedMutableMap.map22860map,
                                GeneratedMutableMap.map22861map,
                                GeneratedMutableMap.map22862map,
                                GeneratedMutableMap.map22863map,
                                GeneratedMutableMap.map22864map,
                                GeneratedMutableMap.map22865map,
                                GeneratedMutableMap.map22866map,
                                GeneratedMutableMap.map22867map,
                                GeneratedMutableMap.map22868map,
                                GeneratedMutableMap.map22869map,
                                GeneratedMutableMap.map22870map,
                                GeneratedMutableMap.map22871map,
                                GeneratedMutableMap.map22872map,
                                GeneratedMutableMap.map22873map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map22858map,
                                GeneratedMutableMap.map22859map,
                                GeneratedMutableMap.map22860map,
                                GeneratedMutableMap.map22861map,
                                GeneratedMutableMap.map22862map,
                                GeneratedMutableMap.map22863map,
                                GeneratedMutableMap.map22864map,
                                GeneratedMutableMap.map22865map,
                                GeneratedMutableMap.map22866map,
                                GeneratedMutableMap.map22867map,
                                GeneratedMutableMap.map22868map,
                                GeneratedMutableMap.map22869map,
                                GeneratedMutableMap.map22870map,
                                GeneratedMutableMap.map22871map,
                                GeneratedMutableMap.map22872map,
                                GeneratedMutableMap.map22873map
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
                                GeneratedMutableMap.map23798map,
                                GeneratedMutableMap.map23799map,
                                GeneratedMutableMap.map23800map,
                                GeneratedMutableMap.map23801map,
                                GeneratedMutableMap.map23802map
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
                                GeneratedMutableMap.map24268map
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
                        POPValues(dictionary, listOf(
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24268map
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
                                GeneratedMutableMap.map24269map
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
                        POPValues(dictionary, listOf(
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map24269map
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
                                GeneratedMutableMap.map24863map,
                                GeneratedMutableMap.map24864map,
                                GeneratedMutableMap.map24865map,
                                GeneratedMutableMap.map24866map,
                                GeneratedMutableMap.map24867map,
                                GeneratedMutableMap.map24868map,
                                GeneratedMutableMap.map24869map
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
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1"
                        ), listOf(
                                GeneratedMutableMap.map24863map,
                                GeneratedMutableMap.map24864map,
                                GeneratedMutableMap.map24865map,
                                GeneratedMutableMap.map24866map,
                                GeneratedMutableMap.map24867map,
                                GeneratedMutableMap.map24868map,
                                GeneratedMutableMap.map24869map
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
                                GeneratedMutableMap.map24870map,
                                GeneratedMutableMap.map24871map,
                                GeneratedMutableMap.map24872map,
                                GeneratedMutableMap.map24873map,
                                GeneratedMutableMap.map24874map,
                                GeneratedMutableMap.map24875map,
                                GeneratedMutableMap.map24876map
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
                        POPValues(dictionary, listOf(
                                "s2",
                                "str2"
                        ), listOf(
                                GeneratedMutableMap.map24870map,
                                GeneratedMutableMap.map24871map,
                                GeneratedMutableMap.map24872map,
                                GeneratedMutableMap.map24873map,
                                GeneratedMutableMap.map24874map,
                                GeneratedMutableMap.map24875map,
                                GeneratedMutableMap.map24876map
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
                                GeneratedMutableMap.map26600map,
                                GeneratedMutableMap.map26601map,
                                GeneratedMutableMap.map26602map,
                                GeneratedMutableMap.map26603map,
                                GeneratedMutableMap.map26604map,
                                GeneratedMutableMap.map26605map,
                                GeneratedMutableMap.map26606map,
                                GeneratedMutableMap.map26607map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "x"
                        ), listOf(
                                GeneratedMutableMap.map26600map,
                                GeneratedMutableMap.map26601map,
                                GeneratedMutableMap.map26602map,
                                GeneratedMutableMap.map26603map,
                                GeneratedMutableMap.map26604map,
                                GeneratedMutableMap.map26605map,
                                GeneratedMutableMap.map26606map,
                                GeneratedMutableMap.map26607map
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
                                GeneratedMutableMap.map26608map,
                                GeneratedMutableMap.map26609map,
                                GeneratedMutableMap.map26610map,
                                GeneratedMutableMap.map26611map,
                                GeneratedMutableMap.map26612map,
                                GeneratedMutableMap.map26613map,
                                GeneratedMutableMap.map26614map,
                                GeneratedMutableMap.map26615map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map26608map,
                                GeneratedMutableMap.map26609map,
                                GeneratedMutableMap.map26610map,
                                GeneratedMutableMap.map26611map,
                                GeneratedMutableMap.map26612map,
                                GeneratedMutableMap.map26613map,
                                GeneratedMutableMap.map26614map,
                                GeneratedMutableMap.map26615map
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
                                GeneratedMutableMap.map26960map
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
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map26960map
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
                                GeneratedMutableMap.map27031map
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
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map27031map
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
                                GeneratedMutableMap.map27172map
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
                        POPValues(dictionary, listOf(
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map27172map
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
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27483map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "date"
                        ), listOf(
                                GeneratedMutableMap.map27480map,
                                GeneratedMutableMap.map27481map,
                                GeneratedMutableMap.map27482map,
                                GeneratedMutableMap.map27483map
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
                            GeneratedMutableMap.map27480map,
                            GeneratedMutableMap.map27481map,
                            GeneratedMutableMap.map27482map,
                            GeneratedMutableMap.map27483map
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
                                GeneratedMutableMap.map30282map,
                                GeneratedMutableMap.map30283map,
                                GeneratedMutableMap.map30284map,
                                GeneratedMutableMap.map30285map,
                                GeneratedMutableMap.map30286map,
                                GeneratedMutableMap.map30287map,
                                GeneratedMutableMap.map30288map
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
                        POPValues(dictionary, listOf(
                                "a",
                                "s1"
                        ), listOf(
                                GeneratedMutableMap.map30282map,
                                GeneratedMutableMap.map30283map,
                                GeneratedMutableMap.map30284map,
                                GeneratedMutableMap.map30285map,
                                GeneratedMutableMap.map30286map,
                                GeneratedMutableMap.map30287map,
                                GeneratedMutableMap.map30288map
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
                                GeneratedMutableMap.map30289map,
                                GeneratedMutableMap.map30290map,
                                GeneratedMutableMap.map30291map,
                                GeneratedMutableMap.map30292map,
                                GeneratedMutableMap.map30293map,
                                GeneratedMutableMap.map30294map,
                                GeneratedMutableMap.map30295map
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
                        POPValues(dictionary, listOf(
                                "b",
                                "s2"
                        ), listOf(
                                GeneratedMutableMap.map30289map,
                                GeneratedMutableMap.map30290map,
                                GeneratedMutableMap.map30291map,
                                GeneratedMutableMap.map30292map,
                                GeneratedMutableMap.map30293map,
                                GeneratedMutableMap.map30294map,
                                GeneratedMutableMap.map30295map
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
                                GeneratedMutableMap.map31012map,
                                GeneratedMutableMap.map31013map,
                                GeneratedMutableMap.map31014map,
                                GeneratedMutableMap.map31015map,
                                GeneratedMutableMap.map31016map,
                                GeneratedMutableMap.map31017map,
                                GeneratedMutableMap.map31018map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map31012map,
                                GeneratedMutableMap.map31013map,
                                GeneratedMutableMap.map31014map,
                                GeneratedMutableMap.map31015map,
                                GeneratedMutableMap.map31016map,
                                GeneratedMutableMap.map31017map,
                                GeneratedMutableMap.map31018map
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
                                GeneratedMutableMap.map31676map,
                                GeneratedMutableMap.map31677map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map31676map,
                                GeneratedMutableMap.map31677map
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
                                GeneratedMutableMap.map31809map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                        ), listOf(
                                GeneratedMutableMap.map31809map
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
                                GeneratedMutableMap.map31676map,
                                GeneratedMutableMap.map31677map,
                                GeneratedMutableMap.map31953map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "v"
                        ), listOf(
                                GeneratedMutableMap.map31676map,
                                GeneratedMutableMap.map31677map,
                                GeneratedMutableMap.map31953map
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
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map10096map,
                                GeneratedMutableMap.map10097map,
                                GeneratedMutableMap.map32229map,
                                GeneratedMutableMap.map10099map,
                                GeneratedMutableMap.map32230map,
                                GeneratedMutableMap.map10101map
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
                                GeneratedMutableMap.map10274map,
                                GeneratedMutableMap.map10275map,
                                GeneratedMutableMap.map32355map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map32356map,
                                GeneratedMutableMap.map10279map
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
                        POPValues(dictionary, listOf(
                                "o",
                                "p2",
                                "o2"
                        ), listOf(
                                GeneratedMutableMap.map10274map,
                                GeneratedMutableMap.map10275map,
                                GeneratedMutableMap.map32355map,
                                GeneratedMutableMap.map10277map,
                                GeneratedMutableMap.map32356map,
                                GeneratedMutableMap.map10279map
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
                                GeneratedMutableMap.map5828map
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
                        POPValues(dictionary, listOf(
                        ), listOf(
                                GeneratedMutableMap.map5828map
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
                                GeneratedMutableMap.map32561map,
                                GeneratedMutableMap.map32562map,
                                GeneratedMutableMap.map32563map
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
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map32561map,
                                GeneratedMutableMap.map32562map,
                                GeneratedMutableMap.map32563map
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
                                GeneratedMutableMap.map34077map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map34077map
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
                                GeneratedMutableMap.map34078map,
                                GeneratedMutableMap.map34079map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34078map,
                                GeneratedMutableMap.map34079map
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
                                GeneratedMutableMap.map34078map,
                                GeneratedMutableMap.map34198map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "z"
                        ), listOf(
                                GeneratedMutableMap.map34078map,
                                GeneratedMutableMap.map34198map
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
                            GeneratedMutableMap.map34077map
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
                            GeneratedMutableMap.map34079map
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
                                GeneratedMutableMap.map34077map,
                                GeneratedMutableMap.map34415map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map34077map,
                                GeneratedMutableMap.map34415map
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
                                GeneratedMutableMap.map34509map,
                                GeneratedMutableMap.map34510map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34509map,
                                GeneratedMutableMap.map34510map
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
                                GeneratedMutableMap.map34509map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34509map
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
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#q>", "\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#q>", "l", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34682map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "l"
                        ), listOf(
                                GeneratedMutableMap.map34682map
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
                            graph.addData(1L, listOf("<http://www.example.org/instance#a>", "<http://www.example.org/schema#p>", "\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            graph.addData(1L, listOf("<http://www.example.org/instance#b>", "<http://www.example.org/schema#p>", "\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "x", "<http://www.example.org/schema#p>", "y", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map34077map,
                                GeneratedMutableMap.map34683map
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
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                        ), listOf(
                                GeneratedMutableMap.map34077map,
                                GeneratedMutableMap.map34683map
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
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
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
                        POPValues(dictionary, listOf(
                                "O"
                        ), listOf(
                                GeneratedMutableMap.map36108map,
                                GeneratedMutableMap.map36109map,
                                GeneratedMutableMap.map36110map,
                                GeneratedMutableMap.map36111map
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
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35969"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35970"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35971"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35972"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35973"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35974"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35975"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35976"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35977"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35978"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_35992", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_35992"
                        ), listOf(
                                GeneratedMutableMap.map36112map,
                                GeneratedMutableMap.map36113map,
                                GeneratedMutableMap.map36114map,
                                GeneratedMutableMap.map36115map,
                                GeneratedMutableMap.map36116map,
                                GeneratedMutableMap.map36117map,
                                GeneratedMutableMap.map36118map,
                                GeneratedMutableMap.map36119map,
                                GeneratedMutableMap.map36120map,
                                GeneratedMutableMap.map36121map
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
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35969"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35970"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_35971"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35972"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35973"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_35974"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35975"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_35976"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35977"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_35978"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_35992"), graphName, false)
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_35992"
                        ), listOf(
                                GeneratedMutableMap.map36112map,
                                GeneratedMutableMap.map36113map,
                                GeneratedMutableMap.map36114map,
                                GeneratedMutableMap.map36115map,
                                GeneratedMutableMap.map36116map,
                                GeneratedMutableMap.map36117map,
                                GeneratedMutableMap.map36118map,
                                GeneratedMutableMap.map36119map,
                                GeneratedMutableMap.map36120map,
                                GeneratedMutableMap.map36121map
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
                            graph.addData(1L, listOf("_:_35969", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_35970", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_35971", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_35972", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_35973", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_35974", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_35975", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_35976", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_35977", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_35978", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_35992", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36122map,
                                GeneratedMutableMap.map36123map,
                                GeneratedMutableMap.map36124map,
                                GeneratedMutableMap.map36125map,
                                GeneratedMutableMap.map36126map,
                                GeneratedMutableMap.map36127map,
                                GeneratedMutableMap.map36128map,
                                GeneratedMutableMap.map36129map,
                                GeneratedMutableMap.map36130map,
                                GeneratedMutableMap.map36131map
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
                            graph.addData(1L, listOf("_:_35969", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_35970", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_35971", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_35972", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_35973", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_35974", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_35975", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_35976", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_35977", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_35978", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#_35992"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                        }(),
                        POPValues(dictionary, listOf(
                                "#_35992",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36122map,
                                GeneratedMutableMap.map36123map,
                                GeneratedMutableMap.map36124map,
                                GeneratedMutableMap.map36125map,
                                GeneratedMutableMap.map36126map,
                                GeneratedMutableMap.map36127map,
                                GeneratedMutableMap.map36128map,
                                GeneratedMutableMap.map36129map,
                                GeneratedMutableMap.map36130map,
                                GeneratedMutableMap.map36131map
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
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "P",
                                "F"
                        ), listOf(
                                GeneratedMutableMap.map36329map
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
                        POPValues(dictionary, listOf(
                                "P",
                                "F"
                        ), listOf(
                                GeneratedMutableMap.map36329map
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
                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36330map
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
                        POPValues(dictionary, listOf(
                                "P",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36330map
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
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36337"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36338"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36339"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36340"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36341"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36342"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36343"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36344"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36345"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36346"))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "O", "<http://www.example.orghasItem>", "#_36360", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_36360"
                        ), listOf(
                                GeneratedMutableMap.map36476map,
                                GeneratedMutableMap.map36477map,
                                GeneratedMutableMap.map36478map,
                                GeneratedMutableMap.map36479map,
                                GeneratedMutableMap.map36480map,
                                GeneratedMutableMap.map36481map,
                                GeneratedMutableMap.map36482map,
                                GeneratedMutableMap.map36483map,
                                GeneratedMutableMap.map36484map,
                                GeneratedMutableMap.map36485map
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
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36337"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36338"))
                            graph.addData(1L, listOf("<http://www.example.orgorder1>", "<http://www.example.orghasItem>", "_:_36339"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36340"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36341"))
                            graph.addData(1L, listOf("<http://www.example.orgorder2>", "<http://www.example.orghasItem>", "_:_36342"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36343"))
                            graph.addData(1L, listOf("<http://www.example.orgorder3>", "<http://www.example.orghasItem>", "_:_36344"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36345"))
                            graph.addData(1L, listOf("<http://www.example.orgorder4>", "<http://www.example.orghasItem>", "_:_36346"))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("O"), AOPVariable.calculate("<http://www.example.orghasItem>"), AOPVariable("#_36360"), graphName, false)
                        }(),
                        POPValues(dictionary, listOf(
                                "O",
                                "#_36360"
                        ), listOf(
                                GeneratedMutableMap.map36476map,
                                GeneratedMutableMap.map36477map,
                                GeneratedMutableMap.map36478map,
                                GeneratedMutableMap.map36479map,
                                GeneratedMutableMap.map36480map,
                                GeneratedMutableMap.map36481map,
                                GeneratedMutableMap.map36482map,
                                GeneratedMutableMap.map36483map,
                                GeneratedMutableMap.map36484map,
                                GeneratedMutableMap.map36485map
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
                            graph.addData(1L, listOf("_:_36337", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_36338", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_36339", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_36340", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_36341", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_36342", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_36343", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_36344", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_36345", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_36346", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "#_36360", "<http://www.w3.org/2000/01/rdf-schema#label>", "L", false, true, false, EIndexPattern.SPO)
                        }(),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36486map,
                                GeneratedMutableMap.map36487map,
                                GeneratedMutableMap.map36488map,
                                GeneratedMutableMap.map36489map,
                                GeneratedMutableMap.map36490map,
                                GeneratedMutableMap.map36491map,
                                GeneratedMutableMap.map36492map,
                                GeneratedMutableMap.map36493map,
                                GeneratedMutableMap.map36494map,
                                GeneratedMutableMap.map36495map
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
                            graph.addData(1L, listOf("_:_36337", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_36338", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pizza\""))
                            graph.addData(1L, listOf("_:_36339", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Wine\""))
                            graph.addData(1L, listOf("_:_36340", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Ice Cream\""))
                            graph.addData(1L, listOf("_:_36341", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Pasta\""))
                            graph.addData(1L, listOf("_:_36342", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_36343", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Sandwich\""))
                            graph.addData(1L, listOf("_:_36344", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            graph.addData(1L, listOf("_:_36345", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Bagel\""))
                            graph.addData(1L, listOf("_:_36346", "<http://www.w3.org/2000/01/rdf-schema#label>", "\"Soft Drink\""))
                            DistributedTripleStore.commit(1L)
                            LOPTriple(AOPVariable("#_36360"), AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"), AOPVariable("L"), graphName, false)
                        }(),
                        POPValues(dictionary, listOf(
                                "#_36360",
                                "L"
                        ), listOf(
                                GeneratedMutableMap.map36486map,
                                GeneratedMutableMap.map36487map,
                                GeneratedMutableMap.map36488map,
                                GeneratedMutableMap.map36489map,
                                GeneratedMutableMap.map36490map,
                                GeneratedMutableMap.map36491map,
                                GeneratedMutableMap.map36492map,
                                GeneratedMutableMap.map36493map,
                                GeneratedMutableMap.map36494map,
                                GeneratedMutableMap.map36495map
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
                        graph.addData(1L,listOf("<http://example.org/ns#b>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:ben@example.org>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://xmlns.com/foaf/0.1/mbox>","mbox",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person",
                            "mbox"
                        ), listOf(
                            GeneratedMutableMap.map36766map
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
                        graph.addData(1L,listOf("<http://example.org/ns#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"person","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "person"
                        ), listOf(
                            GeneratedMutableMap.map36767map
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
                            GeneratedMutableMap.map36768map
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
                            GeneratedMutableMap.map36769map
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
