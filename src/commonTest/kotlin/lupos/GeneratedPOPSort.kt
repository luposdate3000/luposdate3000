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
                                GeneratedMutableMap.map2796map,
                                GeneratedMutableMap.map2797map,
                                GeneratedMutableMap.map2798map,
                                GeneratedMutableMap.map2799map,
                                GeneratedMutableMap.map2800map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O12",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map2796map,
                            GeneratedMutableMap.map2797map,
                            GeneratedMutableMap.map2798map,
                            GeneratedMutableMap.map2799map,
                            GeneratedMutableMap.map2800map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10293map
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
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10372map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map10374map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map10374map,
                            GeneratedMutableMap.map10372map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10375map
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
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map10374map,
                            GeneratedMutableMap.map10372map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map10374map,
                                GeneratedMutableMap.map10372map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10372map,
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map10374map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10290map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map10292map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10290map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map10292map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10372map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map10374map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10372map,
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map10374map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10646map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map10648map,
                                GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10646map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map10648map,
                                GeneratedMutableMap.map10649map,
                                GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map10518map,
                            GeneratedMutableMap.map10516map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10646map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map10648map,
                                GeneratedMutableMap.map10649map,
                                GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map10648map,
                            GeneratedMutableMap.map10646map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10649map
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
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map10648map,
                                GeneratedMutableMap.map10646map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map10649map
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10516map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map10518map,
                                GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10646map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map10648map,
                                GeneratedMutableMap.map10649map
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
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10791map,
                            GeneratedMutableMap.map10789map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10790map,
                            GeneratedMutableMap.map10792map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map10795map
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
                                GeneratedMutableMap.map10791map,
                                GeneratedMutableMap.map10789map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10790map,
                                GeneratedMutableMap.map10792map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map10795map
                            )
                        )
                    ),
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
                                GeneratedMutableMap.map10789map,
                                GeneratedMutableMap.map10790map,
                                GeneratedMutableMap.map10791map,
                                GeneratedMutableMap.map10792map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10795map
                            )
                        )
                    ),
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
                    POPSort(
                        dictionary,
                        AOPVariable("o"),
                        true,
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10791map,
                            GeneratedMutableMap.map10789map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10790map,
                            GeneratedMutableMap.map10792map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map10795map
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
                            GeneratedMutableMap.map10791map,
                            GeneratedMutableMap.map10789map,
                            GeneratedMutableMap.map10794map,
                            GeneratedMutableMap.map10790map,
                            GeneratedMutableMap.map10792map,
                            GeneratedMutableMap.map10793map,
                            GeneratedMutableMap.map10795map
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
                                GeneratedMutableMap.map10855map,
                                GeneratedMutableMap.map10856map,
                                GeneratedMutableMap.map10857map,
                                GeneratedMutableMap.map10858map,
                                GeneratedMutableMap.map10859map,
                                GeneratedMutableMap.map10860map,
                                GeneratedMutableMap.map10861map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10857map,
                            GeneratedMutableMap.map10855map,
                            GeneratedMutableMap.map10860map,
                            GeneratedMutableMap.map10856map,
                            GeneratedMutableMap.map10858map,
                            GeneratedMutableMap.map10859map,
                            GeneratedMutableMap.map10861map
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
                            GeneratedMutableMap.map10857map,
                            GeneratedMutableMap.map10855map,
                            GeneratedMutableMap.map10860map,
                            GeneratedMutableMap.map10856map,
                            GeneratedMutableMap.map10858map,
                            GeneratedMutableMap.map10859map,
                            GeneratedMutableMap.map10861map
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
                                GeneratedMutableMap.map10791map,
                                GeneratedMutableMap.map10789map,
                                GeneratedMutableMap.map10794map,
                                GeneratedMutableMap.map10790map,
                                GeneratedMutableMap.map10792map,
                                GeneratedMutableMap.map10793map,
                                GeneratedMutableMap.map10795map
                            )
                        )
                    ),
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
                    LOPSort(
                        true,
                        AOPVariable("p"),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map10857map,
                                GeneratedMutableMap.map10855map,
                                GeneratedMutableMap.map10860map,
                                GeneratedMutableMap.map10856map,
                                GeneratedMutableMap.map10858map,
                                GeneratedMutableMap.map10859map,
                                GeneratedMutableMap.map10861map
                            )
                        )
                    ),
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
                    POPSort(
                        dictionary,
                        AOPVariable("s"),
                        true,
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
                    ),
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
                    LOPSort(
                        true,
                        AOPVariable("s"),
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
                    ),
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
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map
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
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map
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
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map
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
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map
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
                                GeneratedMutableMap.map27336map,
                                GeneratedMutableMap.map27337map,
                                GeneratedMutableMap.map27338map,
                                GeneratedMutableMap.map27341map,
                                GeneratedMutableMap.map27342map,
                                GeneratedMutableMap.map27343map,
                                GeneratedMutableMap.map27339map,
                                GeneratedMutableMap.map27340map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map
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
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27340map
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
                                GeneratedMutableMap.map27344map,
                                GeneratedMutableMap.map27345map,
                                GeneratedMutableMap.map27346map,
                                GeneratedMutableMap.map27349map,
                                GeneratedMutableMap.map27350map,
                                GeneratedMutableMap.map27351map,
                                GeneratedMutableMap.map27347map,
                                GeneratedMutableMap.map27348map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map
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
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27348map
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
                                GeneratedMutableMap.map27337map,
                                GeneratedMutableMap.map27338map,
                                GeneratedMutableMap.map27336map,
                                GeneratedMutableMap.map27341map,
                                GeneratedMutableMap.map27342map,
                                GeneratedMutableMap.map27343map,
                                GeneratedMutableMap.map27339map,
                                GeneratedMutableMap.map27340map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27340map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27337map
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
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27339map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27340map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27337map
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
                                GeneratedMutableMap.map27345map,
                                GeneratedMutableMap.map27346map,
                                GeneratedMutableMap.map27344map,
                                GeneratedMutableMap.map27349map,
                                GeneratedMutableMap.map27350map,
                                GeneratedMutableMap.map27351map,
                                GeneratedMutableMap.map27347map,
                                GeneratedMutableMap.map27348map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27348map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27345map
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
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27347map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27348map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27345map
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
                    LOPSort(
                        true,
                        AOPVariable("sum"),
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
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27343map
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
                            GeneratedMutableMap.map27337map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27343map
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27351map
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
                            GeneratedMutableMap.map27345map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27351map
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
                                GeneratedMutableMap.map27337map,
                                GeneratedMutableMap.map27338map,
                                GeneratedMutableMap.map27336map,
                                GeneratedMutableMap.map27341map,
                                GeneratedMutableMap.map27342map,
                                GeneratedMutableMap.map27634map,
                                GeneratedMutableMap.map27635map,
                                GeneratedMutableMap.map27343map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27337map
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
                            GeneratedMutableMap.map27341map,
                            GeneratedMutableMap.map27634map,
                            GeneratedMutableMap.map27342map,
                            GeneratedMutableMap.map27343map,
                            GeneratedMutableMap.map27635map,
                            GeneratedMutableMap.map27336map,
                            GeneratedMutableMap.map27338map,
                            GeneratedMutableMap.map27337map
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
                                GeneratedMutableMap.map27345map,
                                GeneratedMutableMap.map27346map,
                                GeneratedMutableMap.map27344map,
                                GeneratedMutableMap.map27349map,
                                GeneratedMutableMap.map27350map,
                                GeneratedMutableMap.map27636map,
                                GeneratedMutableMap.map27637map,
                                GeneratedMutableMap.map27351map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27345map
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
                            GeneratedMutableMap.map27349map,
                            GeneratedMutableMap.map27636map,
                            GeneratedMutableMap.map27350map,
                            GeneratedMutableMap.map27351map,
                            GeneratedMutableMap.map27637map,
                            GeneratedMutableMap.map27344map,
                            GeneratedMutableMap.map27346map,
                            GeneratedMutableMap.map27345map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map33159map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map33160map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map33160map,
                            GeneratedMutableMap.map33159map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10293map
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
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map33160map,
                            GeneratedMutableMap.map33159map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map33161map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map33162map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map33162map,
                            GeneratedMutableMap.map33161map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10375map
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
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map33162map,
                            GeneratedMutableMap.map33161map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map33160map,
                                GeneratedMutableMap.map33159map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map33159map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map33160map,
                            GeneratedMutableMap.map10293map
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map33159map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map33160map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map33162map,
                                GeneratedMutableMap.map33161map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map33161map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map33162map,
                            GeneratedMutableMap.map10375map
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
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map33161map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map33162map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10288map,
                                GeneratedMutableMap.map33159map,
                                GeneratedMutableMap.map10289map,
                                GeneratedMutableMap.map10291map,
                                GeneratedMutableMap.map33160map,
                                GeneratedMutableMap.map10293map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10288map,
                            GeneratedMutableMap.map10289map,
                            GeneratedMutableMap.map33159map,
                            GeneratedMutableMap.map10291map,
                            GeneratedMutableMap.map33160map,
                            GeneratedMutableMap.map10293map
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
                                GeneratedMutableMap.map10370map,
                                GeneratedMutableMap.map33161map,
                                GeneratedMutableMap.map10371map,
                                GeneratedMutableMap.map10373map,
                                GeneratedMutableMap.map33162map,
                                GeneratedMutableMap.map10375map
                            )
                        )
                    ),
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
                            GeneratedMutableMap.map10370map,
                            GeneratedMutableMap.map10371map,
                            GeneratedMutableMap.map33161map,
                            GeneratedMutableMap.map10373map,
                            GeneratedMutableMap.map33162map,
                            GeneratedMutableMap.map10375map
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map33325map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map33326map,
                                GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map33327map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map33328map,
                                GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map33325map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map33326map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map10519map,
                            GeneratedMutableMap.map10514map
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
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map33327map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map33328map,
                                GeneratedMutableMap.map10649map,
                                GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map10649map,
                            GeneratedMutableMap.map10644map
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
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map33325map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map33326map,
                                GeneratedMutableMap.map10519map,
                                GeneratedMutableMap.map10514map
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
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10517map,
                            GeneratedMutableMap.map33326map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10515map,
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map33327map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map33328map,
                                GeneratedMutableMap.map10649map,
                                GeneratedMutableMap.map10644map
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
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10647map,
                            GeneratedMutableMap.map33328map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10645map,
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map10649map
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
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map33326map,
                                GeneratedMutableMap.map33325map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map10519map
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10515map,
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
                            GeneratedMutableMap.map10514map,
                            GeneratedMutableMap.map33325map,
                            GeneratedMutableMap.map10515map,
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
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map33328map,
                                GeneratedMutableMap.map33327map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map10649map
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10645map,
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
                            GeneratedMutableMap.map10644map,
                            GeneratedMutableMap.map33327map,
                            GeneratedMutableMap.map10645map,
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
                                GeneratedMutableMap.map10514map,
                                GeneratedMutableMap.map33325map,
                                GeneratedMutableMap.map10515map,
                                GeneratedMutableMap.map10517map,
                                GeneratedMutableMap.map33326map,
                                GeneratedMutableMap.map10519map
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
                                GeneratedMutableMap.map10644map,
                                GeneratedMutableMap.map33327map,
                                GeneratedMutableMap.map10645map,
                                GeneratedMutableMap.map10647map,
                                GeneratedMutableMap.map33328map,
                                GeneratedMutableMap.map10649map
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
                    POPSort(
                        dictionary,
                        AOPVariable("sum"),
                        true,
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map35480map,
                                GeneratedMutableMap.map35481map
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
                    LOPSort(
                        true,
                        AOPVariable("sum"),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map35139map,
                                GeneratedMutableMap.map35482map
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
                    POPSort(
                        dictionary,
                        AOPVariable("O"),
                        true,
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37191map,
                                GeneratedMutableMap.map37192map,
                                GeneratedMutableMap.map37193map,
                                GeneratedMutableMap.map37194map
                            )
                        )
                    ),
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
                    LOPSort(
                        true,
                        AOPVariable("O"),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37195map,
                                GeneratedMutableMap.map37196map,
                                GeneratedMutableMap.map37197map,
                                GeneratedMutableMap.map37198map
                            )
                        )
                    ),
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
                    POPSort(
                        dictionary,
                        AOPVariable("L"),
                        true,
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
                    ),
                    POPValues(dictionary, listOf(
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37246map,
                            GeneratedMutableMap.map37243map
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
                            "#_37075",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37241map,
                            GeneratedMutableMap.map37244map,
                            GeneratedMutableMap.map37245map,
                            GeneratedMutableMap.map37242map,
                            GeneratedMutableMap.map37246map,
                            GeneratedMutableMap.map37243map
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37256map,
                            GeneratedMutableMap.map37253map
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
                                                                        LOPTriple(AOPVariable("#_37075"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37075"),graphName,false)                                                                    }()
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
                            AOPVariable("#_37075"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37251map,
                            GeneratedMutableMap.map37254map,
                            GeneratedMutableMap.map37255map,
                            GeneratedMutableMap.map37252map,
                            GeneratedMutableMap.map37256map,
                            GeneratedMutableMap.map37253map
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
                    ),
                    POPValues(dictionary, listOf(
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37677map,
                            GeneratedMutableMap.map37674map
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
                            "#_37526",
                            "L",
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37672map,
                            GeneratedMutableMap.map37675map,
                            GeneratedMutableMap.map37676map,
                            GeneratedMutableMap.map37673map,
                            GeneratedMutableMap.map37677map,
                            GeneratedMutableMap.map37674map
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37697map,
                            GeneratedMutableMap.map37694map
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
                                                                        LOPTriple(AOPVariable("#_37526"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37526"),graphName,false)                                                                    }()
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
                            AOPVariable("#_37526"),
                            AOPVariable("L"),
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37692map,
                            GeneratedMutableMap.map37695map,
                            GeneratedMutableMap.map37696map,
                            GeneratedMutableMap.map37693map,
                            GeneratedMutableMap.map37697map,
                            GeneratedMutableMap.map37694map
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
