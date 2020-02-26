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


class GeneratedPOPRenameTest {
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
                        POPRename(
                                dictionary,
                                AOPVariable("s"),
                                AOPVariable("s"),
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
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map9731map,
                                GeneratedMutableMap.map9732map,
                                GeneratedMutableMap.map9733map,
                                GeneratedMutableMap.map9734map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("s"),
                                AOPVariable("s"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                    DistributedTripleStore.commit(1L)
                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                }()

                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "o",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map9731map,
                                GeneratedMutableMap.map9732map,
                                GeneratedMutableMap.map9733map,
                                GeneratedMutableMap.map9734map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("s"),
                                AOPVariable("s"),
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
                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map9735map,
                                GeneratedMutableMap.map9736map,
                                GeneratedMutableMap.map9737map,
                                GeneratedMutableMap.map9738map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("s"),
                                AOPVariable("s"),
                                {
                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                    val graph = DistributedTripleStore.createGraph(graphName)
                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                    DistributedTripleStore.commit(1L)
                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                }()

                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map9735map,
                                GeneratedMutableMap.map9736map,
                                GeneratedMutableMap.map9737map,
                                GeneratedMutableMap.map9738map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("p"),
                                AOPVariable("p"),
                                POPValues(dictionary, listOf(
                                        "p",
                                        "o",
                                        "s"
                                ), listOf(
                                        GeneratedMutableMap.map9731map,
                                        GeneratedMutableMap.map9732map,
                                        GeneratedMutableMap.map9733map,
                                        GeneratedMutableMap.map9734map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map9739map,
                                GeneratedMutableMap.map9740map,
                                GeneratedMutableMap.map9741map,
                                GeneratedMutableMap.map9742map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("p"),
                                AOPVariable("p"),
                                POPRename(
                                        dictionary,
                                        AOPVariable("s"),
                                        AOPVariable("s"),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                            DistributedTripleStore.commit(1L)
                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                        }()

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "o",
                                "s",
                                "p"
                        ), listOf(
                                GeneratedMutableMap.map9739map,
                                GeneratedMutableMap.map9740map,
                                GeneratedMutableMap.map9741map,
                                GeneratedMutableMap.map9742map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("p"),
                                AOPVariable("p"),
                                LOPValues(listOf(
                                        AOPVariable("p"),
                                        AOPVariable("o"),
                                        AOPVariable("s")
                                ), listOf(
                                        GeneratedMutableMap.map9735map,
                                        GeneratedMutableMap.map9736map,
                                        GeneratedMutableMap.map9737map,
                                        GeneratedMutableMap.map9738map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("s"),
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map9743map,
                                GeneratedMutableMap.map9744map,
                                GeneratedMutableMap.map9745map,
                                GeneratedMutableMap.map9746map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("p"),
                                AOPVariable("p"),
                                LOPRename(
                                        AOPVariable("s"),
                                        AOPVariable("s"),
                                        {
                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                            val graph = DistributedTripleStore.createGraph(graphName)
                                            graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                            graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                            graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                            DistributedTripleStore.commit(1L)
                                            LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                        }()

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("o"),
                                AOPVariable("s"),
                                AOPVariable("p")
                        ), listOf(
                                GeneratedMutableMap.map9743map,
                                GeneratedMutableMap.map9744map,
                                GeneratedMutableMap.map9745map,
                                GeneratedMutableMap.map9746map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/construct/constructwhere01.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("o"),
                                AOPVariable("o"),
                                POPValues(dictionary, listOf(
                                        "o",
                                        "s",
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map9739map,
                                        GeneratedMutableMap.map9740map,
                                        GeneratedMutableMap.map9741map,
                                        GeneratedMutableMap.map9742map
                                )
                                )
                        ),
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
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("o"),
                                AOPVariable("o"),
                                POPRename(
                                        dictionary,
                                        AOPVariable("p"),
                                        AOPVariable("p"),
                                        POPRename(
                                                dictionary,
                                                AOPVariable("s"),
                                                AOPVariable("s"),
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                                    DistributedTripleStore.commit(1L)
                                                    TripleStoreIteratorGlobal(1L, dictionary, graphName, "s", "p", "o", false, false, false, EIndexPattern.SPO)
                                                }()

                                        )

                                )

                        ),
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
                        LOPRename(
                                AOPVariable("o"),
                                AOPVariable("o"),
                                LOPValues(listOf(
                                        AOPVariable("o"),
                                        AOPVariable("s"),
                                        AOPVariable("p")
                                ), listOf(
                                        GeneratedMutableMap.map9743map,
                                        GeneratedMutableMap.map9744map,
                                        GeneratedMutableMap.map9745map,
                                        GeneratedMutableMap.map9746map
                                )
                                )
                        ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("o"),
                                AOPVariable("o"),
                                LOPRename(
                                        AOPVariable("p"),
                                        AOPVariable("p"),
                                        LOPRename(
                                                AOPVariable("s"),
                                                AOPVariable("s"),
                                                {
                                                    val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                    val graph = DistributedTripleStore.createGraph(graphName)
                                                    graph.addData(1L, listOf("<http://example.org/s1>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o1>"))
                                                    graph.addData(1L, listOf("<http://example.org/s2>", "<http://example.org/p>", "<http://example.org/o2>"))
                                                    graph.addData(1L, listOf("<http://example.org/s3>", "<http://example.org/p>", "<http://example.org/o3>"))
                                                    DistributedTripleStore.commit(1L)
                                                    LOPTriple(AOPVariable("s"), AOPVariable("p"), AOPVariable("o"), graphName, false)
                                                }()

                                        )

                                )

                        ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("s"),
                                AOPVariable("P"),
                                POPValues(dictionary, listOf(
                                        "P",
                                        "FullName"
                                ), listOf(
                                        GeneratedMutableMap.map37586map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map37588map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                                            DistributedTripleStore.commit(1L)
                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "FullName",
                                "s"
                        ), listOf(
                                GeneratedMutableMap.map37588map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("s"),
                                AOPVariable("P"),
                                LOPValues(listOf(
                                        AOPVariable("P"),
                                        AOPVariable("FullName")
                                ), listOf(
                                        GeneratedMutableMap.map37587map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map37589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
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
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"), AOPVariable("F"), graphName, false)
                                                        }()
                                                        ,
                                                        {
                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                                            DistributedTripleStore.commit(1L)
                                                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"), AOPVariable("L"), graphName, false)
                                                        }()
                                                        ,
                                                        false)

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("FullName"),
                                AOPVariable("s")
                        ), listOf(
                                GeneratedMutableMap.map37589map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("o"),
                                AOPVariable("FullName"),
                                POPValues(dictionary, listOf(
                                        "FullName",
                                        "s",
                                        "p"
                                ), listOf(
                                        GeneratedMutableMap.map37590map
                                )
                                )
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map37592map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                        dictionary,
                        POPRename(
                                dictionary,
                                AOPVariable("o"),
                                AOPVariable("FullName"),
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/firstName>", "F", false, true, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                                                            DistributedTripleStore.commit(1L)
                                                                            TripleStoreIteratorGlobal(1L, dictionary, graphName, "P", "<http://xmlns.com/foaf/0.1/lastName>", "L", false, true, false, EIndexPattern.SPO)
                                                                        }()
                                                                        ,
                                                                        false)

                                                        )

                                                )

                                        )

                                )

                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                        ), listOf(
                                GeneratedMutableMap.map37592map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("o"),
                                AOPVariable("FullName"),
                                LOPValues(listOf(
                                        AOPVariable("FullName"),
                                        AOPVariable("s"),
                                        AOPVariable("p")
                                ), listOf(
                                        GeneratedMutableMap.map37591map
                                )
                                )
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map37593map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
            {
                val dictionary = ResultSetDictionary()
                MicroTestLN(
                        dictionary,
                        LOPRename(
                                AOPVariable("o"),
                                AOPVariable("FullName"),
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
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/firstName>", "\"John\""))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"), AOPVariable("F"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        {
                                                                            val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                            val graph = DistributedTripleStore.createGraph(graphName)
                                                                            graph.addData(1L, listOf("<http://p1>", "<http://xmlns.com/foaf/0.1/lastName>", "\"Doe\""))
                                                                            DistributedTripleStore.commit(1L)
                                                                            LOPTriple(AOPVariable("P"), AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"), AOPVariable("L"), graphName, false)
                                                                        }()
                                                                        ,
                                                                        false)

                                                        )

                                                )

                                        )

                                )

                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                        ), listOf(
                                GeneratedMutableMap.map37593map
                        )
                        )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */,
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
