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


class GeneratedPOPSortTest {
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
                    POPSort(
                        dictionary,
                        AOPVariable("O12"),
                        true,
                        POPValues(dictionary, listOf(
                                "O12",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map2794map,
                                GeneratedMutableMap.map2795map,
                                GeneratedMutableMap.map2796map,
                                GeneratedMutableMap.map2797map,
                                GeneratedMutableMap.map2798map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O12",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map2794map,
                            GeneratedMutableMap.map2795map,
                            GeneratedMutableMap.map2796map,
                            GeneratedMutableMap.map2797map,
                            GeneratedMutableMap.map2798map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg08b.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map10365map,
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map10367map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
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

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map10367map,
                                GeneratedMutableMap.map10365map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o"),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10283map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map10285map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
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

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10283map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map10285map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map10365map,
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map10367map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10365map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map10367map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
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
                                        true                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o2"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10603map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o2"),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()
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
                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                    }()
,
                                        true                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o2"),
                                        true,
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
                                                        true                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p2"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10603map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10601map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p2"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o2"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                    }()
,
                                                        true                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p2"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o2"),
                                                        true,
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
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10603map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10601map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p2"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o2"),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                    }()
,
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("p2"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("o2"),
                                                                        true,
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
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10603map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("p2"),
                                                                    LOPSort(
                                                                        true,
                                                                        AOPVariable("o2"),
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                    }()
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
                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                    }()
