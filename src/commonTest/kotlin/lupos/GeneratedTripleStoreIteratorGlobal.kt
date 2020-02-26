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


class GeneratedTripleStoreIteratorGlobalTest {
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
                            GeneratedMutableMap.map357map,
                            GeneratedMutableMap.map358map,
                            GeneratedMutableMap.map359map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/william>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/givenName>","\"William\""))
                        graph.addData(1L,listOf("<http://example.org/william>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:bill@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map360map,
                            GeneratedMutableMap.map361map,
                            GeneratedMutableMap.map362map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map363map,
                            GeneratedMutableMap.map364map,
                            GeneratedMutableMap.map365map,
                            GeneratedMutableMap.map357map,
                            GeneratedMutableMap.map358map,
                            GeneratedMutableMap.map359map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map366map,
                            GeneratedMutableMap.map367map,
                            GeneratedMutableMap.map368map,
                            GeneratedMutableMap.map360map,
                            GeneratedMutableMap.map361map,
                            GeneratedMutableMap.map362map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-07.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map363map,
                            GeneratedMutableMap.map364map,
                            GeneratedMutableMap.map365map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map366map,
                            GeneratedMutableMap.map367map,
                            GeneratedMutableMap.map368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-08.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map363map,
                            GeneratedMutableMap.map364map,
                            GeneratedMutableMap.map365map,
                            GeneratedMutableMap.map428map,
                            GeneratedMutableMap.map429map,
                            GeneratedMutableMap.map430map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-03.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map366map,
                            GeneratedMutableMap.map367map,
                            GeneratedMutableMap.map368map,
                            GeneratedMutableMap.map431map,
                            GeneratedMutableMap.map432map,
                            GeneratedMutableMap.map433map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-03.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map363map,
                            GeneratedMutableMap.map364map,
                            GeneratedMutableMap.map498map,
                            GeneratedMutableMap.map365map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/john>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/givenName>","\"John\""))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/sue>"))
                        graph.addData(1L,listOf("<http://example.org/john>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:johnny@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map366map,
                            GeneratedMutableMap.map367map,
                            GeneratedMutableMap.map499map,
                            GeneratedMutableMap.map368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/add/add-05.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map978map,
                            GeneratedMutableMap.map979map,
                            GeneratedMutableMap.map980map,
                            GeneratedMutableMap.map981map,
                            GeneratedMutableMap.map982map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o1>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o2>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p1>","<http://www.example.org/o3>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p2>","<http://www.example.org/o1>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p2>","<http://www.example.org/o2>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("S"),AOPVariable("P"),AOPVariable("O"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("S"),
                            AOPVariable("P"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map983map,
                            GeneratedMutableMap.map984map,
                            GeneratedMutableMap.map985map,
                            GeneratedMutableMap.map986map,
                            GeneratedMutableMap.map987map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "O1"
                        ), listOf(
                            GeneratedMutableMap.map2749map,
                            GeneratedMutableMap.map2750map,
                            GeneratedMutableMap.map2751map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/p>"),AOPVariable("O1"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("S"),
                            AOPVariable("O1")
                        ), listOf(
                            GeneratedMutableMap.map2752map,
                            GeneratedMutableMap.map2753map,
                            GeneratedMutableMap.map2754map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "O2"
                        ), listOf(
                            GeneratedMutableMap.map2755map,
                            GeneratedMutableMap.map2756map,
                            GeneratedMutableMap.map2757map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"0\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/s>","<http://www.example.org/q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("S"),AOPVariable.calculate("<http://www.example.org/q>"),AOPVariable("O2"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("S"),
                            AOPVariable("O2")
                        ), listOf(
                            GeneratedMutableMap.map2752map,
                            GeneratedMutableMap.map2753map,
                            GeneratedMutableMap.map2754map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            {
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
                            GeneratedMutableMap.map3298map,
                            GeneratedMutableMap.map3299map,
                            GeneratedMutableMap.map3300map,
                            GeneratedMutableMap.map3301map,
                            GeneratedMutableMap.map3302map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://www.example.org/dec>"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map3303map,
                            GeneratedMutableMap.map3304map,
                            GeneratedMutableMap.map3305map,
                            GeneratedMutableMap.map3306map,
                            GeneratedMutableMap.map3307map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map3468map,
                            GeneratedMutableMap.map3469map,
                            GeneratedMutableMap.map3470map,
                            GeneratedMutableMap.map3471map,
                            GeneratedMutableMap.map3472map,
                            GeneratedMutableMap.map3473map,
                            GeneratedMutableMap.map3474map,
                            GeneratedMutableMap.map3475map,
                            GeneratedMutableMap.map3476map,
                            GeneratedMutableMap.map3477map,
                            GeneratedMutableMap.map3478map,
                            GeneratedMutableMap.map3479map,
                            GeneratedMutableMap.map3480map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map3481map,
                            GeneratedMutableMap.map3482map,
                            GeneratedMutableMap.map3483map,
                            GeneratedMutableMap.map3484map,
                            GeneratedMutableMap.map3485map,
                            GeneratedMutableMap.map3486map,
                            GeneratedMutableMap.map3487map,
                            GeneratedMutableMap.map3488map,
                            GeneratedMutableMap.map3489map,
                            GeneratedMutableMap.map3490map,
                            GeneratedMutableMap.map3491map,
                            GeneratedMutableMap.map3492map,
                            GeneratedMutableMap.map3493map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map3468map,
                            GeneratedMutableMap.map3469map,
                            GeneratedMutableMap.map3470map,
                            GeneratedMutableMap.map3471map,
                            GeneratedMutableMap.map3472map,
                            GeneratedMutableMap.map3473map,
                            GeneratedMutableMap.map3474map,
                            GeneratedMutableMap.map3475map,
                            GeneratedMutableMap.map3476map,
                            GeneratedMutableMap.map3477map,
                            GeneratedMutableMap.map3478map,
                            GeneratedMutableMap.map4225map,
                            GeneratedMutableMap.map3480map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map3481map,
                            GeneratedMutableMap.map3482map,
                            GeneratedMutableMap.map3483map,
                            GeneratedMutableMap.map3484map,
                            GeneratedMutableMap.map3485map,
                            GeneratedMutableMap.map3486map,
                            GeneratedMutableMap.map3487map,
                            GeneratedMutableMap.map3488map,
                            GeneratedMutableMap.map3489map,
                            GeneratedMutableMap.map3490map,
                            GeneratedMutableMap.map3491map,
                            GeneratedMutableMap.map4226map,
                            GeneratedMutableMap.map3493map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
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
                            GeneratedMutableMap.map3298map,
                            GeneratedMutableMap.map3299map,
                            GeneratedMutableMap.map3300map,
                            GeneratedMutableMap.map3301map,
                            GeneratedMutableMap.map3302map
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
                            GeneratedMutableMap.map4971map,
                            GeneratedMutableMap.map4972map,
                            GeneratedMutableMap.map4973map,
                            GeneratedMutableMap.map4974map,
                            GeneratedMutableMap.map4975map,
                            GeneratedMutableMap.map4976map,
                            GeneratedMutableMap.map4977map,
                            GeneratedMutableMap.map4978map,
                            GeneratedMutableMap.map4979map,
                            GeneratedMutableMap.map4980map,
                            GeneratedMutableMap.map4981map,
                            GeneratedMutableMap.map4982map
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
                            GeneratedMutableMap.map5331map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/update-silent/add-silent.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5332map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/update-silent/add-silent.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5393map,
                            GeneratedMutableMap.map5331map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-data-named2.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o2>"))
                        graph.addData(1L,listOf("<http://example.org/ns#s>","<http://example.org/ns#p>","<http://example.org/ns#o>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5394map,
                            GeneratedMutableMap.map5332map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-data-named2.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"o\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5472map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5471map,
                            GeneratedMutableMap.map5473map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"o\""))
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5472map,
                            GeneratedMutableMap.map5474map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5473map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5474map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-02.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5606map,
                            GeneratedMutableMap.map5473map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://example.org/b>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/s>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5607map,
                            GeneratedMutableMap.map5474map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-04.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5681map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/p>","\"z\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5682map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5683map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/p>","\"y\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5684map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map5606map,
                            GeneratedMutableMap.map5685map,
                            GeneratedMutableMap.map5686map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://example.org/b>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/p>","\"q\""))
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/p>","\"q\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map5607map,
                            GeneratedMutableMap.map5687map,
                            GeneratedMutableMap.map5688map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/basic-update/insert-using-01.ru */ ,
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
                            GeneratedMutableMap.map5899map
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
                            GeneratedMutableMap.map6333map
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
                            GeneratedMutableMap.map6333map
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
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map,
                            GeneratedMutableMap.map6720map,
                            GeneratedMutableMap.map6721map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
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
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map,
                            GeneratedMutableMap.map6720map,
                            GeneratedMutableMap.map6721map
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
                            GeneratedMutableMap.map7193map,
                            GeneratedMutableMap.map7194map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map
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
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map8143map,
                            GeneratedMutableMap.map8144map,
                            GeneratedMutableMap.map8145map,
                            GeneratedMutableMap.map8146map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "price"
                        ), listOf(
                            GeneratedMutableMap.map8501map,
                            GeneratedMutableMap.map8502map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://example.org/ns#price>"),AOPVariable("price"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("price")
                        ), listOf(
                            GeneratedMutableMap.map8503map,
                            GeneratedMutableMap.map8504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "title"
                        ), listOf(
                            GeneratedMutableMap.map8505map,
                            GeneratedMutableMap.map8506map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://purl.org/dc/elements/1.1/title>"),AOPVariable("title"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("book"),
                            AOPVariable("title")
                        ), listOf(
                            GeneratedMutableMap.map8507map,
                            GeneratedMutableMap.map8508map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/inline01.rq */ ,
            {
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p1","o1",false,false,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p2","o2",false,false,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map9049map,
                            GeneratedMutableMap.map9050map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("o2"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map9051map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map9407map,
                            GeneratedMutableMap.map9408map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-default-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/description>","\"Graph 1\""))
                        graph.addData(1L,listOf("<http://example.org/g1>","<http://example.org/name>","\"G1\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9409map,
                            GeneratedMutableMap.map9410map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-default-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map9411map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-graph-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/g2>","<http://example.org/name>","\"G2\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9412map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-graph-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map9442map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-named-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<>","<http://example.org/name>","\"Default Graph\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9443map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/drop/drop-named-01.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map9611map,
                            GeneratedMutableMap.map9612map,
                            GeneratedMutableMap.map9613map,
                            GeneratedMutableMap.map9614map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o2>"))
                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","<http://example.org/o3>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map9615map,
                            GeneratedMutableMap.map9616map,
                            GeneratedMutableMap.map9617map,
                            GeneratedMutableMap.map9618map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */ ,
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
                            GeneratedMutableMap.map9748map
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
                            GeneratedMutableMap.map9749map
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
                            GeneratedMutableMap.map9862map
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
                            GeneratedMutableMap.map9863map
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
                            GeneratedMutableMap.map10021map,
                            GeneratedMutableMap.map10022map,
                            GeneratedMutableMap.map10023map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/copy/copy-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://xmlns.com/foaf/0.1/givenName>","\"Ronnie\""))
                        graph.addData(1L,listOf("<http://example.org/ron>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:ronnie@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10024map,
                            GeneratedMutableMap.map10025map,
                            GeneratedMutableMap.map10026map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/copy/copy-07.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map10086map,
                            GeneratedMutableMap.map10087map,
                            GeneratedMutableMap.map10088map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://xmlns.com/foaf/0.1/givenName>","\"Jerry\""))
                        graph.addData(1L,listOf("<http://example.org/jerry>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:jerry@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10089map,
                            GeneratedMutableMap.map10090map,
                            GeneratedMutableMap.map10091map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10293map
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10293map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
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
                            GeneratedMutableMap.map10508map,
                            GeneratedMutableMap.map10509map,
                            GeneratedMutableMap.map10510map,
                            GeneratedMutableMap.map10511map,
                            GeneratedMutableMap.map10512map,
                            GeneratedMutableMap.map10513map
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
                            GeneratedMutableMap.map10508map,
                            GeneratedMutableMap.map10509map,
                            GeneratedMutableMap.map10510map,
                            GeneratedMutableMap.map10511map,
                            GeneratedMutableMap.map10512map,
                            GeneratedMutableMap.map10513map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
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
                            GeneratedMutableMap.map10789map,
                            GeneratedMutableMap.map10790map,
                            GeneratedMutableMap.map10791map,
                            GeneratedMutableMap.map10792map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10795map
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
                            GeneratedMutableMap.map10789map,
                            GeneratedMutableMap.map10790map,
                            GeneratedMutableMap.map10791map,
                            GeneratedMutableMap.map10792map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10795map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10855map,
                            GeneratedMutableMap.map10856map,
                            GeneratedMutableMap.map10857map,
                            GeneratedMutableMap.map10858map,
                            GeneratedMutableMap.map10859map,
                            GeneratedMutableMap.map10860map,
                            GeneratedMutableMap.map10861map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-where/delete-where-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8581map,
                            GeneratedMutableMap.map8582map,
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-where/delete-where-05.ru */ ,
            {
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
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
                            GeneratedMutableMap.map11143map,
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11144map,
                            GeneratedMutableMap.map11145map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map
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
                            GeneratedMutableMap.map11386map,
                            GeneratedMutableMap.map11387map,
                            GeneratedMutableMap.map11388map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("b"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("b")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map,
                            GeneratedMutableMap.map9051map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-02.ru */ ,
            {
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
                            GeneratedMutableMap.map11390map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map11391map,
                            GeneratedMutableMap.map11392map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11393map,
                            GeneratedMutableMap.map11394map,
                            GeneratedMutableMap.map11395map,
                            GeneratedMutableMap.map11396map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map11397map,
                            GeneratedMutableMap.map8582map,
                            GeneratedMutableMap.map11398map,
                            GeneratedMutableMap.map11399map,
                            GeneratedMutableMap.map8584map,
                            GeneratedMutableMap.map11400map,
                            GeneratedMutableMap.map11401map,
                            GeneratedMutableMap.map11402map,
                            GeneratedMutableMap.map11403map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-01.ru */ ,
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
                            GeneratedMutableMap.map11386map,
                            GeneratedMutableMap.map11387map,
                            GeneratedMutableMap.map11388map
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
                            GeneratedMutableMap.map11390map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map11391map,
                            GeneratedMutableMap.map11392map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11393map,
                            GeneratedMutableMap.map11394map,
                            GeneratedMutableMap.map11395map,
                            GeneratedMutableMap.map11396map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/name>","\"Alan\"",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a"
                        ), listOf(
                            GeneratedMutableMap.map11658map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Alan\""),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("a")
                        ), listOf(
                            GeneratedMutableMap.map11659map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map11390map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map11392map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11395map,
                            GeneratedMutableMap.map11396map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map11397map,
                            GeneratedMutableMap.map8582map,
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map11399map,
                            GeneratedMutableMap.map8584map,
                            GeneratedMutableMap.map11402map,
                            GeneratedMutableMap.map11403map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map11892map,
                            GeneratedMutableMap.map11893map,
                            GeneratedMutableMap.map11894map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("Var_B"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map11389map,
                            GeneratedMutableMap.map9051map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map12025map,
                            GeneratedMutableMap.map11390map,
                            GeneratedMutableMap.map8577map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map11392map,
                            GeneratedMutableMap.map8579map,
                            GeneratedMutableMap.map11395map,
                            GeneratedMutableMap.map11396map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map12026map,
                            GeneratedMutableMap.map11397map,
                            GeneratedMutableMap.map8582map,
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map11399map,
                            GeneratedMutableMap.map8584map,
                            GeneratedMutableMap.map11402map,
                            GeneratedMutableMap.map11403map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://xmlns.com/foaf/0.1/knows>","Var_B",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "Var_B"
                        ), listOf(
                            GeneratedMutableMap.map11894map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("Var_B"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("a"),
                            AOPVariable("Var_B")
                        ), listOf(
                            GeneratedMutableMap.map9051map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete-insert/delete-insert-05b.ru */ ,
            {
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
                            GeneratedMutableMap.map13042map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/a>"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("s"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s")
                        ), listOf(
                            GeneratedMutableMap.map13043map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/mbox>","\"alan@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/name>","\"Alan\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
            }() /* resources/sparql11-test-suite/delete/delete-using-01.ru */ ,
            {
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
                            GeneratedMutableMap.map13443map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/c>","<http://xmlns.com/foaf/0.1/name>","\"Chris\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"Chris\""),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s")
                        ), listOf(
                            GeneratedMutableMap.map13444map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8580map,
                            GeneratedMutableMap.map9055map,
                            GeneratedMutableMap.map8583map,
                            GeneratedMutableMap.map8584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-06.ru */ ,
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
                            GeneratedMutableMap.map13042map
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map8576map,
                            GeneratedMutableMap.map8577map
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
                            GeneratedMutableMap.map13443map
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
                            GeneratedMutableMap.map11143map,
                            GeneratedMutableMap.map11139map,
                            GeneratedMutableMap.map11140map,
                            GeneratedMutableMap.map11144map,
                            GeneratedMutableMap.map11145map
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
                            GeneratedMutableMap.map11144map,
                            GeneratedMutableMap.map11145map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map14194map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable.calculate("<http://example.org/b>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s")
                        ), listOf(
                            GeneratedMutableMap.map11659map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/mbox>","\"bob@example.org\""))
                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/name>","\"Bob\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
            }() /* resources/sparql11-test-suite/delete/delete-using-05.ru */ ,
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
                            GeneratedMutableMap.map13443map
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
                            GeneratedMutableMap.map8575map,
                            GeneratedMutableMap.map11138map,
                            GeneratedMutableMap.map8578map,
                            GeneratedMutableMap.map8579map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#myBanana>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15725map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#myBanana>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/ns#banana>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15726map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "#c"
                        ), listOf(
                            GeneratedMutableMap.map15764map,
                            GeneratedMutableMap.map15765map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c1"))
                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c2"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://example.org/ns#b1>"),AOPVariable("#c"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("#c")
                        ), listOf(
                            GeneratedMutableMap.map15766map,
                            GeneratedMutableMap.map15767map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15810map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15811map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
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
                            GeneratedMutableMap.map15849map
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
                            GeneratedMutableMap.map16026map
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
                            GeneratedMutableMap.map16027map,
                            GeneratedMutableMap.map16028map,
                            GeneratedMutableMap.map16029map,
                            GeneratedMutableMap.map16030map,
                            GeneratedMutableMap.map16031map,
                            GeneratedMutableMap.map16032map,
                            GeneratedMutableMap.map16033map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","x",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16154map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/ns#d>"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#range>"),AOPVariable("x"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map16155map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
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
                            GeneratedMutableMap.map16295map
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
                            GeneratedMutableMap.map16295map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","<http://example.org/ns#apple>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","f",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "f"
                        ), listOf(
                            GeneratedMutableMap.map16339map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","<http://example.org/ns#apple>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/ns#favourite-fruit>"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>"),AOPVariable("f"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("f")
                        ), listOf(
                            GeneratedMutableMap.map16340map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable("c"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/y>"))
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","_:y"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://example.org/x/p>"),AOPVariable("y"),graphName,false)                    }(),
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16567map,
                            GeneratedMutableMap.map16568map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("y"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map16569map,
                            GeneratedMutableMap.map16570map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/owlds02.rq */ ,
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
                            GeneratedMutableMap.map16612map
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
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/name>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map16780map
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
                        graph.addData(1L,listOf("_:_16654","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#c",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        graph.addData(1L,listOf("<http://example.org/person1>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/hasPublication>","#b0",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#b0"
                        ), listOf(
                            GeneratedMutableMap.map16929map,
                            GeneratedMutableMap.map16930map
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
                        graph.addData(1L,listOf("_:_16801","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://example.org/Conference>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16821","<http://www.w3.org/2002/07/owl#someValuesFrom>","#_16830",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16821",
                            "#_16830"
                        ), listOf(
                            GeneratedMutableMap.map16934map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16830","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_16801","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16821","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16821"
                        ), listOf(
                            GeneratedMutableMap.map16931map
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
                        graph.addData(1L,listOf("_:_16801","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b0","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_16821",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_16801","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_16821","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/publishedAt>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_16821"
                        ), listOf(
                            GeneratedMutableMap.map16931map
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
                            GeneratedMutableMap.map17047map
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
                            GeneratedMutableMap.map16612map
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
                        graph.addData(1L,listOf("_:_16973","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","c",false,true,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q4.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"p","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "p"
                        ), listOf(
                            GeneratedMutableMap.map17131map,
                            GeneratedMutableMap.map17132map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#ObjectProperty>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("p")
                        ), listOf(
                            GeneratedMutableMap.map17133map,
                            GeneratedMutableMap.map17134map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "p",
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map17135map,
                            GeneratedMutableMap.map17136map,
                            GeneratedMutableMap.map17137map,
                            GeneratedMutableMap.map17138map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/John>"),AOPVariable("p"),AOPVariable("v"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map17139map,
                            GeneratedMutableMap.map17140map,
                            GeneratedMutableMap.map17141map,
                            GeneratedMutableMap.map17142map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q5.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://xmlns.com/foaf/0.1/name>","\"name\"@en",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map17183map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"name\"@en"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map17184map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
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
                            GeneratedMutableMap.map17288map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("p")
                        ), listOf(
                            GeneratedMutableMap.map17289map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
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
                            GeneratedMutableMap.map17290map,
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map,
                            GeneratedMutableMap.map6720map,
                            GeneratedMutableMap.map6721map,
                            GeneratedMutableMap.map17291map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
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
                            GeneratedMutableMap.map17780map,
                            GeneratedMutableMap.map7193map,
                            GeneratedMutableMap.map7194map,
                            GeneratedMutableMap.map7195map,
                            GeneratedMutableMap.map7196map,
                            GeneratedMutableMap.map17781map
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
                            GeneratedMutableMap.map17288map
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
                            GeneratedMutableMap.map17782map
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
                            GeneratedMutableMap.map17290map,
                            GeneratedMutableMap.map6718map,
                            GeneratedMutableMap.map6719map,
                            GeneratedMutableMap.map6720map,
                            GeneratedMutableMap.map6721map,
                            GeneratedMutableMap.map17291map
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
                            GeneratedMutableMap.map16027map,
                            GeneratedMutableMap.map16029map,
                            GeneratedMutableMap.map16030map,
                            GeneratedMutableMap.map16493map
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
                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/a>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/x/p>","#y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "#y"
                        ), listOf(
                            GeneratedMutableMap.map18768map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X"
                        ), listOf(
                            GeneratedMutableMap.map18841map,
                            GeneratedMutableMap.map18842map,
                            GeneratedMutableMap.map18843map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/test#Person>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("X")
                        ), listOf(
                            GeneratedMutableMap.map18844map,
                            GeneratedMutableMap.map17184map,
                            GeneratedMutableMap.map18845map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#name>"),AOPVariable("Y1"),graphName,false)                    }(),
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
                            "Y2"
                        ), listOf(
                            GeneratedMutableMap.map18850map,
                            GeneratedMutableMap.map18851map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#nick>","\"Anick\""))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#nick>","\"Bnick\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#nick>"),AOPVariable("Y2"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y2")
                        ), listOf(
                            GeneratedMutableMap.map18852map,
                            GeneratedMutableMap.map18853map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-04.rq */ ,
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
                            GeneratedMutableMap.map18900map,
                            GeneratedMutableMap.map18901map,
                            GeneratedMutableMap.map18902map
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
                            GeneratedMutableMap.map18987map,
                            GeneratedMutableMap.map18988map
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
                            GeneratedMutableMap.map18989map
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
                            GeneratedMutableMap.map18990map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#s>","#aa",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "Y",
                            "#aa"
                        ), listOf(
                            GeneratedMutableMap.map19074map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("Y"),AOPVariable.calculate("<http://example.org/test#s>"),AOPVariable("#aa"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("Y"),
                            AOPVariable("#aa")
                        ), listOf(
                            GeneratedMutableMap.map19075map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#t>","Y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19076map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#t>"),AOPVariable("Y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19077map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
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
                            GeneratedMutableMap.map18990map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#aa"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("#aa")
                        ), listOf(
                            GeneratedMutableMap.map19078map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19079map,
                            GeneratedMutableMap.map19080map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("#aa"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Z"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("#aa"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19081map,
                            GeneratedMutableMap.map19082map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a"
                        ), listOf(
                            GeneratedMutableMap.map19143map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a")
                        ), listOf(
                            GeneratedMutableMap.map19144map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19145map,
                            GeneratedMutableMap.map19146map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19081map,
                            GeneratedMutableMap.map19082map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","#a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "#a"
                        ), listOf(
                            GeneratedMutableMap.map19208map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("#a")
                        ), listOf(
                            GeneratedMutableMap.map19209map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#a","<http://example.org/test#q>","Y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#a",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19210map,
                            GeneratedMutableMap.map19211map,
                            GeneratedMutableMap.map19212map,
                            GeneratedMutableMap.map19213map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#q>"),AOPVariable("Y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("#a"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19214map,
                            GeneratedMutableMap.map19215map,
                            GeneratedMutableMap.map19216map,
                            GeneratedMutableMap.map19217map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
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
                            GeneratedMutableMap.map19317map,
                            GeneratedMutableMap.map19318map,
                            GeneratedMutableMap.map19319map,
                            GeneratedMutableMap.map19320map
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
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"X","<http://example.org/test#p>","a",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "X",
                            "a"
                        ), listOf(
                            GeneratedMutableMap.map19321map
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
                            GeneratedMutableMap.map19410map
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
                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"a","<http://example.org/test#p>","b",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "a",
                            "b"
                        ), listOf(
                            GeneratedMutableMap.map19486map
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
                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/2002/07/owl#sameAs>","<http://example.org/test#x>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://www.w3.org/2002/07/owl#sameAs>","x",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "b",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map19487map
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
                            GeneratedMutableMap.map19489map,
                            GeneratedMutableMap.map19490map,
                            GeneratedMutableMap.map19491map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://example.org/test#hasChild>","<http://example.org/test#Charlie>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://example.org/test#hasChild>","<http://example.org/test#Alice>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://example.org/test#hasChild>","child",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "parent",
                            "child"
                        ), listOf(
                            GeneratedMutableMap.map19579map,
                            GeneratedMutableMap.map19580map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://example.org/test#hasChild>","<http://example.org/test#Charlie>"))
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://example.org/test#hasChild>","<http://example.org/test#Alice>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("parent"),AOPVariable.calculate("<http://example.org/test#hasChild>"),AOPVariable("child"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("parent"),
                            AOPVariable("child")
                        ), listOf(
                            GeneratedMutableMap.map19581map,
                            GeneratedMutableMap.map19582map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19608","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19594","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19608","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19594","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19608","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19594"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19587","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19590","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19593","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19594","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19595","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19608",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19703"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19696","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19699","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19702","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19703","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19704","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19720",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_19702","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19703","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19720","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19702","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19703","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19720","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19820"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19813","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19816","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19819","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19820","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19821","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19834",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_19819","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19820","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19834","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19819","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19820","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19834","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_19928","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19929","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19944","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_19929"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_19922","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19925","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_19928","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19929","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_19930","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_19944",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_19928","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_19929","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_19944","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_20055"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_20048","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20051","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20054","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20055","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20056","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20070",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_20054","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20055","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20070","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20054","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20055","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20070","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","_:_20181"))
                        graph.addData(1L,listOf("<http://example.org/test#Father>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Female>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Male>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Mother>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#Parent>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("<http://example.org/test#hasChild>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                        graph.addData(1L,listOf("_:_20174","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20177","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:_20180","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20181","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20182","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>"))
                        graph.addData(1L,listOf("_:ont","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Ontology>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20196",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        graph.addData(1L,listOf("_:_20180","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20181","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20196","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20180","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20181","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20196","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20306","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20307","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20323","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20306","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20323","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20306","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20307","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20323","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
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
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("_:_20399","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        graph.addData(1L,listOf("_:_20400","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#onProperty>","<http://example.org/test#hasChild>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20497map,
                            GeneratedMutableMap.map20498map
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
                        graph.addData(1L,listOf("_:_20399","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/2002/07/owl#someValuesFrom>","<http://www.w3.org/2002/07/owl#Thing>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20497map
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
                        graph.addData(1L,listOf("_:_20399","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        graph.addData(1L,listOf("_:_20400","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#b","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Restriction>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#b"
                        ), listOf(
                            GeneratedMutableMap.map20497map,
                            GeneratedMutableMap.map20498map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20512",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20630",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20787",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_20944","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_20944"
                        ), listOf(
                            GeneratedMutableMap.map21048map,
                            GeneratedMutableMap.map21049map,
                            GeneratedMutableMap.map21050map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_20944",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21077",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21085","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21085"
                        ), listOf(
                            GeneratedMutableMap.map21233map,
                            GeneratedMutableMap.map21234map,
                            GeneratedMutableMap.map21235map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21249",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21257","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21257"
                        ), listOf(
                            GeneratedMutableMap.map21430map,
                            GeneratedMutableMap.map21431map,
                            GeneratedMutableMap.map21432map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21446","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21446"
                        ), listOf(
                            GeneratedMutableMap.map21625map,
                            GeneratedMutableMap.map21626map,
                            GeneratedMutableMap.map21627map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21446",false,true,false,EIndexPattern.SPO)
                    }(),
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_21455","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#Class>",false,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "#_21455"
                        ), listOf(
                            GeneratedMutableMap.map21641map,
                            GeneratedMutableMap.map21642map,
                            GeneratedMutableMap.map21643map
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","#_21657",false,true,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/simple8.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/str>","str",false,true,false,EIndexPattern.SPO)
                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
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
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("num"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24295map,
                            GeneratedMutableMap.map24296map,
                            GeneratedMutableMap.map24297map,
                            GeneratedMutableMap.map24298map,
                            GeneratedMutableMap.map24299map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s6>","<http://example.org/str>","str1",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map24778map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s6>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map24779map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s7>","<http://example.org/str>","str2",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "str2"
                        ), listOf(
                            GeneratedMutableMap.map24780map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s7>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("str2")
                        ), listOf(
                            GeneratedMutableMap.map24781map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s2","<http://example.org/str>","str2",false,true,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s2"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s1"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
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
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("str"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map27728map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map27729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s4>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map27802map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s4>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map27803map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map27948map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s8>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map27949map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
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
                            GeneratedMutableMap.map28263map,
                            GeneratedMutableMap.map28264map,
                            GeneratedMutableMap.map28265map,
                            GeneratedMutableMap.map28266map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
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
                            GeneratedMutableMap.map28263map,
                            GeneratedMutableMap.map28264map,
                            GeneratedMutableMap.map28265map,
                            GeneratedMutableMap.map28266map
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"b","<http://example.org/str>","s2",false,true,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map32599map,
                            GeneratedMutableMap.map32600map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("v"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map32601map,
                            GeneratedMutableMap.map32602map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example/q>","w",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "s",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32734map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/q>","\"9\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("w"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("w")
                        ), listOf(
                            GeneratedMutableMap.map32735map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "v"
                        ), listOf(
                            GeneratedMutableMap.map32599map,
                            GeneratedMutableMap.map32600map,
                            GeneratedMutableMap.map32881map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example/s1>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s2>","<http://example/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://example/s3>","<http://example/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("v"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v")
                        ), listOf(
                            GeneratedMutableMap.map32601map,
                            GeneratedMutableMap.map32602map,
                            GeneratedMutableMap.map32882map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"o","p2","o2",false,false,false,EIndexPattern.SPO)
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>",true,true,true,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5900map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/p1>"),AOPVariable.calculate("<http://example.org/s2>"),graphName,false)                    }(),
                    LOPValues(listOf(
                        ), listOf(
                            GeneratedMutableMap.map33455map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            GeneratedMutableMap.map33612map,
                            GeneratedMutableMap.map33613map,
                            GeneratedMutableMap.map33614map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://xmlns.com/foaf/0.1/Person>"))
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://xmlns.com/foaf/0.1/givenName>","\"Tom\""))
                        graph.addData(1L,listOf("<http://example.org/tom>","<http://xmlns.com/foaf/0.1/mbox>","<mailto:tom@example.org>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map33615map,
                            GeneratedMutableMap.map33616map,
                            GeneratedMutableMap.map33617map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/move/move-07.ru */ ,
            {
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
                            GeneratedMutableMap.map35131map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map35132map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35133map,
                            GeneratedMutableMap.map35134map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35132map,
                            GeneratedMutableMap.map35137map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map35133map,
                            GeneratedMutableMap.map35258map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map35132map,
                            GeneratedMutableMap.map35260map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
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
                            GeneratedMutableMap.map35131map
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
                            GeneratedMutableMap.map35134map
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
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","y",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map35131map,
                            GeneratedMutableMap.map35479map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map35132map,
                            GeneratedMutableMap.map35137map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map35582map,
                            GeneratedMutableMap.map35583map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35132map,
                            GeneratedMutableMap.map35584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map35582map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35132map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "y"
                        ), listOf(
                            GeneratedMutableMap.map35131map,
                            GeneratedMutableMap.map35759map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ), listOf(
                            GeneratedMutableMap.map35132map,
                            GeneratedMutableMap.map35760map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#q>","l",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "x",
                            "l"
                        ), listOf(
                            GeneratedMutableMap.map35761map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("l"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l")
                        ), listOf(
                            GeneratedMutableMap.map35137map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37191map,
                            GeneratedMutableMap.map37192map,
                            GeneratedMutableMap.map37193map,
                            GeneratedMutableMap.map37194map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.example.orgOrder>"))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37195map,
                            GeneratedMutableMap.map37196map,
                            GeneratedMutableMap.map37197map,
                            GeneratedMutableMap.map37198map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37075"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("#_37075"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/lastName>","L",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37487map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37488map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                        DistributedTripleStore.commit(1L)
                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"P","<http://xmlns.com/foaf/0.1/firstName>","F",false,true,false,EIndexPattern.SPO)
                    }(),
                    POPValues(dictionary, listOf(
                            "P",
                            "F"
                        ), listOf(
                            GeneratedMutableMap.map37489map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    {
                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                        val graph=DistributedTripleStore.createGraph(graphName)
                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                        DistributedTripleStore.commit(1L)
                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                    }(),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("F")
                        ), listOf(
                            GeneratedMutableMap.map37490map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
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
                        LOPTriple(AOPVariable("#_37526"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37526"),graphName,false)                    }(),
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
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
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
                            GeneratedMutableMap.map37998map
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
                            GeneratedMutableMap.map37999map
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
                            GeneratedMutableMap.map38000map
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
                            GeneratedMutableMap.map38001map
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