,
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10473map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map10475map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("p2"),
                                                                        true,
                                                                                    POPSort(
                                                                                        dictionary,
                                                                                        AOPVariable("o2"),
                                                                                        true,
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
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10473map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map10475map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10603map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map10605map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o"),
                                                                    LOPSort(
                                                                        true,
                                                                        AOPVariable("p2"),
                                                                                    LOPSort(
                                                                                        true,
                                                                                        AOPVariable("o2"),
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                                    }()
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
                                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                                    }()
,
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10603map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map10605map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10732map,
                                GeneratedMutableMap.map10733map,
                                GeneratedMutableMap.map10734map,
                                GeneratedMutableMap.map10735map,
                                GeneratedMutableMap.map10736map,
                                GeneratedMutableMap.map10737map,
                                GeneratedMutableMap.map10738map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
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
                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10734map,
                                GeneratedMutableMap.map10732map,
                                GeneratedMutableMap.map10737map,
                                GeneratedMutableMap.map10733map,
                                GeneratedMutableMap.map10735map,
                                GeneratedMutableMap.map10736map,
                                GeneratedMutableMap.map10738map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o"),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10666map,
                                GeneratedMutableMap.map10667map,
                                GeneratedMutableMap.map10668map,
                                GeneratedMutableMap.map10669map,
                                GeneratedMutableMap.map10670map,
                                GeneratedMutableMap.map10671map,
                                GeneratedMutableMap.map10672map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
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
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10666map,
                            GeneratedMutableMap.map10667map,
                            GeneratedMutableMap.map10668map,
                            GeneratedMutableMap.map10669map,
                            GeneratedMutableMap.map10670map,
                            GeneratedMutableMap.map10671map,
                            GeneratedMutableMap.map10672map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10732map,
                                GeneratedMutableMap.map10733map,
                                GeneratedMutableMap.map10734map,
                                GeneratedMutableMap.map10735map,
                                GeneratedMutableMap.map10736map,
                                GeneratedMutableMap.map10737map,
                                GeneratedMutableMap.map10738map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10732map,
                            GeneratedMutableMap.map10733map,
                            GeneratedMutableMap.map10734map,
                            GeneratedMutableMap.map10735map,
                            GeneratedMutableMap.map10736map,
                            GeneratedMutableMap.map10737map,
                            GeneratedMutableMap.map10738map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/csv-tsv-res/csvtsv01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27198map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27198map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map,
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27198map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("sum"),
                                        true,
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
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27198map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("y"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map,
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("y"),
                                    LOPSort(
                                        true,
                                        AOPVariable("sum"),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27206map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map,
                                GeneratedMutableMap.map27197map,
                                GeneratedMutableMap.map27198map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27198map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27195map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("y"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        true,
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
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27197map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27198map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27195map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("x"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map,
                                GeneratedMutableMap.map27205map,
                                GeneratedMutableMap.map27206map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27206map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27203map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("x"),
                                    LOPSort(
                                        true,
                                        AOPVariable("y"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("sum"),
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                                    }()
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27205map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27206map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27203map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27474map,
                                GeneratedMutableMap.map27475map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27476map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27474map,
                                GeneratedMutableMap.map27475map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27201map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27201map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("y"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("sum"),
                                        true,
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
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27195map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27201map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("y"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27476map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27209map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27209map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("y"),
                                    LOPSort(
                                        true,
                                        AOPVariable("sum"),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27203map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27209map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map27195map,
                                GeneratedMutableMap.map27196map,
                                GeneratedMutableMap.map27194map,
                                GeneratedMutableMap.map27199map,
                                GeneratedMutableMap.map27200map,
                                GeneratedMutableMap.map27474map,
                                GeneratedMutableMap.map27475map,
                                GeneratedMutableMap.map27201map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27195map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("x"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("y"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("sum"),
                                                        true,
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
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27199map,
                            GeneratedMutableMap.map27474map,
                            GeneratedMutableMap.map27200map,
                            GeneratedMutableMap.map27201map,
                            GeneratedMutableMap.map27475map,
                            GeneratedMutableMap.map27194map,
                            GeneratedMutableMap.map27196map,
                            GeneratedMutableMap.map27195map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("x"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map27203map,
                                GeneratedMutableMap.map27204map,
                                GeneratedMutableMap.map27202map,
                                GeneratedMutableMap.map27207map,
                                GeneratedMutableMap.map27208map,
                                GeneratedMutableMap.map27476map,
                                GeneratedMutableMap.map27477map,
                                GeneratedMutableMap.map27209map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27203map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("x"),
                                    LOPSort(
                                        true,
                                        AOPVariable("y"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("sum"),
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                                    }()
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                                    }()
,
                                                                                        false                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27207map,
                            GeneratedMutableMap.map27476map,
                            GeneratedMutableMap.map27208map,
                            GeneratedMutableMap.map27209map,
                            GeneratedMutableMap.map27477map,
                            GeneratedMutableMap.map27202map,
                            GeneratedMutableMap.map27204map,
                            GeneratedMutableMap.map27203map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map32979map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map32980map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map32981map,
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map32982map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
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

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map32980map,
                                GeneratedMutableMap.map32979map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map32982map,
                                GeneratedMutableMap.map32981map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o"),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map10281map,
                                GeneratedMutableMap.map32979map,
                                GeneratedMutableMap.map10282map,
                                GeneratedMutableMap.map10284map,
                                GeneratedMutableMap.map32980map,
                                GeneratedMutableMap.map10286map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
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

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10281map,
                            GeneratedMutableMap.map10282map,
                            GeneratedMutableMap.map32979map,
                            GeneratedMutableMap.map10284map,
                            GeneratedMutableMap.map32980map,
                            GeneratedMutableMap.map10286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10363map,
                                GeneratedMutableMap.map32981map,
                                GeneratedMutableMap.map10364map,
                                GeneratedMutableMap.map10366map,
                                GeneratedMutableMap.map32982map,
                                GeneratedMutableMap.map10368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10363map,
                            GeneratedMutableMap.map10364map,
                            GeneratedMutableMap.map32981map,
                            GeneratedMutableMap.map10366map,
                            GeneratedMutableMap.map32982map,
                            GeneratedMutableMap.map10368map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map33109map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map33110map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o2"),
                        true,
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
                                        true                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o2"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map33111map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map33112map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o2"),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()
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
                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                    }()
,
                                        true                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map33109map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map33110map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p2"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o2"),
                                        true,
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
                                                        true                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map,
                            GeneratedMutableMap.map10471map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p2"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map33111map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map33112map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10601map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p2"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o2"),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                    }()
,
                                                        true                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map,
                            GeneratedMutableMap.map10601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map33109map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map33110map,
                                GeneratedMutableMap.map10476map,
                                GeneratedMutableMap.map10471map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p2"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o2"),
                                                        true,
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
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map33111map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map33112map,
                                GeneratedMutableMap.map10606map,
                                GeneratedMutableMap.map10601map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("o"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p2"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o2"),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                    }()
,
                                                                        true                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map33110map,
                                GeneratedMutableMap.map33109map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("p"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("o"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("p2"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("o2"),
                                                                        true,
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
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map33112map,
                                GeneratedMutableMap.map33111map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("p"),
                                    LOPSort(
                                        true,
                                        AOPVariable("o"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("p2"),
                                                                    LOPSort(
                                                                        true,
                                                                        AOPVariable("o2"),
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
                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                    }()
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
                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                    }()
,
                                                                                        true                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "p2",
                                "o2"
                            ), listOf(
                                GeneratedMutableMap.map10471map,
                                GeneratedMutableMap.map33109map,
                                GeneratedMutableMap.map10472map,
                                GeneratedMutableMap.map10474map,
                                GeneratedMutableMap.map33110map,
                                GeneratedMutableMap.map10476map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
                                    POPSort(
                                        dictionary,
                                        AOPVariable("p"),
                                        true,
                                                    POPSort(
                                                        dictionary,
                                                        AOPVariable("o"),
                                                        true,
                                                                    POPSort(
                                                                        dictionary,
                                                                        AOPVariable("p2"),
                                                                        true,
                                                                                    POPSort(
                                                                                        dictionary,
                                                                                        AOPVariable("o2"),
                                                                                        true,
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
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "p2",
                            "o2"
                        ), listOf(
                            GeneratedMutableMap.map10471map,
                            GeneratedMutableMap.map10472map,
                            GeneratedMutableMap.map33109map,
                            GeneratedMutableMap.map10474map,
                            GeneratedMutableMap.map33110map,
                            GeneratedMutableMap.map10476map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("p2"),
                                AOPVariable("o2")
                            ), listOf(
                                GeneratedMutableMap.map10601map,
                                GeneratedMutableMap.map33111map,
                                GeneratedMutableMap.map10602map,
                                GeneratedMutableMap.map10604map,
                                GeneratedMutableMap.map33112map,
                                GeneratedMutableMap.map10606map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("s"),
                                    LOPSort(
                                        true,
                                        AOPVariable("p"),
                                                    LOPSort(
                                                        true,
                                                        AOPVariable("o"),
                                                                    LOPSort(
                                                                        true,
                                                                        AOPVariable("p2"),
                                                                                    LOPSort(
                                                                                        true,
                                                                                        AOPVariable("o2"),
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                                    }()
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
                                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                                    }()
,
                                                                                                        true                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map10601map,
                            GeneratedMutableMap.map10602map,
                            GeneratedMutableMap.map33111map,
                            GeneratedMutableMap.map10604map,
                            GeneratedMutableMap.map33112map,
                            GeneratedMutableMap.map10606map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map35184map,
                                GeneratedMutableMap.map35185map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35184map,
                            GeneratedMutableMap.map35185map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
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

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35184map,
                            GeneratedMutableMap.map35185map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map34843map,
                                GeneratedMutableMap.map35186map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map34843map,
                            GeneratedMutableMap.map35186map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("sum"),
                                    LOPBind(
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("y"), AOPVariable("y")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map34843map,
                            GeneratedMutableMap.map35186map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map36887map,
                                GeneratedMutableMap.map36888map,
                                GeneratedMutableMap.map36889map,
                                GeneratedMutableMap.map36890map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36887map,
                            GeneratedMutableMap.map36888map,
                            GeneratedMutableMap.map36889map,
                            GeneratedMutableMap.map36890map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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

                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36887map,
                            GeneratedMutableMap.map36888map,
                            GeneratedMutableMap.map36889map,
                            GeneratedMutableMap.map36890map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("O"),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map36891map,
                                GeneratedMutableMap.map36892map,
                                GeneratedMutableMap.map36893map,
                                GeneratedMutableMap.map36894map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36891map,
                            GeneratedMutableMap.map36892map,
                            GeneratedMutableMap.map36893map,
                            GeneratedMutableMap.map36894map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36891map,
                            GeneratedMutableMap.map36892map,
                            GeneratedMutableMap.map36893map,
                            GeneratedMutableMap.map36894map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#_36771",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map36925map,
                                GeneratedMutableMap.map36926map,
                                GeneratedMutableMap.map36927map,
                                GeneratedMutableMap.map36928map,
                                GeneratedMutableMap.map36929map,
                                GeneratedMutableMap.map36930map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_36771",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36925map,
                            GeneratedMutableMap.map36928map,
                            GeneratedMutableMap.map36929map,
                            GeneratedMutableMap.map36926map,
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36927map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_36753","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_36754","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_36755","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_36756","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_36757","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_36771","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36748"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36749"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36750"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36751"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36752"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36753"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36754"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36755"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36756"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36757"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_36771",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "#_36771",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map36925map,
                            GeneratedMutableMap.map36928map,
                            GeneratedMutableMap.map36929map,
                            GeneratedMutableMap.map36926map,
                            GeneratedMutableMap.map36930map,
                            GeneratedMutableMap.map36927map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("L"),
                        LOPValues(listOf(
                                AOPVariable("#_36771"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map36945map,
                                GeneratedMutableMap.map36946map,
                                GeneratedMutableMap.map36947map,
                                GeneratedMutableMap.map36948map,
                                GeneratedMutableMap.map36949map,
                                GeneratedMutableMap.map36950map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("#_36771"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36945map,
                            GeneratedMutableMap.map36948map,
                            GeneratedMutableMap.map36949map,
                            GeneratedMutableMap.map36946map,
                            GeneratedMutableMap.map36950map,
                            GeneratedMutableMap.map36947map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("L"),
                                    LOPJoin(
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_36748","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_36749","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_36750","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_36751","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_36752","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_36753","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_36754","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_36755","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_36756","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_36757","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("#_36771"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36748"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36749"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_36750"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36751"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36752"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_36753"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36754"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_36755"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36756"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_36757"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_36771"),graphName,false)                                                                    }()
,
                                                        false                                                    )
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
                                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                                    }()

                                                                                                    )

                                                                                    )

                                                                    )

                                                    )
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("#_36771"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map36945map,
                            GeneratedMutableMap.map36948map,
                            GeneratedMutableMap.map36949map,
                            GeneratedMutableMap.map36946map,
                            GeneratedMutableMap.map36950map,
                            GeneratedMutableMap.map36947map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                        POPValues(dictionary, listOf(
                                "#_37186",
                                "L",
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37332map,
                                GeneratedMutableMap.map37333map,
                                GeneratedMutableMap.map37334map,
                                GeneratedMutableMap.map37335map,
                                GeneratedMutableMap.map37336map,
                                GeneratedMutableMap.map37337map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "#_37186",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37332map,
                            GeneratedMutableMap.map37335map,
                            GeneratedMutableMap.map37336map,
                            GeneratedMutableMap.map37333map,
                            GeneratedMutableMap.map37337map,
                            GeneratedMutableMap.map37334map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
                                    POPJoinHashMap(
                                        dictionary,
                                                    POPJoinHashMap(
                                                        dictionary,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_37168","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_37169","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_37170","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_37171","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_37172","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"#_37186","<http://www.w3.org/2000/01/rdf-schema#label>","L",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37163"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37164"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37165"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37166"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37167"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37168"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37169"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37170"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37171"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37172"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"O","<http://www.example.orghasItem>","#_37186",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "#_37186",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37332map,
                            GeneratedMutableMap.map37335map,
                            GeneratedMutableMap.map37336map,
                            GeneratedMutableMap.map37333map,
                            GeneratedMutableMap.map37337map,
                            GeneratedMutableMap.map37334map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("L"),
                        LOPValues(listOf(
                                AOPVariable("#_37186"),
                                AOPVariable("L"),
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37352map,
                                GeneratedMutableMap.map37353map,
                                GeneratedMutableMap.map37354map,
                                GeneratedMutableMap.map37355map,
                                GeneratedMutableMap.map37356map,
                                GeneratedMutableMap.map37357map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37186"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37352map,
                            GeneratedMutableMap.map37355map,
                            GeneratedMutableMap.map37356map,
                            GeneratedMutableMap.map37353map,
                            GeneratedMutableMap.map37357map,
                            GeneratedMutableMap.map37354map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPSort(
                        true,
                        AOPVariable("L"),
                                    LOPJoin(
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("_:_37163","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_37164","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pizza\""))
                                                                        graph.addData(1L,listOf("_:_37165","<http://www.w3.org/2000/01/rdf-schema#label>","\"Wine\""))
                                                                        graph.addData(1L,listOf("_:_37166","<http://www.w3.org/2000/01/rdf-schema#label>","\"Ice Cream\""))
                                                                        graph.addData(1L,listOf("_:_37167","<http://www.w3.org/2000/01/rdf-schema#label>","\"Pasta\""))
                                                                        graph.addData(1L,listOf("_:_37168","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_37169","<http://www.w3.org/2000/01/rdf-schema#label>","\"Sandwich\""))
                                                                        graph.addData(1L,listOf("_:_37170","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        graph.addData(1L,listOf("_:_37171","<http://www.w3.org/2000/01/rdf-schema#label>","\"Bagel\""))
                                                                        graph.addData(1L,listOf("_:_37172","<http://www.w3.org/2000/01/rdf-schema#label>","\"Soft Drink\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("#_37186"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37163"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37164"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder1>","<http://www.example.orghasItem>","_:_37165"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37166"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37167"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder2>","<http://www.example.orghasItem>","_:_37168"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37169"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder3>","<http://www.example.orghasItem>","_:_37170"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37171"))
                                                                        graph.addData(1L,listOf("<http://www.example.orgorder4>","<http://www.example.orghasItem>","_:_37172"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37186"),graphName,false)                                                                    }()
,
                                                        false                                                    )
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
                                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                                    }()

                                                                                                    )

                                                                                    )

                                                                    )

                                                    )
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37186"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37352map,
                            GeneratedMutableMap.map37355map,
                            GeneratedMutableMap.map37356map,
                            GeneratedMutableMap.map37353map,
                            GeneratedMutableMap.map37357map,
                            GeneratedMutableMap.map37354map
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
