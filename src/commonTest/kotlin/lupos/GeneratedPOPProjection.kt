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


class GeneratedPOPProjectionTest {
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map988map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map988map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map1140map,
                                GeneratedMutableMap.map1141map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map1140map,
                            GeneratedMutableMap.map1141map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f1185",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map1419map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map1140map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "#f1745",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map1966map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map988map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "#f2006",
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map2238map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "C"
                        ), listOf(
                            GeneratedMutableMap.map1140map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O12"),
                            AOPVariable("C")
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("sum")
                        ),
                        POPValues(dictionary, listOf(
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map3308map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map3308map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("sum")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map3494map,
                                GeneratedMutableMap.map3495map,
                                GeneratedMutableMap.map3496map,
                                GeneratedMutableMap.map3497map,
                                GeneratedMutableMap.map3498map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map3494map,
                            GeneratedMutableMap.map3495map,
                            GeneratedMutableMap.map3496map,
                            GeneratedMutableMap.map3497map,
                            GeneratedMutableMap.map3498map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-sum-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("avg")
                        ),
                        POPValues(dictionary, listOf(
                                "avg"
                            ), listOf(
                                GeneratedMutableMap.map3630map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "avg"
                        ), listOf(
                            GeneratedMutableMap.map3630map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("avg")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "#f3674",
                                "avg"
                            ), listOf(
                                GeneratedMutableMap.map3946map,
                                GeneratedMutableMap.map3947map,
                                GeneratedMutableMap.map3948map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "avg"
                        ), listOf(
                            GeneratedMutableMap.map3949map,
                            GeneratedMutableMap.map3950map,
                            GeneratedMutableMap.map3951map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-avg-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("min")
                        ),
                        POPValues(dictionary, listOf(
                                "min"
                            ), listOf(
                                GeneratedMutableMap.map4073map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "min"
                        ), listOf(
                            GeneratedMutableMap.map4073map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("min")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "min"
                            ), listOf(
                                GeneratedMutableMap.map4227map,
                                GeneratedMutableMap.map4228map,
                                GeneratedMutableMap.map4229map,
                                GeneratedMutableMap.map4230map,
                                GeneratedMutableMap.map4231map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "min"
                        ), listOf(
                            GeneratedMutableMap.map4227map,
                            GeneratedMutableMap.map4228map,
                            GeneratedMutableMap.map4229map,
                            GeneratedMutableMap.map4230map,
                            GeneratedMutableMap.map4231map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-min-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "max"
                            ), listOf(
                                GeneratedMutableMap.map4379map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map4379map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "max"
                            ), listOf(
                                GeneratedMutableMap.map4531map,
                                GeneratedMutableMap.map4532map,
                                GeneratedMutableMap.map4533map,
                                GeneratedMutableMap.map4534map,
                                GeneratedMutableMap.map4535map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map4531map,
                            GeneratedMutableMap.map4532map,
                            GeneratedMutableMap.map4533map,
                            GeneratedMutableMap.map4534map,
                            GeneratedMutableMap.map4535map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/aggregates/agg-max-02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("sample")
                        ),
                        POPValues(dictionary, listOf(
                                "sample"
                            ), listOf(
                                GeneratedMutableMap.map4683map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "sample"
                        ), listOf(
                            GeneratedMutableMap.map4683map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-sample-01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("g"),
                            AOPVariable("avg"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "g",
                                "c",
                                "avg"
                            ), listOf(
                                GeneratedMutableMap.map4983map,
                                GeneratedMutableMap.map4984map,
                                GeneratedMutableMap.map4985map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "g",
                            "avg",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map4986map,
                            GeneratedMutableMap.map4987map,
                            GeneratedMutableMap.map4988map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-err-01.rq */
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("max")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "max"
                            ), listOf(
                                GeneratedMutableMap.map5095map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "max"
                        ), listOf(
                            GeneratedMutableMap.map5095map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/aggregates/agg-empty-group.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map,
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6734map,
                            GeneratedMutableMap.map6735map,
                            GeneratedMutableMap.map6736map,
                            GeneratedMutableMap.map6737map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6734map,
                            GeneratedMutableMap.map6735map,
                            GeneratedMutableMap.map6736map,
                            GeneratedMutableMap.map6737map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map,
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6738map,
                            GeneratedMutableMap.map6739map,
                            GeneratedMutableMap.map6740map,
                            GeneratedMutableMap.map6741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6738map,
                            GeneratedMutableMap.map6739map,
                            GeneratedMutableMap.map6740map,
                            GeneratedMutableMap.map6741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z",
                                "z2"
                            ), listOf(
                                GeneratedMutableMap.map6885map,
                                GeneratedMutableMap.map6886map,
                                GeneratedMutableMap.map6887map,
                                GeneratedMutableMap.map6888map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "z",
                            "z2"
                        ), listOf(
                            GeneratedMutableMap.map6893map,
                            GeneratedMutableMap.map6894map,
                            GeneratedMutableMap.map6895map,
                            GeneratedMutableMap.map6896map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z2"),
                                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "z",
                            "z2"
                        ), listOf(
                            GeneratedMutableMap.map6893map,
                            GeneratedMutableMap.map6894map,
                            GeneratedMutableMap.map6895map,
                            GeneratedMutableMap.map6896map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z"),
                                AOPVariable("z2")
                            ), listOf(
                                GeneratedMutableMap.map6889map,
                                GeneratedMutableMap.map6890map,
                                GeneratedMutableMap.map6891map,
                                GeneratedMutableMap.map6892map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ), listOf(
                            GeneratedMutableMap.map6897map,
                            GeneratedMutableMap.map6898map,
                            GeneratedMutableMap.map6899map,
                            GeneratedMutableMap.map6900map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                                    LOPBind(
                                        AOPVariable("z2"),
                                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                                                    LOPBind(
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ), listOf(
                            GeneratedMutableMap.map6897map,
                            GeneratedMutableMap.map6898map,
                            GeneratedMutableMap.map6899map,
                            GeneratedMutableMap.map6900map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z"),
                            AOPVariable("s1")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "s1",
                                "p1",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7213map,
                                GeneratedMutableMap.map7214map,
                                GeneratedMutableMap.map7215map,
                                GeneratedMutableMap.map7216map,
                                GeneratedMutableMap.map7201map,
                                GeneratedMutableMap.map7217map,
                                GeneratedMutableMap.map7218map,
                                GeneratedMutableMap.map7219map,
                                GeneratedMutableMap.map7220map,
                                GeneratedMutableMap.map7206map,
                                GeneratedMutableMap.map7221map,
                                GeneratedMutableMap.map7222map,
                                GeneratedMutableMap.map7223map,
                                GeneratedMutableMap.map7224map,
                                GeneratedMutableMap.map7211map,
                                GeneratedMutableMap.map7225map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "z",
                            "s1"
                        ), listOf(
                            GeneratedMutableMap.map7226map,
                            GeneratedMutableMap.map7227map,
                            GeneratedMutableMap.map7228map,
                            GeneratedMutableMap.map7229map,
                            GeneratedMutableMap.map7230map,
                            GeneratedMutableMap.map7231map,
                            GeneratedMutableMap.map7232map,
                            GeneratedMutableMap.map7233map,
                            GeneratedMutableMap.map7234map,
                            GeneratedMutableMap.map7235map,
                            GeneratedMutableMap.map7236map,
                            GeneratedMutableMap.map7237map,
                            GeneratedMutableMap.map7238map,
                            GeneratedMutableMap.map7239map,
                            GeneratedMutableMap.map7240map,
                            GeneratedMutableMap.map7241map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/bind03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7292map,
                                GeneratedMutableMap.map7293map,
                                GeneratedMutableMap.map7294map,
                                GeneratedMutableMap.map7295map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7292map,
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPBindUndefined(
                                        dictionary,
                                        AOPVariable("z"),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7292map,
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map7297map,
                                GeneratedMutableMap.map7298map,
                                GeneratedMutableMap.map7299map,
                                GeneratedMutableMap.map7300map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7297map,
                            GeneratedMutableMap.map7298map,
                            GeneratedMutableMap.map7299map,
                            GeneratedMutableMap.map7300map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPUndef(),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7297map,
                            GeneratedMutableMap.map7298map,
                            GeneratedMutableMap.map7299map,
                            GeneratedMutableMap.map7300map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map7542map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map7546map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPFilter(
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                                                    LOPBind(
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(1), AOPVariable("o")),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map6726map,
                                GeneratedMutableMap.map6727map,
                                GeneratedMutableMap.map6728map,
                                GeneratedMutableMap.map6729map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map6730map,
                                GeneratedMutableMap.map6731map,
                                GeneratedMutableMap.map6732map,
                                GeneratedMutableMap.map6733map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
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
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "v",
                                "z"
                            ), listOf(
                                GeneratedMutableMap.map8318map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map8318map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("z"),
                                                        AOPInteger(4),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/p>","v",false,true,false,EIndexPattern.SPO)
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "v",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map8318map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("v"),
                                AOPVariable("z")
                            ), listOf(
                                GeneratedMutableMap.map8322map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map8322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ),
                                    LOPFilter(
                                        AOPEQ(AOPVariable("v"), AOPVariable("z")),
                                                    LOPBind(
                                                        AOPVariable("z"),
                                                        AOPInteger(4),
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/p>","\"4\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/p>"),AOPVariable("v"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("v"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map8322map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bind/bind11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8509map,
                                GeneratedMutableMap.map8510map
                            )
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
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
                                        false                                    )

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
                        LOPValues(listOf(
                                AOPVariable("book"),
                                AOPVariable("title"),
                                AOPVariable("price")
                            ), listOf(
                                GeneratedMutableMap.map8511map,
                                GeneratedMutableMap.map8512map
                            )
                        )
                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://purl.org/dc/elements/1.1/title>","\"SPARQL Tutorial\""))
                                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://purl.org/dc/elements/1.1/title>","\"The Semantic Web\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://purl.org/dc/elements/1.1/title>"),AOPVariable("title"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/book/book1>","<http://example.org/ns#price>","\"42\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/book/book2>","<http://example.org/ns#price>","\"23\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("book"),AOPVariable.calculate("<http://example.org/ns#price>"),AOPVariable("price"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o")
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o")
                        ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o")
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
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map8586map,
                            GeneratedMutableMap.map8587map,
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o")
                        ),
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

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map8585map,
                            GeneratedMutableMap.map8586map,
                            GeneratedMutableMap.map8587map,
                            GeneratedMutableMap.map8588map,
                            GeneratedMutableMap.map8589map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                                        false                                    )

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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map8716map,
                            GeneratedMutableMap.map8717map,
                            GeneratedMutableMap.map8718map,
                            GeneratedMutableMap.map8719map,
                            GeneratedMutableMap.map8720map,
                            GeneratedMutableMap.map8721map,
                            GeneratedMutableMap.map8722map,
                            GeneratedMutableMap.map8723map,
                            GeneratedMutableMap.map8724map,
                            GeneratedMutableMap.map8725map,
                            GeneratedMutableMap.map8726map,
                            GeneratedMutableMap.map8727map,
                            GeneratedMutableMap.map8728map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                                                    }()
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map8716map,
                            GeneratedMutableMap.map8717map,
                            GeneratedMutableMap.map8718map,
                            GeneratedMutableMap.map8719map,
                            GeneratedMutableMap.map8720map,
                            GeneratedMutableMap.map8721map,
                            GeneratedMutableMap.map8722map,
                            GeneratedMutableMap.map8723map,
                            GeneratedMutableMap.map8724map,
                            GeneratedMutableMap.map8725map,
                            GeneratedMutableMap.map8726map,
                            GeneratedMutableMap.map8727map,
                            GeneratedMutableMap.map8728map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1")
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1")
                        ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1")
                        ),
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
                    ),
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
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p1"),
                            AOPVariable("o1")
                        ),
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

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                                        true                                    )

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
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map8716map,
                            GeneratedMutableMap.map8717map,
                            GeneratedMutableMap.map8718map,
                            GeneratedMutableMap.map9074map,
                            GeneratedMutableMap.map9075map,
                            GeneratedMutableMap.map9076map,
                            GeneratedMutableMap.map9077map,
                            GeneratedMutableMap.map9078map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p1"),AOPVariable("o1"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/a>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/b>"))
                                                        graph.addData(1L,listOf("<http://example.org/b>","<http://xmlns.com/foaf/0.1/knows>","<http://example.org/c>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/knows>"),AOPVariable("o2"),graphName,false)                                                    }()
,
                                        true                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("o1"),
                            AOPVariable("o2")
                        ), listOf(
                            GeneratedMutableMap.map8716map,
                            GeneratedMutableMap.map8717map,
                            GeneratedMutableMap.map8718map,
                            GeneratedMutableMap.map9074map,
                            GeneratedMutableMap.map9075map,
                            GeneratedMutableMap.map9076map,
                            GeneratedMutableMap.map9077map,
                            GeneratedMutableMap.map9078map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/bindings/values07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("book"),
                            AOPVariable("title"),
                            AOPVariable("price")
                        ),
                        POPValues(dictionary, listOf(
                                "book",
                                "title",
                                "price"
                            ), listOf(
                                GeneratedMutableMap.map8509map
                            )
                        )
                    ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                    ),
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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","<http://example.org/o3>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","p","o",false,false,false,EIndexPattern.SPO)
                                                                                    }()

                                                                    )

                                                    )

                                    )

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                    ),
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
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p>","<http://example.org/o1>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o1>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s2>","<http://example.org/p>","<http://example.org/o2>"))
                                                                                        graph.addData(1L,listOf("<http://example.org/s3>","<http://example.org/p>","<http://example.org/o3>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()

                                                                    )

                                                    )

                                    )

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()

                                                                    )

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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ),
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
                                                                                                                        true                                                                                                                    )

                                                                                                    )

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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ),
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
                                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                                                    }()
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
                                                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                                                    }()
,
                                                                                                                        true                                                                                                                    )

                                                                                                    )

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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()

                                                                    )

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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map15725map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15725map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#myBanana>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>",false,true,true,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map15726map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15726map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#myBanana>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/ns#banana>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/ns#banana>"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "#c"
                            ), listOf(
                                GeneratedMutableMap.map15764map,
                                GeneratedMutableMap.map15765map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15768map,
                            GeneratedMutableMap.map15768map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c1"))
                                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c2"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://example.org/ns#b1>","#c",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15768map,
                            GeneratedMutableMap.map15768map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("#c")
                            ), listOf(
                                GeneratedMutableMap.map15766map,
                                GeneratedMutableMap.map15767map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15769map,
                            GeneratedMutableMap.map15769map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c1"))
                                        graph.addData(1L,listOf("<http://example.org/ns#a1>","<http://example.org/ns#b1>","_:c2"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://example.org/ns#b1>"),AOPVariable("#c"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15769map,
                            GeneratedMutableMap.map15769map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdf03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map15810map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map15810map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>",false,true,true,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map15811map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map15811map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map15849map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16030map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16030map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/rdfs05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16154map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map16154map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","x",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map16155map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map16155map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#d>","<http://www.w3.org/2000/01/rdf-schema#range>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#XMLLiteral>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/ns#d>"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#range>"),AOPVariable("x"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map16295map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("f")
                        ),
                        POPValues(dictionary, listOf(
                                "f"
                            ), listOf(
                                GeneratedMutableMap.map16339map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "f"
                        ), listOf(
                            GeneratedMutableMap.map16339map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("f")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","<http://example.org/ns#apple>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","f",true,true,false,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("f")
                        ),
                        LOPValues(listOf(
                                AOPVariable("f")
                            ), listOf(
                                GeneratedMutableMap.map16340map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("f")
                        ), listOf(
                            GeneratedMutableMap.map16340map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/rdfs12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("f")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/ns#favourite-fruit>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>","<http://example.org/ns#apple>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/ns#favourite-fruit>"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#_2>"),AOPVariable("f"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y"
                            ), listOf(
                                GeneratedMutableMap.map16563map,
                                GeneratedMutableMap.map16564map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ),
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
                                        false                                    )

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y")
                            ), listOf(
                                GeneratedMutableMap.map16565map,
                                GeneratedMutableMap.map16566map
                            )
                        )
                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y")
                        ),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","<http://example.org/x/y>"))
                                                        graph.addData(1L,listOf("<http://example.org/x/x>","<http://example.org/x/p>","_:y"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://example.org/x/p>"),AOPVariable("y"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/x/y>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                                        graph.addData(1L,listOf("_:y","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/x/c>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("y"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/x/c>"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("c")
                        ),
                        POPValues(dictionary, listOf(
                                "c"
                            ), listOf(
                                GeneratedMutableMap.map16612map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "c"
                        ), listOf(
                            GeneratedMutableMap.map16612map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/paper-sparqldl-Q1.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ),
                        POPValues(dictionary, listOf(
                                "p",
                                "v"
                            ), listOf(
                                GeneratedMutableMap.map17135map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ),
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
                                        false                                    )

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ),
                        LOPValues(listOf(
                                AOPVariable("p"),
                                AOPVariable("v")
                            ), listOf(
                                GeneratedMutableMap.map17139map
                            )
                        )
                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("p"),
                            AOPVariable("v")
                        ),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/hasPublication>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                                        graph.addData(1L,listOf("<http://example.org/publishedAt>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#ObjectProperty>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#ObjectProperty>"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/hasPublication>","<http://example.org/paper1>"))
                                                        graph.addData(1L,listOf("<http://example.org/John>","<http://example.org/name>","\"Johnnie\""))
                                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/GraduateAssistant>"))
                                                        graph.addData(1L,listOf("<http://example.org/John>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#NamedIndividual>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/John>"),AOPVariable("p"),AOPVariable("v"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map17183map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map17183map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://xmlns.com/foaf/0.1/name>","\"name\"@en",false,true,true,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map17184map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map17184map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/lang.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://xmlns.com/foaf/0.1/name>","\"name\"@en"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/name>"),AOPVariable.calculate("\"name\"@en"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("z")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6734map,
                            GeneratedMutableMap.map6735map,
                            GeneratedMutableMap.map6736map,
                            GeneratedMutableMap.map6737map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6738map,
                            GeneratedMutableMap.map6739map,
                            GeneratedMutableMap.map6740map,
                            GeneratedMutableMap.map6741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z2"),
                                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "z",
                            "z2"
                        ), listOf(
                            GeneratedMutableMap.map6893map,
                            GeneratedMutableMap.map6894map,
                            GeneratedMutableMap.map6895map,
                            GeneratedMutableMap.map6896map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ),
                                    LOPBind(
                                        AOPVariable("z2"),
                                        AOPAddition(AOPInteger(100), AOPVariable("o")),
                                                    LOPBind(
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("o"),
                            AOPVariable("z"),
                            AOPVariable("z2")
                        ), listOf(
                            GeneratedMutableMap.map6897map,
                            GeneratedMutableMap.map6898map,
                            GeneratedMutableMap.map6899map,
                            GeneratedMutableMap.map6900map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPBindUndefined(
                                        dictionary,
                                        AOPVariable("z"),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7292map,
                            GeneratedMutableMap.map7293map,
                            GeneratedMutableMap.map7294map,
                            GeneratedMutableMap.map7295map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPUndef(),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7297map,
                            GeneratedMutableMap.map7298map,
                            GeneratedMutableMap.map7299map,
                            GeneratedMutableMap.map7300map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind04.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map7542map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPFilter(
                                        AOPEQ(AOPVariable("z"), AOPInteger(3)),
                                                    LOPBind(
                                                        AOPVariable("z"),
                                                        AOPAddition(AOPInteger(1), AOPVariable("o")),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map7546map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o",
                            "z"
                        ), listOf(
                            GeneratedMutableMap.map6726map,
                            GeneratedMutableMap.map6727map,
                            GeneratedMutableMap.map6728map,
                            GeneratedMutableMap.map6729map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ),
                                    LOPBind(
                                        AOPVariable("z"),
                                        AOPAddition(AOPInteger(10), AOPVariable("o")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/p>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://www.w3.org/2002/07/owl#DatatypeProperty>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("p"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.w3.org/2002/07/owl#DatatypeProperty>"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("z")
                        ), listOf(
                            GeneratedMutableMap.map6730map,
                            GeneratedMutableMap.map6731map,
                            GeneratedMutableMap.map6732map,
                            GeneratedMutableMap.map6733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/bind06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "Y1",
                                "Y2"
                            ), listOf(
                                GeneratedMutableMap.map18854map,
                                GeneratedMutableMap.map18855map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ),
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
                                                        false                                                    )
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
                                        false                                    )

                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("Y1"),
                                AOPVariable("Y2")
                            ), listOf(
                                GeneratedMutableMap.map18856map,
                                GeneratedMutableMap.map18857map
                            )
                        )
                    ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y1"),
                            AOPVariable("Y2")
                        ),
                                    LOPJoin(
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                                        graph.addData(1L,listOf("<http://example.org/test#c>","<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>","<http://example.org/test#Person>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://example.org/test#Person>"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#name>","\"A\""))
                                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#name>","\"B\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#name>"),AOPVariable("Y1"),graphName,false)                                                                    }()
,
                                                        false                                                    )
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#nick>","\"Anick\""))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#nick>","\"Bnick\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#nick>"),AOPVariable("Y2"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                                "#a"
                            ), listOf(
                                GeneratedMutableMap.map18900map,
                                GeneratedMutableMap.map18901map,
                                GeneratedMutableMap.map18902map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5900map,
                            GeneratedMutableMap.map5900map,
                            GeneratedMutableMap.map5900map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/entailment/sparqldl-05.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
                        POPValues(dictionary, listOf(
                                "#aa",
                                "X",
                                "Y",
                                "Z"
                            ), listOf(
                                GeneratedMutableMap.map19085map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19087map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
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
                                                                        false                                                                    )
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"Y","<http://example.org/test#s>","#aa",false,true,false,EIndexPattern.SPO)
                                                                    }()
,
                                                        false                                                    )
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y",
                            "Z"
                        ), listOf(
                            GeneratedMutableMap.map19087map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
                        LOPValues(listOf(
                                AOPVariable("#aa"),
                                AOPVariable("X"),
                                AOPVariable("Y"),
                                AOPVariable("Z")
                            ), listOf(
                                GeneratedMutableMap.map19086map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19088map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ),
                                    LOPJoin(
                                                    LOPJoin(
                                                                    LOPJoin(
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable.calculate("<http://example.org/test#a>"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#aa"),graphName,false)                                                                                    }()
,
                                                                                    {
                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                        graph.addData(1L,listOf("<http://example.org/test#dd>","<http://example.org/test#t>","<http://example.org/test#bb>"))
                                                                                        DistributedTripleStore.commit(1L)
                                                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#t>"),AOPVariable("Y"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/test#bb>","<http://example.org/test#s>","<http://example.org/test#aa>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("Y"),AOPVariable.calculate("<http://example.org/test#s>"),AOPVariable("#aa"),graphName,false)                                                                    }()
,
                                                        false                                                    )
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#aa"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Z"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y"),
                            AOPVariable("Z")
                        ), listOf(
                            GeneratedMutableMap.map19088map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
                        POPValues(dictionary, listOf(
                                "X",
                                "#a",
                                "Y"
                            ), listOf(
                                GeneratedMutableMap.map19147map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19149map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19149map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
                        LOPValues(listOf(
                                AOPVariable("X"),
                                AOPVariable("#a"),
                                AOPVariable("Y")
                            ), listOf(
                                GeneratedMutableMap.map19148map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19150map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#aa>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#aa>","<http://example.org/test#r>","<http://example.org/test#ee>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#cc>","<http://example.org/test#r>","<http://example.org/test#dd>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#r>"),AOPVariable("Y"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19150map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-08.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19224map,
                            GeneratedMutableMap.map19225map,
                            GeneratedMutableMap.map19226map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
                                        false                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "X",
                            "Y"
                        ), listOf(
                            GeneratedMutableMap.map19224map,
                            GeneratedMutableMap.map19225map,
                            GeneratedMutableMap.map19226map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19227map,
                            GeneratedMutableMap.map19228map,
                            GeneratedMutableMap.map19229map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ),
                                    LOPJoin(
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#a>","<http://example.org/test#p>","<http://example.org/test#b>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("X"),AOPVariable.calculate("<http://example.org/test#p>"),AOPVariable("#a"),graphName,false)                                                    }()
,
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#c>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#h>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#b>","<http://example.org/test#q>","<http://example.org/test#i>"))
                                                        graph.addData(1L,listOf("<http://example.org/test#x>","<http://example.org/test#q>","<http://example.org/test#x>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("#a"),AOPVariable.calculate("<http://example.org/test#q>"),AOPVariable("Y"),graphName,false)                                                    }()
,
                                        false                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("X"),
                            AOPVariable("Y")
                        ), listOf(
                            GeneratedMutableMap.map19227map,
                            GeneratedMutableMap.map19228map,
                            GeneratedMutableMap.map19229map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/sparqldl-09.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("C")
                        ),
                        POPValues(dictionary, listOf(
                                "C"
                            ), listOf(
                                GeneratedMutableMap.map19410map
                            )
                        )
                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("a"),
                            AOPVariable("b"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "b",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map19488map
                            )
                        )
                    ),
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
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("parent")
                        ),
                        POPValues(dictionary, listOf(
                                "parent",
                                "child"
                            ), listOf(
                                GeneratedMutableMap.map19579map,
                                GeneratedMutableMap.map19580map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "parent"
                        ), listOf(
                            GeneratedMutableMap.map19583map,
                            GeneratedMutableMap.map19584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("parent")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://example.org/test#hasChild>","<http://example.org/test#Charlie>"))
                                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://example.org/test#hasChild>","<http://example.org/test#Alice>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"parent","<http://example.org/test#hasChild>","child",false,true,false,EIndexPattern.SPO)
                                    }()

                    ),
                    POPValues(dictionary, listOf(
                            "parent"
                        ), listOf(
                            GeneratedMutableMap.map19583map,
                            GeneratedMutableMap.map19584map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("parent")
                        ),
                        LOPValues(listOf(
                                AOPVariable("parent"),
                                AOPVariable("child")
                            ), listOf(
                                GeneratedMutableMap.map19581map,
                                GeneratedMutableMap.map19582map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("parent")
                        ), listOf(
                            GeneratedMutableMap.map19585map,
                            GeneratedMutableMap.map19586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("parent")
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/test#Bob>","<http://example.org/test#hasChild>","<http://example.org/test#Charlie>"))
                                        graph.addData(1L,listOf("<http://example.org/test#Dudley>","<http://example.org/test#hasChild>","<http://example.org/test#Alice>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable("parent"),AOPVariable.calculate("<http://example.org/test#hasChild>"),AOPVariable("child"),graphName,false)                                    }()

                    ),
                    LOPValues(listOf(
                            AOPVariable("parent")
                        ), listOf(
                            GeneratedMutableMap.map19585map,
                            GeneratedMutableMap.map19586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/entailment/parent2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map22849map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map22851map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                                    POPFilter(
                                                        dictionary,
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map22851map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map22850map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map22852map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    LOPBind(
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPVariable("str"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                                    LOPFilter(
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map22852map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map23123map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23125map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                                    POPFilter(
                                                        dictionary,
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23125map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map23124map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23126map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    LOPBind(
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPBuildInCallSTR(AOPVariable("str")), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
                                                    LOPFilter(
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23126map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map23330map,
                                GeneratedMutableMap.map23331map,
                                GeneratedMutableMap.map23332map,
                                GeneratedMutableMap.map23333map,
                                GeneratedMutableMap.map23334map,
                                GeneratedMutableMap.map23335map,
                                GeneratedMutableMap.map23336map,
                                GeneratedMutableMap.map23337map,
                                GeneratedMutableMap.map23338map,
                                GeneratedMutableMap.map23339map,
                                GeneratedMutableMap.map23340map,
                                GeneratedMutableMap.map23341map,
                                GeneratedMutableMap.map23342map,
                                GeneratedMutableMap.map23343map,
                                GeneratedMutableMap.map23344map,
                                GeneratedMutableMap.map23345map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23362map,
                            GeneratedMutableMap.map23363map,
                            GeneratedMutableMap.map23364map,
                            GeneratedMutableMap.map23365map,
                            GeneratedMutableMap.map23366map,
                            GeneratedMutableMap.map23367map,
                            GeneratedMutableMap.map23368map,
                            GeneratedMutableMap.map23369map,
                            GeneratedMutableMap.map23370map,
                            GeneratedMutableMap.map23371map,
                            GeneratedMutableMap.map22851map,
                            GeneratedMutableMap.map23372map,
                            GeneratedMutableMap.map23373map,
                            GeneratedMutableMap.map23374map,
                            GeneratedMutableMap.map23375map,
                            GeneratedMutableMap.map23376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23362map,
                            GeneratedMutableMap.map23363map,
                            GeneratedMutableMap.map23364map,
                            GeneratedMutableMap.map23365map,
                            GeneratedMutableMap.map23366map,
                            GeneratedMutableMap.map23367map,
                            GeneratedMutableMap.map23368map,
                            GeneratedMutableMap.map23369map,
                            GeneratedMutableMap.map23370map,
                            GeneratedMutableMap.map23371map,
                            GeneratedMutableMap.map22851map,
                            GeneratedMutableMap.map23372map,
                            GeneratedMutableMap.map23373map,
                            GeneratedMutableMap.map23374map,
                            GeneratedMutableMap.map23375map,
                            GeneratedMutableMap.map23376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map23346map,
                                GeneratedMutableMap.map23347map,
                                GeneratedMutableMap.map23348map,
                                GeneratedMutableMap.map23349map,
                                GeneratedMutableMap.map23350map,
                                GeneratedMutableMap.map23351map,
                                GeneratedMutableMap.map23352map,
                                GeneratedMutableMap.map23353map,
                                GeneratedMutableMap.map23354map,
                                GeneratedMutableMap.map23355map,
                                GeneratedMutableMap.map23356map,
                                GeneratedMutableMap.map23357map,
                                GeneratedMutableMap.map23358map,
                                GeneratedMutableMap.map23359map,
                                GeneratedMutableMap.map23360map,
                                GeneratedMutableMap.map23361map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23377map,
                            GeneratedMutableMap.map23378map,
                            GeneratedMutableMap.map23379map,
                            GeneratedMutableMap.map23380map,
                            GeneratedMutableMap.map23381map,
                            GeneratedMutableMap.map23382map,
                            GeneratedMutableMap.map23383map,
                            GeneratedMutableMap.map23384map,
                            GeneratedMutableMap.map23385map,
                            GeneratedMutableMap.map23386map,
                            GeneratedMutableMap.map22852map,
                            GeneratedMutableMap.map23387map,
                            GeneratedMutableMap.map23388map,
                            GeneratedMutableMap.map23389map,
                            GeneratedMutableMap.map23390map,
                            GeneratedMutableMap.map23391map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    LOPBind(
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRDT(AOPVariable("o"), AOPIri("http://www.w3.org/2001/XMLSchema#string")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23377map,
                            GeneratedMutableMap.map23378map,
                            GeneratedMutableMap.map23379map,
                            GeneratedMutableMap.map23380map,
                            GeneratedMutableMap.map23381map,
                            GeneratedMutableMap.map23382map,
                            GeneratedMutableMap.map23383map,
                            GeneratedMutableMap.map23384map,
                            GeneratedMutableMap.map23385map,
                            GeneratedMutableMap.map23386map,
                            GeneratedMutableMap.map22852map,
                            GeneratedMutableMap.map23387map,
                            GeneratedMutableMap.map23388map,
                            GeneratedMutableMap.map23389map,
                            GeneratedMutableMap.map23390map,
                            GeneratedMutableMap.map23391map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strdt03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                            ), listOf(
                                GeneratedMutableMap.map23644map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23645map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("s2"),
                                        AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                                    POPFilter(
                                                        dictionary,
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23645map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                            ), listOf(
                                GeneratedMutableMap.map22850map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map22852map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                                    LOPBind(
                                        AOPVariable("s2"),
                                        AOPBuildInCallSTRLANG(AOPVariable("str"), AOPSimpleLiteral("\"", "en-US")),
                                                    LOPFilter(
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map22852map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "s2"
                            ), listOf(
                                GeneratedMutableMap.map23913map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23915map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("s2"),
                                        AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                                    POPFilter(
                                                        dictionary,
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                    }()

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "s2"
                        ), listOf(
                            GeneratedMutableMap.map23915map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("s2")
                            ), listOf(
                                GeneratedMutableMap.map23914map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map23916map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ),
                                    LOPBind(
                                        AOPVariable("s2"),
                                        AOPBuildInCallSTRLANG(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "en-US")),
                                                    LOPFilter(
                                                        AOPBuildInCallLANGMATCHES(AOPBuildInCallLANG(AOPVariable("str")), AOPSimpleLiteral("\"", "en")),
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
                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("s2")
                        ), listOf(
                            GeneratedMutableMap.map23916map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "str1"
                            ), listOf(
                                GeneratedMutableMap.map23330map,
                                GeneratedMutableMap.map23331map,
                                GeneratedMutableMap.map23332map,
                                GeneratedMutableMap.map23333map,
                                GeneratedMutableMap.map23334map,
                                GeneratedMutableMap.map23335map,
                                GeneratedMutableMap.map23336map,
                                GeneratedMutableMap.map23337map,
                                GeneratedMutableMap.map23338map,
                                GeneratedMutableMap.map24085map,
                                GeneratedMutableMap.map23340map,
                                GeneratedMutableMap.map24086map,
                                GeneratedMutableMap.map24087map,
                                GeneratedMutableMap.map24088map,
                                GeneratedMutableMap.map23344map,
                                GeneratedMutableMap.map23345map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23362map,
                            GeneratedMutableMap.map23363map,
                            GeneratedMutableMap.map23364map,
                            GeneratedMutableMap.map23365map,
                            GeneratedMutableMap.map23366map,
                            GeneratedMutableMap.map23367map,
                            GeneratedMutableMap.map23368map,
                            GeneratedMutableMap.map23369map,
                            GeneratedMutableMap.map23370map,
                            GeneratedMutableMap.map24093map,
                            GeneratedMutableMap.map22851map,
                            GeneratedMutableMap.map24094map,
                            GeneratedMutableMap.map24095map,
                            GeneratedMutableMap.map24096map,
                            GeneratedMutableMap.map23375map,
                            GeneratedMutableMap.map23376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str1"
                        ), listOf(
                            GeneratedMutableMap.map23362map,
                            GeneratedMutableMap.map23363map,
                            GeneratedMutableMap.map23364map,
                            GeneratedMutableMap.map23365map,
                            GeneratedMutableMap.map23366map,
                            GeneratedMutableMap.map23367map,
                            GeneratedMutableMap.map23368map,
                            GeneratedMutableMap.map23369map,
                            GeneratedMutableMap.map23370map,
                            GeneratedMutableMap.map24093map,
                            GeneratedMutableMap.map22851map,
                            GeneratedMutableMap.map24094map,
                            GeneratedMutableMap.map24095map,
                            GeneratedMutableMap.map24096map,
                            GeneratedMutableMap.map23375map,
                            GeneratedMutableMap.map23376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("str1")
                            ), listOf(
                                GeneratedMutableMap.map23346map,
                                GeneratedMutableMap.map23347map,
                                GeneratedMutableMap.map23348map,
                                GeneratedMutableMap.map23349map,
                                GeneratedMutableMap.map23350map,
                                GeneratedMutableMap.map23351map,
                                GeneratedMutableMap.map23352map,
                                GeneratedMutableMap.map23353map,
                                GeneratedMutableMap.map23354map,
                                GeneratedMutableMap.map24089map,
                                GeneratedMutableMap.map23356map,
                                GeneratedMutableMap.map24090map,
                                GeneratedMutableMap.map24091map,
                                GeneratedMutableMap.map24092map,
                                GeneratedMutableMap.map23360map,
                                GeneratedMutableMap.map23361map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23377map,
                            GeneratedMutableMap.map23378map,
                            GeneratedMutableMap.map23379map,
                            GeneratedMutableMap.map23380map,
                            GeneratedMutableMap.map23381map,
                            GeneratedMutableMap.map23382map,
                            GeneratedMutableMap.map23383map,
                            GeneratedMutableMap.map23384map,
                            GeneratedMutableMap.map23385map,
                            GeneratedMutableMap.map24097map,
                            GeneratedMutableMap.map22852map,
                            GeneratedMutableMap.map24098map,
                            GeneratedMutableMap.map24099map,
                            GeneratedMutableMap.map24100map,
                            GeneratedMutableMap.map23390map,
                            GeneratedMutableMap.map23391map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ),
                                    LOPBind(
                                        AOPVariable("str1"),
                                        AOPBuildInCallSTRLANG(AOPVariable("o"), AOPSimpleLiteral("\"", "en-US")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str1")
                        ), listOf(
                            GeneratedMutableMap.map23377map,
                            GeneratedMutableMap.map23378map,
                            GeneratedMutableMap.map23379map,
                            GeneratedMutableMap.map23380map,
                            GeneratedMutableMap.map23381map,
                            GeneratedMutableMap.map23382map,
                            GeneratedMutableMap.map23383map,
                            GeneratedMutableMap.map23384map,
                            GeneratedMutableMap.map23385map,
                            GeneratedMutableMap.map24097map,
                            GeneratedMutableMap.map22852map,
                            GeneratedMutableMap.map24098map,
                            GeneratedMutableMap.map24099map,
                            GeneratedMutableMap.map24100map,
                            GeneratedMutableMap.map23390map,
                            GeneratedMutableMap.map23391map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/strlang03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "num"
                            ), listOf(
                                GeneratedMutableMap.map24283map,
                                GeneratedMutableMap.map24284map,
                                GeneratedMutableMap.map24285map,
                                GeneratedMutableMap.map24286map,
                                GeneratedMutableMap.map24287map
                            )
                        )
                    ),
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
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
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
                                                    }()

                                    )

                    ),
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
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("num")
                            ), listOf(
                                GeneratedMutableMap.map23318map,
                                GeneratedMutableMap.map23319map,
                                GeneratedMutableMap.map23320map,
                                GeneratedMutableMap.map23321map,
                                GeneratedMutableMap.map23322map
                            )
                        )
                    ),
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
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                                    LOPFilter(
                                        AOPBuildInCallIsNUMERIC(AOPVariable("num")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("num"),graphName,false)                                                    }()

                                    )

                    ),
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
            }() /* resources/sparql11-test-suite/functions/isnumeric01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num"
                            ), listOf(
                                GeneratedMutableMap.map24298map,
                                GeneratedMutableMap.map24299map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24298map,
                            GeneratedMutableMap.map24299map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num"
                        ), listOf(
                            GeneratedMutableMap.map24298map,
                            GeneratedMutableMap.map24299map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num")
                            ), listOf(
                                GeneratedMutableMap.map24303map,
                                GeneratedMutableMap.map24304map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map24303map,
                            GeneratedMutableMap.map24304map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ),
                                    LOPFilter(
                                        AOPGEQ(AOPBuildInCallABS(AOPVariable("num")), AOPInteger(2)),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num")
                        ), listOf(
                            GeneratedMutableMap.map24303map,
                            GeneratedMutableMap.map24304map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/abs01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "ceil"
                            ), listOf(
                                GeneratedMutableMap.map24495map,
                                GeneratedMutableMap.map24496map,
                                GeneratedMutableMap.map24497map,
                                GeneratedMutableMap.map24498map,
                                GeneratedMutableMap.map24499map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "ceil"
                        ), listOf(
                            GeneratedMutableMap.map24495map,
                            GeneratedMutableMap.map24496map,
                            GeneratedMutableMap.map24497map,
                            GeneratedMutableMap.map24498map,
                            GeneratedMutableMap.map24499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("ceil"),
                                        AOPBuildInCallCEIL(AOPVariable("num")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "ceil"
                        ), listOf(
                            GeneratedMutableMap.map24495map,
                            GeneratedMutableMap.map24496map,
                            GeneratedMutableMap.map24497map,
                            GeneratedMutableMap.map24498map,
                            GeneratedMutableMap.map24499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("ceil")
                            ), listOf(
                                GeneratedMutableMap.map24500map,
                                GeneratedMutableMap.map24501map,
                                GeneratedMutableMap.map24502map,
                                GeneratedMutableMap.map24503map,
                                GeneratedMutableMap.map24504map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24501map,
                            GeneratedMutableMap.map24502map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ),
                                    LOPBind(
                                        AOPVariable("ceil"),
                                        AOPBuildInCallCEIL(AOPVariable("num")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("ceil")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24501map,
                            GeneratedMutableMap.map24502map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ceil01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "floor"
                            ), listOf(
                                GeneratedMutableMap.map24588map,
                                GeneratedMutableMap.map24589map,
                                GeneratedMutableMap.map24590map,
                                GeneratedMutableMap.map24591map,
                                GeneratedMutableMap.map24592map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "floor"
                        ), listOf(
                            GeneratedMutableMap.map24588map,
                            GeneratedMutableMap.map24589map,
                            GeneratedMutableMap.map24590map,
                            GeneratedMutableMap.map24591map,
                            GeneratedMutableMap.map24592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("floor"),
                                        AOPBuildInCallFLOOR(AOPVariable("num")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "floor"
                        ), listOf(
                            GeneratedMutableMap.map24588map,
                            GeneratedMutableMap.map24589map,
                            GeneratedMutableMap.map24590map,
                            GeneratedMutableMap.map24591map,
                            GeneratedMutableMap.map24592map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("floor")
                            ), listOf(
                                GeneratedMutableMap.map24500map,
                                GeneratedMutableMap.map24593map,
                                GeneratedMutableMap.map24594map,
                                GeneratedMutableMap.map24503map,
                                GeneratedMutableMap.map24595map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24595map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ),
                                    LOPBind(
                                        AOPVariable("floor"),
                                        AOPBuildInCallFLOOR(AOPVariable("num")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("floor")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24595map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/floor01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "num",
                                "round"
                            ), listOf(
                                GeneratedMutableMap.map24679map,
                                GeneratedMutableMap.map24680map,
                                GeneratedMutableMap.map24681map,
                                GeneratedMutableMap.map24682map,
                                GeneratedMutableMap.map24683map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "round"
                        ), listOf(
                            GeneratedMutableMap.map24679map,
                            GeneratedMutableMap.map24680map,
                            GeneratedMutableMap.map24681map,
                            GeneratedMutableMap.map24682map,
                            GeneratedMutableMap.map24683map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("round"),
                                        AOPBuildInCallROUND(AOPVariable("num")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "num",
                            "round"
                        ), listOf(
                            GeneratedMutableMap.map24679map,
                            GeneratedMutableMap.map24680map,
                            GeneratedMutableMap.map24681map,
                            GeneratedMutableMap.map24682map,
                            GeneratedMutableMap.map24683map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("num"),
                                AOPVariable("round")
                            ), listOf(
                                GeneratedMutableMap.map24500map,
                                GeneratedMutableMap.map24593map,
                                GeneratedMutableMap.map24594map,
                                GeneratedMutableMap.map24503map,
                                GeneratedMutableMap.map24504map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ),
                                    LOPBind(
                                        AOPVariable("round"),
                                        AOPBuildInCallROUND(AOPVariable("num")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/n1>","<http://example.org/num>","\"-1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n2>","<http://example.org/num>","\"-1.6\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n3>","<http://example.org/num>","\"1.1\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        graph.addData(1L,listOf("<http://example.org/n4>","<http://example.org/num>","\"-2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://example.org/n5>","<http://example.org/num>","\"2.5\"^^<http://www.w3.org/2001/XMLSchema#decimal>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/num>"),AOPVariable("num"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("num"),
                            AOPVariable("round")
                        ), listOf(
                            GeneratedMutableMap.map24500map,
                            GeneratedMutableMap.map24593map,
                            GeneratedMutableMap.map24594map,
                            GeneratedMutableMap.map24503map,
                            GeneratedMutableMap.map24504map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/round01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "str1",
                                "str2",
                                "str"
                            ), listOf(
                                GeneratedMutableMap.map24784map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map24786map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str"),
                                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map24786map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str")
                        ),
                        LOPValues(listOf(
                                AOPVariable("str1"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map24785map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24787map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str")
                        ),
                                    LOPBind(
                                        AOPVariable("str"),
                                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s6>","<http://example.org/str>","\"abc\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s6>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://example.org/s7>","<http://example.org/str>","\"DEF\"^^<http://www.w3.org/2001/XMLSchema#string>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s7>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24787map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s1",
                                "str1",
                                "s2",
                                "str2",
                                "str"
                            ), listOf(
                                GeneratedMutableMap.map25497map,
                                GeneratedMutableMap.map25498map,
                                GeneratedMutableMap.map25499map,
                                GeneratedMutableMap.map25500map,
                                GeneratedMutableMap.map25501map,
                                GeneratedMutableMap.map25502map,
                                GeneratedMutableMap.map25503map,
                                GeneratedMutableMap.map25504map,
                                GeneratedMutableMap.map25505map,
                                GeneratedMutableMap.map25506map,
                                GeneratedMutableMap.map25507map,
                                GeneratedMutableMap.map25508map,
                                GeneratedMutableMap.map25509map,
                                GeneratedMutableMap.map25510map,
                                GeneratedMutableMap.map25511map,
                                GeneratedMutableMap.map25512map,
                                GeneratedMutableMap.map25513map,
                                GeneratedMutableMap.map25514map,
                                GeneratedMutableMap.map25515map,
                                GeneratedMutableMap.map25516map,
                                GeneratedMutableMap.map25517map,
                                GeneratedMutableMap.map25518map,
                                GeneratedMutableMap.map25519map,
                                GeneratedMutableMap.map25520map,
                                GeneratedMutableMap.map25521map,
                                GeneratedMutableMap.map25522map,
                                GeneratedMutableMap.map25523map,
                                GeneratedMutableMap.map25524map,
                                GeneratedMutableMap.map25525map,
                                GeneratedMutableMap.map25526map,
                                GeneratedMutableMap.map25527map,
                                GeneratedMutableMap.map25528map,
                                GeneratedMutableMap.map25529map,
                                GeneratedMutableMap.map25530map,
                                GeneratedMutableMap.map25531map,
                                GeneratedMutableMap.map25532map,
                                GeneratedMutableMap.map25533map,
                                GeneratedMutableMap.map25534map,
                                GeneratedMutableMap.map25535map,
                                GeneratedMutableMap.map25536map,
                                GeneratedMutableMap.map25537map,
                                GeneratedMutableMap.map25538map,
                                GeneratedMutableMap.map25539map,
                                GeneratedMutableMap.map25540map,
                                GeneratedMutableMap.map25541map,
                                GeneratedMutableMap.map25542map,
                                GeneratedMutableMap.map25543map,
                                GeneratedMutableMap.map25544map,
                                GeneratedMutableMap.map25545map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map25595map,
                            GeneratedMutableMap.map25596map,
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
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25608map,
                            GeneratedMutableMap.map25609map,
                            GeneratedMutableMap.map25610map,
                            GeneratedMutableMap.map25611map,
                            GeneratedMutableMap.map25612map,
                            GeneratedMutableMap.map25613map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25614map,
                            GeneratedMutableMap.map25615map,
                            GeneratedMutableMap.map25616map,
                            GeneratedMutableMap.map25617map,
                            GeneratedMutableMap.map25618map,
                            GeneratedMutableMap.map25619map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25620map,
                            GeneratedMutableMap.map25621map,
                            GeneratedMutableMap.map25622map,
                            GeneratedMutableMap.map25623map,
                            GeneratedMutableMap.map25624map,
                            GeneratedMutableMap.map25625map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25626map,
                            GeneratedMutableMap.map25627map,
                            GeneratedMutableMap.map25628map,
                            GeneratedMutableMap.map25629map,
                            GeneratedMutableMap.map25630map,
                            GeneratedMutableMap.map25631map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("str"),
                                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map25595map,
                            GeneratedMutableMap.map25596map,
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
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25608map,
                            GeneratedMutableMap.map25609map,
                            GeneratedMutableMap.map25610map,
                            GeneratedMutableMap.map25611map,
                            GeneratedMutableMap.map25612map,
                            GeneratedMutableMap.map25613map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25614map,
                            GeneratedMutableMap.map25615map,
                            GeneratedMutableMap.map25616map,
                            GeneratedMutableMap.map25617map,
                            GeneratedMutableMap.map25618map,
                            GeneratedMutableMap.map25619map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25620map,
                            GeneratedMutableMap.map25621map,
                            GeneratedMutableMap.map25622map,
                            GeneratedMutableMap.map25623map,
                            GeneratedMutableMap.map25624map,
                            GeneratedMutableMap.map25625map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25626map,
                            GeneratedMutableMap.map25627map,
                            GeneratedMutableMap.map25628map,
                            GeneratedMutableMap.map25629map,
                            GeneratedMutableMap.map25630map,
                            GeneratedMutableMap.map25631map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map,
                            GeneratedMutableMap.map25601map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s1"),
                                AOPVariable("str1"),
                                AOPVariable("s2"),
                                AOPVariable("str2"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map25546map,
                                GeneratedMutableMap.map25547map,
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
                                GeneratedMutableMap.map25594map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("str")
                        ), listOf(
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
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25645map,
                            GeneratedMutableMap.map25646map,
                            GeneratedMutableMap.map25647map,
                            GeneratedMutableMap.map25648map,
                            GeneratedMutableMap.map25649map,
                            GeneratedMutableMap.map25650map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25651map,
                            GeneratedMutableMap.map25652map,
                            GeneratedMutableMap.map25653map,
                            GeneratedMutableMap.map25654map,
                            GeneratedMutableMap.map25655map,
                            GeneratedMutableMap.map25656map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25657map,
                            GeneratedMutableMap.map25658map,
                            GeneratedMutableMap.map25659map,
                            GeneratedMutableMap.map25660map,
                            GeneratedMutableMap.map25661map,
                            GeneratedMutableMap.map25662map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25663map,
                            GeneratedMutableMap.map25664map,
                            GeneratedMutableMap.map25665map,
                            GeneratedMutableMap.map25666map,
                            GeneratedMutableMap.map25667map,
                            GeneratedMutableMap.map25668map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str")
                        ),
                                    LOPBind(
                                        AOPVariable("str"),
                                        AOPBuildInCallCONCAT(AOPVariable("str1"), AOPVariable("str2")),
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
                                                                        LOPTriple(AOPVariable("s1"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str1"),graphName,false)                                                                    }()
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
                                                                        LOPTriple(AOPVariable("s2"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str2"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("str")
                        ), listOf(
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
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25645map,
                            GeneratedMutableMap.map25646map,
                            GeneratedMutableMap.map25647map,
                            GeneratedMutableMap.map25648map,
                            GeneratedMutableMap.map25649map,
                            GeneratedMutableMap.map25650map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25651map,
                            GeneratedMutableMap.map25652map,
                            GeneratedMutableMap.map25653map,
                            GeneratedMutableMap.map25654map,
                            GeneratedMutableMap.map25655map,
                            GeneratedMutableMap.map25656map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25657map,
                            GeneratedMutableMap.map25658map,
                            GeneratedMutableMap.map25659map,
                            GeneratedMutableMap.map25660map,
                            GeneratedMutableMap.map25661map,
                            GeneratedMutableMap.map25662map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25663map,
                            GeneratedMutableMap.map25664map,
                            GeneratedMutableMap.map25665map,
                            GeneratedMutableMap.map25666map,
                            GeneratedMutableMap.map25667map,
                            GeneratedMutableMap.map25668map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map,
                            GeneratedMutableMap.map25638map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/concat02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "len"
                            ), listOf(
                                GeneratedMutableMap.map25829map,
                                GeneratedMutableMap.map25830map,
                                GeneratedMutableMap.map25831map,
                                GeneratedMutableMap.map25832map,
                                GeneratedMutableMap.map25833map,
                                GeneratedMutableMap.map25834map,
                                GeneratedMutableMap.map25835map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "str",
                            "len"
                        ), listOf(
                            GeneratedMutableMap.map25843map,
                            GeneratedMutableMap.map25844map,
                            GeneratedMutableMap.map25845map,
                            GeneratedMutableMap.map25846map,
                            GeneratedMutableMap.map25847map,
                            GeneratedMutableMap.map25848map,
                            GeneratedMutableMap.map25849map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("len"),
                                        AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "str",
                            "len"
                        ), listOf(
                            GeneratedMutableMap.map25843map,
                            GeneratedMutableMap.map25844map,
                            GeneratedMutableMap.map25845map,
                            GeneratedMutableMap.map25846map,
                            GeneratedMutableMap.map25847map,
                            GeneratedMutableMap.map25848map,
                            GeneratedMutableMap.map25849map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("len")
                            ), listOf(
                                GeneratedMutableMap.map25836map,
                                GeneratedMutableMap.map25837map,
                                GeneratedMutableMap.map25838map,
                                GeneratedMutableMap.map25839map,
                                GeneratedMutableMap.map25840map,
                                GeneratedMutableMap.map25841map,
                                GeneratedMutableMap.map25842map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ), listOf(
                            GeneratedMutableMap.map25850map,
                            GeneratedMutableMap.map25851map,
                            GeneratedMutableMap.map25852map,
                            GeneratedMutableMap.map25853map,
                            GeneratedMutableMap.map25854map,
                            GeneratedMutableMap.map25855map,
                            GeneratedMutableMap.map25856map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ),
                                    LOPBind(
                                        AOPVariable("len"),
                                        AOPBuildInCallSTRLEN(AOPVariable("str")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("str"),
                            AOPVariable("len")
                        ), listOf(
                            GeneratedMutableMap.map25850map,
                            GeneratedMutableMap.map25851map,
                            GeneratedMutableMap.map25852map,
                            GeneratedMutableMap.map25853map,
                            GeneratedMutableMap.map25854map,
                            GeneratedMutableMap.map25855map,
                            GeneratedMutableMap.map25856map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/length01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "ustr"
                            ), listOf(
                                GeneratedMutableMap.map25949map,
                                GeneratedMutableMap.map25950map,
                                GeneratedMutableMap.map25951map,
                                GeneratedMutableMap.map25952map,
                                GeneratedMutableMap.map25953map,
                                GeneratedMutableMap.map25954map,
                                GeneratedMutableMap.map25955map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "ustr"
                        ), listOf(
                            GeneratedMutableMap.map25963map,
                            GeneratedMutableMap.map25964map,
                            GeneratedMutableMap.map25965map,
                            GeneratedMutableMap.map25966map,
                            GeneratedMutableMap.map25967map,
                            GeneratedMutableMap.map25968map,
                            GeneratedMutableMap.map25969map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("ustr"),
                                        AOPBuildInCallUCASE(AOPVariable("str")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "ustr"
                        ), listOf(
                            GeneratedMutableMap.map25963map,
                            GeneratedMutableMap.map25964map,
                            GeneratedMutableMap.map25965map,
                            GeneratedMutableMap.map25966map,
                            GeneratedMutableMap.map25967map,
                            GeneratedMutableMap.map25968map,
                            GeneratedMutableMap.map25969map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("ustr")
                            ), listOf(
                                GeneratedMutableMap.map25956map,
                                GeneratedMutableMap.map25957map,
                                GeneratedMutableMap.map25958map,
                                GeneratedMutableMap.map25959map,
                                GeneratedMutableMap.map25960map,
                                GeneratedMutableMap.map25961map,
                                GeneratedMutableMap.map25962map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ), listOf(
                            GeneratedMutableMap.map25970map,
                            GeneratedMutableMap.map25971map,
                            GeneratedMutableMap.map22844map,
                            GeneratedMutableMap.map22845map,
                            GeneratedMutableMap.map22846map,
                            GeneratedMutableMap.map25972map,
                            GeneratedMutableMap.map22848map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ),
                                    LOPBind(
                                        AOPVariable("ustr"),
                                        AOPBuildInCallUCASE(AOPVariable("str")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("ustr")
                        ), listOf(
                            GeneratedMutableMap.map25970map,
                            GeneratedMutableMap.map25971map,
                            GeneratedMutableMap.map22844map,
                            GeneratedMutableMap.map22845map,
                            GeneratedMutableMap.map22846map,
                            GeneratedMutableMap.map25972map,
                            GeneratedMutableMap.map22848map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ucase01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str",
                                "lstr"
                            ), listOf(
                                GeneratedMutableMap.map26065map,
                                GeneratedMutableMap.map26066map,
                                GeneratedMutableMap.map26067map,
                                GeneratedMutableMap.map26068map,
                                GeneratedMutableMap.map26069map,
                                GeneratedMutableMap.map26070map,
                                GeneratedMutableMap.map26071map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "lstr"
                        ), listOf(
                            GeneratedMutableMap.map26077map,
                            GeneratedMutableMap.map26078map,
                            GeneratedMutableMap.map26079map,
                            GeneratedMutableMap.map26080map,
                            GeneratedMutableMap.map26081map,
                            GeneratedMutableMap.map26082map,
                            GeneratedMutableMap.map26083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("lstr"),
                                        AOPBuildInCallLCASE(AOPVariable("str")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "lstr"
                        ), listOf(
                            GeneratedMutableMap.map26077map,
                            GeneratedMutableMap.map26078map,
                            GeneratedMutableMap.map26079map,
                            GeneratedMutableMap.map26080map,
                            GeneratedMutableMap.map26081map,
                            GeneratedMutableMap.map26082map,
                            GeneratedMutableMap.map26083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str"),
                                AOPVariable("lstr")
                            ), listOf(
                                GeneratedMutableMap.map26072map,
                                GeneratedMutableMap.map26073map,
                                GeneratedMutableMap.map26074map,
                                GeneratedMutableMap.map25959map,
                                GeneratedMutableMap.map25960map,
                                GeneratedMutableMap.map26075map,
                                GeneratedMutableMap.map26076map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ), listOf(
                            GeneratedMutableMap.map22842map,
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map26084map,
                            GeneratedMutableMap.map22845map,
                            GeneratedMutableMap.map22846map,
                            GeneratedMutableMap.map22847map,
                            GeneratedMutableMap.map26085map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ),
                                    LOPBind(
                                        AOPVariable("lstr"),
                                        AOPBuildInCallLCASE(AOPVariable("str")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("lstr")
                        ), listOf(
                            GeneratedMutableMap.map22842map,
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map26084map,
                            GeneratedMutableMap.map22845map,
                            GeneratedMutableMap.map22846map,
                            GeneratedMutableMap.map22847map,
                            GeneratedMutableMap.map26085map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/lcase01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "str"
                            ), listOf(
                                GeneratedMutableMap.map22836map,
                                GeneratedMutableMap.map22840map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map,
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22836map,
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map22843map,
                                GeneratedMutableMap.map22847map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    LOPFilter(
                                        AOPBuildInCallCONTAINS(AOPVariable("str"), AOPSimpleLiteral("\"", "a")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22843map,
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/contains01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                            ), listOf(
                                GeneratedMutableMap.map26829map,
                                GeneratedMutableMap.map26836map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26839map,
                            GeneratedMutableMap.map22839map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map26839map,
                            GeneratedMutableMap.map22839map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map23320map,
                                GeneratedMutableMap.map23327map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24302map,
                            GeneratedMutableMap.map22846map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    LOPFilter(
                                        AOPBuildInCallSTRSTARTS(AOPBuildInCallSTR(AOPVariable("str")), AOPSimpleLiteral("\"", "1")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map24302map,
                            GeneratedMutableMap.map22846map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/starts01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "str"
                            ), listOf(
                                GeneratedMutableMap.map26837map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    POPFilter(
                                        dictionary,
                                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "str"
                        ), listOf(
                            GeneratedMutableMap.map22840map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("str")
                            ), listOf(
                                GeneratedMutableMap.map23328map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ),
                                    LOPFilter(
                                        AOPBuildInCallSTRENDS(AOPVariable("str"), AOPSimpleLiteral("\"", "bc")),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("str"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("str")
                        ), listOf(
                            GeneratedMutableMap.map22847map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/ends01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27370map,
                            GeneratedMutableMap.map27371map,
                            GeneratedMutableMap.map27372map,
                            GeneratedMutableMap.map27373map,
                            GeneratedMutableMap.map27374map,
                            GeneratedMutableMap.map27375map,
                            GeneratedMutableMap.map27376map,
                            GeneratedMutableMap.map27377map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27370map,
                            GeneratedMutableMap.map27371map,
                            GeneratedMutableMap.map27372map,
                            GeneratedMutableMap.map27373map,
                            GeneratedMutableMap.map27374map,
                            GeneratedMutableMap.map27375map,
                            GeneratedMutableMap.map27376map,
                            GeneratedMutableMap.map27377map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27378map,
                            GeneratedMutableMap.map27379map,
                            GeneratedMutableMap.map27380map,
                            GeneratedMutableMap.map27381map,
                            GeneratedMutableMap.map27382map,
                            GeneratedMutableMap.map27383map,
                            GeneratedMutableMap.map27384map,
                            GeneratedMutableMap.map27385map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                                                    }()
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                                                    }()
,
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27378map,
                            GeneratedMutableMap.map27379map,
                            GeneratedMutableMap.map27380map,
                            GeneratedMutableMap.map27381map,
                            GeneratedMutableMap.map27382map,
                            GeneratedMutableMap.map27383map,
                            GeneratedMutableMap.map27384map,
                            GeneratedMutableMap.map27385map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-1.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27370map,
                            GeneratedMutableMap.map27656map,
                            GeneratedMutableMap.map27372map,
                            GeneratedMutableMap.map27373map,
                            GeneratedMutableMap.map27657map,
                            GeneratedMutableMap.map27375map,
                            GeneratedMutableMap.map27376map,
                            GeneratedMutableMap.map27377map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map27370map,
                            GeneratedMutableMap.map27656map,
                            GeneratedMutableMap.map27372map,
                            GeneratedMutableMap.map27373map,
                            GeneratedMutableMap.map27657map,
                            GeneratedMutableMap.map27375map,
                            GeneratedMutableMap.map27376map,
                            GeneratedMutableMap.map27377map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27378map,
                            GeneratedMutableMap.map27658map,
                            GeneratedMutableMap.map27380map,
                            GeneratedMutableMap.map27381map,
                            GeneratedMutableMap.map27659map,
                            GeneratedMutableMap.map27383map,
                            GeneratedMutableMap.map27384map,
                            GeneratedMutableMap.map27385map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/p>"),AOPVariable("x"),graphName,false)                                                                                                                    }()
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
                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example/q>"),AOPVariable("y"),graphName,false)                                                                                                                    }()
,
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map27378map,
                            GeneratedMutableMap.map27658map,
                            GeneratedMutableMap.map27380map,
                            GeneratedMutableMap.map27381map,
                            GeneratedMutableMap.map27659map,
                            GeneratedMutableMap.map27383map,
                            GeneratedMutableMap.map27384map,
                            GeneratedMutableMap.map27385map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/plus-2.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map27730map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27732map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallMD5(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27732map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map27731map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallMD5(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27733map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map27804map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27806map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallMD5(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s4>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27806map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map27805map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27807map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallMD5(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s4>","<http://example.org/str>","\"食べ物\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s4>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27807map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/md5-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map27876map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27878map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA1(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27878map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map27877map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27879map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA1(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27879map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map27950map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27952map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA1(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map27952map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map27951map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27953map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA1(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s8>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map27953map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha1-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map28022map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28024map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA256(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28024map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map28023map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28025map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA256(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/str>","\"foo\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28025map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        POPValues(dictionary, listOf(
                                "l",
                                "hash"
                            ), listOf(
                                GeneratedMutableMap.map28094map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28096map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA256(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s8>","<http://example.org/str>","l",true,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "hash"
                        ), listOf(
                            GeneratedMutableMap.map28096map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                        LOPValues(listOf(
                                AOPVariable("l"),
                                AOPVariable("hash")
                            ), listOf(
                                GeneratedMutableMap.map28095map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28097map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("hash")
                        ),
                                    LOPBind(
                                        AOPVariable("hash"),
                                        AOPBuildInCallSHA256(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/s8>","<http://example.org/str>","\"食\""))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable.calculate("<http://example.org/s8>"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("hash")
                        ), listOf(
                            GeneratedMutableMap.map28097map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/sha256-02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28271map,
                                GeneratedMutableMap.map28272map,
                                GeneratedMutableMap.map28273map,
                                GeneratedMutableMap.map28274map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28279map,
                            GeneratedMutableMap.map28280map,
                            GeneratedMutableMap.map28281map,
                            GeneratedMutableMap.map28282map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallMINUTES(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28279map,
                            GeneratedMutableMap.map28280map,
                            GeneratedMutableMap.map28281map,
                            GeneratedMutableMap.map28282map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28275map,
                                GeneratedMutableMap.map28276map,
                                GeneratedMutableMap.map28277map,
                                GeneratedMutableMap.map28278map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28283map,
                            GeneratedMutableMap.map28284map,
                            GeneratedMutableMap.map28285map,
                            GeneratedMutableMap.map28286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallMINUTES(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28283map,
                            GeneratedMutableMap.map28284map,
                            GeneratedMutableMap.map28285map,
                            GeneratedMutableMap.map28286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/minutes-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28361map,
                                GeneratedMutableMap.map28362map,
                                GeneratedMutableMap.map28363map,
                                GeneratedMutableMap.map28364map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28369map,
                            GeneratedMutableMap.map28370map,
                            GeneratedMutableMap.map28371map,
                            GeneratedMutableMap.map28372map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallSECONDS(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28369map,
                            GeneratedMutableMap.map28370map,
                            GeneratedMutableMap.map28371map,
                            GeneratedMutableMap.map28372map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28365map,
                                GeneratedMutableMap.map28366map,
                                GeneratedMutableMap.map28367map,
                                GeneratedMutableMap.map28368map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28373map,
                            GeneratedMutableMap.map28374map,
                            GeneratedMutableMap.map28375map,
                            GeneratedMutableMap.map28376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallSECONDS(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28373map,
                            GeneratedMutableMap.map28374map,
                            GeneratedMutableMap.map28375map,
                            GeneratedMutableMap.map28376map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/seconds-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28488map,
                                GeneratedMutableMap.map28489map,
                                GeneratedMutableMap.map28490map,
                                GeneratedMutableMap.map28491map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28496map,
                            GeneratedMutableMap.map28497map,
                            GeneratedMutableMap.map28498map,
                            GeneratedMutableMap.map28499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallHOURS(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28496map,
                            GeneratedMutableMap.map28497map,
                            GeneratedMutableMap.map28498map,
                            GeneratedMutableMap.map28499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28492map,
                                GeneratedMutableMap.map28493map,
                                GeneratedMutableMap.map28494map,
                                GeneratedMutableMap.map28495map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28500map,
                            GeneratedMutableMap.map28501map,
                            GeneratedMutableMap.map28502map,
                            GeneratedMutableMap.map28503map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallHOURS(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28500map,
                            GeneratedMutableMap.map28501map,
                            GeneratedMutableMap.map28502map,
                            GeneratedMutableMap.map28503map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/hours-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28615map,
                                GeneratedMutableMap.map28616map,
                                GeneratedMutableMap.map28617map,
                                GeneratedMutableMap.map28274map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28621map,
                            GeneratedMutableMap.map28622map,
                            GeneratedMutableMap.map28623map,
                            GeneratedMutableMap.map28282map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallMONTH(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28621map,
                            GeneratedMutableMap.map28622map,
                            GeneratedMutableMap.map28623map,
                            GeneratedMutableMap.map28282map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28618map,
                                GeneratedMutableMap.map28619map,
                                GeneratedMutableMap.map28620map,
                                GeneratedMutableMap.map28278map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28624map,
                            GeneratedMutableMap.map28625map,
                            GeneratedMutableMap.map28626map,
                            GeneratedMutableMap.map28286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallMONTH(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28624map,
                            GeneratedMutableMap.map28625map,
                            GeneratedMutableMap.map28626map,
                            GeneratedMutableMap.map28286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/month-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28738map,
                                GeneratedMutableMap.map28739map,
                                GeneratedMutableMap.map28740map,
                                GeneratedMutableMap.map28741map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28746map,
                            GeneratedMutableMap.map28747map,
                            GeneratedMutableMap.map28748map,
                            GeneratedMutableMap.map28749map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallYEAR(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28746map,
                            GeneratedMutableMap.map28747map,
                            GeneratedMutableMap.map28748map,
                            GeneratedMutableMap.map28749map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28742map,
                                GeneratedMutableMap.map28743map,
                                GeneratedMutableMap.map28744map,
                                GeneratedMutableMap.map28745map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28750map,
                            GeneratedMutableMap.map28751map,
                            GeneratedMutableMap.map28752map,
                            GeneratedMutableMap.map28753map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallYEAR(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28750map,
                            GeneratedMutableMap.map28751map,
                            GeneratedMutableMap.map28752map,
                            GeneratedMutableMap.map28753map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/year-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28865map,
                                GeneratedMutableMap.map28866map,
                                GeneratedMutableMap.map28867map,
                                GeneratedMutableMap.map28491map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28871map,
                            GeneratedMutableMap.map28872map,
                            GeneratedMutableMap.map28873map,
                            GeneratedMutableMap.map28499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallDAY(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28871map,
                            GeneratedMutableMap.map28872map,
                            GeneratedMutableMap.map28873map,
                            GeneratedMutableMap.map28499map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map28868map,
                                GeneratedMutableMap.map28869map,
                                GeneratedMutableMap.map28870map,
                                GeneratedMutableMap.map28495map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28874map,
                            GeneratedMutableMap.map28875map,
                            GeneratedMutableMap.map28876map,
                            GeneratedMutableMap.map28503map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallDAY(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map28874map,
                            GeneratedMutableMap.map28875map,
                            GeneratedMutableMap.map28876map,
                            GeneratedMutableMap.map28503map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/day-01.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map28951map,
                                GeneratedMutableMap.map28952map,
                                GeneratedMutableMap.map28953map,
                                GeneratedMutableMap.map28954map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map28955map,
                            GeneratedMutableMap.map28956map,
                            GeneratedMutableMap.map28957map,
                            GeneratedMutableMap.map28958map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/functions/timezone-01.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "date",
                                "x"
                            ), listOf(
                                GeneratedMutableMap.map29070map,
                                GeneratedMutableMap.map29071map,
                                GeneratedMutableMap.map29072map,
                                GeneratedMutableMap.map28954map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29077map,
                            GeneratedMutableMap.map29078map,
                            GeneratedMutableMap.map29079map,
                            GeneratedMutableMap.map28958map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("x"),
                                        AOPBuildInCallTZ(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"s","<http://example.org/date>","date",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "x"
                        ), listOf(
                            GeneratedMutableMap.map29077map,
                            GeneratedMutableMap.map29078map,
                            GeneratedMutableMap.map29079map,
                            GeneratedMutableMap.map28958map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("date"),
                                AOPVariable("x")
                            ), listOf(
                                GeneratedMutableMap.map29073map,
                                GeneratedMutableMap.map29074map,
                                GeneratedMutableMap.map29075map,
                                GeneratedMutableMap.map29076map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map29080map,
                            GeneratedMutableMap.map29081map,
                            GeneratedMutableMap.map29082map,
                            GeneratedMutableMap.map29083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ),
                                    LOPBind(
                                        AOPVariable("x"),
                                        AOPBuildInCallTZ(AOPVariable("date")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://example.org/d1>","<http://example.org/date>","\"2010-06-21T11:28:01Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d2>","<http://example.org/date>","\"2010-12-21T15:38:02-08:00\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d3>","<http://example.org/date>","\"2008-06-20T23:59:00Z\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        graph.addData(1L,listOf("<http://example.org/d4>","<http://example.org/date>","\"2011-02-01T01:02:03\"^^<http://www.w3.org/2001/XMLSchema#dateTime>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("s"),AOPVariable.calculate("<http://example.org/date>"),AOPVariable("date"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("x")
                        ), listOf(
                            GeneratedMutableMap.map29080map,
                            GeneratedMutableMap.map29081map,
                            GeneratedMutableMap.map29082map,
                            GeneratedMutableMap.map29083map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/tz-01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                        POPValues(dictionary, listOf(
                                "a",
                                "s1",
                                "b",
                                "s2",
                                "b2",
                                "b1"
                            ), listOf(
                                GeneratedMutableMap.map31243map,
                                GeneratedMutableMap.map31244map,
                                GeneratedMutableMap.map31245map,
                                GeneratedMutableMap.map31246map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "s2",
                            "b1",
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31251map,
                            GeneratedMutableMap.map31252map,
                            GeneratedMutableMap.map31253map,
                            GeneratedMutableMap.map31254map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("b1"),
                                        AOPBuildInCallBNODE1(AOPVariable("s1")),
                                                    POPBind(
                                                        dictionary,
                                                        AOPVariable("b2"),
                                                        AOPBuildInCallBNODE1(AOPVariable("s2")),
                                                                    POPFilter(
                                                                        dictionary,
                                                                        AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                                                                    POPFilter(
                                                                                        dictionary,
                                                                                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "s1",
                            "s2",
                            "b1",
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31251map,
                            GeneratedMutableMap.map31252map,
                            GeneratedMutableMap.map31253map,
                            GeneratedMutableMap.map31254map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                        LOPValues(listOf(
                                AOPVariable("a"),
                                AOPVariable("s1"),
                                AOPVariable("b"),
                                AOPVariable("s2"),
                                AOPVariable("b2"),
                                AOPVariable("b1")
                            ), listOf(
                                GeneratedMutableMap.map31247map,
                                GeneratedMutableMap.map31248map,
                                GeneratedMutableMap.map31249map,
                                GeneratedMutableMap.map31250map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ), listOf(
                            GeneratedMutableMap.map31255map,
                            GeneratedMutableMap.map31256map,
                            GeneratedMutableMap.map31257map,
                            GeneratedMutableMap.map31258map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                                    LOPBind(
                                        AOPVariable("b1"),
                                        AOPBuildInCallBNODE1(AOPVariable("s1")),
                                                    LOPBind(
                                                        AOPVariable("b2"),
                                                        AOPBuildInCallBNODE1(AOPVariable("s2")),
                                                                    LOPFilter(
                                                                        AOPOr(AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("a"), AOPIri("http://example.org/s1"))),
                                                                                    LOPFilter(
                                                                                        AOPOr(AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s3")), AOPEQ(AOPVariable("b"), AOPIri("http://example.org/s1"))),
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
                                                                                                                        LOPTriple(AOPVariable("a"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s1"),graphName,false)                                                                                                                    }()
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
                                                                                                                        LOPTriple(AOPVariable("b"),AOPVariable.calculate("<http://example.org/str>"),AOPVariable("s2"),graphName,false)                                                                                                                    }()
,
                                                                                                        false                                                                                                    )

                                                                                    )

                                                                    )

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("s1"),
                            AOPVariable("s2"),
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ), listOf(
                            GeneratedMutableMap.map31255map,
                            GeneratedMutableMap.map31256map,
                            GeneratedMutableMap.map31257map,
                            GeneratedMutableMap.map31258map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                            ), listOf(
                                GeneratedMutableMap.map31335map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31336map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("b1"),
                            AOPVariable("b2")
                        ),
                        POPValues(dictionary, listOf(
                                "b2",
                                "b1"
                            ), listOf(
                                GeneratedMutableMap.map31338map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "b1",
                            "b2"
                        ), listOf(
                            GeneratedMutableMap.map31339map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/bnode02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("n")
                        ),
                        POPValues(dictionary, listOf(
                                "n"
                            ), listOf(
                                GeneratedMutableMap.map31611map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "n"
                        ), listOf(
                            GeneratedMutableMap.map31611map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/now01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("uri"),
                            AOPVariable("iri")
                        ),
                        POPValues(dictionary, listOf(
                                "iri",
                                "uri"
                            ), listOf(
                                GeneratedMutableMap.map31702map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "uri",
                            "iri"
                        ), listOf(
                            GeneratedMutableMap.map31703map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/iri01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o",
                                "integer"
                            ), listOf(
                                GeneratedMutableMap.map31928map,
                                GeneratedMutableMap.map31929map,
                                GeneratedMutableMap.map31930map,
                                GeneratedMutableMap.map31931map,
                                GeneratedMutableMap.map31932map,
                                GeneratedMutableMap.map31933map,
                                GeneratedMutableMap.map31934map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "integer"
                        ), listOf(
                            GeneratedMutableMap.map31942map,
                            GeneratedMutableMap.map31943map,
                            GeneratedMutableMap.map31944map,
                            GeneratedMutableMap.map31945map,
                            GeneratedMutableMap.map31946map,
                            GeneratedMutableMap.map31947map,
                            GeneratedMutableMap.map31948map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("integer"),
                                        AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
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
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "o",
                            "integer"
                        ), listOf(
                            GeneratedMutableMap.map31942map,
                            GeneratedMutableMap.map31943map,
                            GeneratedMutableMap.map31944map,
                            GeneratedMutableMap.map31945map,
                            GeneratedMutableMap.map31946map,
                            GeneratedMutableMap.map31947map,
                            GeneratedMutableMap.map31948map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o"),
                                AOPVariable("integer")
                            ), listOf(
                                GeneratedMutableMap.map31935map,
                                GeneratedMutableMap.map31936map,
                                GeneratedMutableMap.map31937map,
                                GeneratedMutableMap.map31938map,
                                GeneratedMutableMap.map31939map,
                                GeneratedMutableMap.map31940map,
                                GeneratedMutableMap.map31941map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ), listOf(
                            GeneratedMutableMap.map31949map,
                            GeneratedMutableMap.map31950map,
                            GeneratedMutableMap.map31951map,
                            GeneratedMutableMap.map31952map,
                            GeneratedMutableMap.map31953map,
                            GeneratedMutableMap.map31954map,
                            GeneratedMutableMap.map31955map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ),
                                    LOPBind(
                                        AOPVariable("integer"),
                                        AOPBuildInCallIF(AOPEQ(AOPBuildInCallLANG(AOPVariable("o")), AOPSimpleLiteral("\"", "ja")), AOPBoolean(true), AOPBoolean(false)),
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
                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("o"),
                            AOPVariable("integer")
                        ), listOf(
                            GeneratedMutableMap.map31949map,
                            GeneratedMutableMap.map31950map,
                            GeneratedMutableMap.map31951map,
                            GeneratedMutableMap.map31952map,
                            GeneratedMutableMap.map31953map,
                            GeneratedMutableMap.map31954map,
                            GeneratedMutableMap.map31955map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("error")
                        ),
                        POPValues(dictionary, listOf(
                                "error"
                            ), listOf(
                                GeneratedMutableMap.map32017map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "error"
                        ), listOf(
                            GeneratedMutableMap.map32017map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/functions/if02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s")
                        ),
                        POPValues(dictionary, listOf(
                                "s"
                            ), listOf(
                                GeneratedMutableMap.map32603map,
                                GeneratedMutableMap.map32604map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s"
                        ), listOf(
                            GeneratedMutableMap.map32603map,
                            GeneratedMutableMap.map32604map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("w"),
                            AOPVariable("S")
                        ),
                        POPValues(dictionary, listOf(
                                "w",
                                "S"
                            ), listOf(
                                GeneratedMutableMap.map32740map,
                                GeneratedMutableMap.map32741map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "w",
                            "S"
                        ), listOf(
                            GeneratedMutableMap.map32740map,
                            GeneratedMutableMap.map32741map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("w")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "w"
                            ), listOf(
                                GeneratedMutableMap.map32734map,
                                GeneratedMutableMap.map32885map,
                                GeneratedMutableMap.map32886map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "w"
                        ), listOf(
                            GeneratedMutableMap.map32734map,
                            GeneratedMutableMap.map32885map,
                            GeneratedMutableMap.map32886map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/grouping/group05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                    }()

                                                                    )

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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ),
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
                                                                                                                        true                                                                                                                    )

                                                                                                    )

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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o"),
                            AOPVariable("p2"),
                            AOPVariable("o2")
                        ),
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
                                                                                                                                        LOPTriple(AOPVariable("s"),AOPVariable("p"),AOPVariable("o"),graphName,false)                                                                                                                                    }()
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
                                                                                                                                        LOPTriple(AOPVariable("o"),AOPVariable("p2"),AOPVariable("o2"),graphName,false)                                                                                                                                    }()
,
                                                                                                                        true                                                                                                                    )

                                                                                                    )

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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                        POPValues(dictionary, listOf(
                            ), listOf(
                                GeneratedMutableMap.map5900map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                        ), listOf(
                            GeneratedMutableMap.map5900map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        DistributedTripleStore.commit(1L)
                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>",true,true,true,EIndexPattern.SPO)
                                    }()

                    ),
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
                    LOPProjection(
                        mutableListOf(
                        ),
                        LOPValues(listOf(
                            ), listOf(
                                GeneratedMutableMap.map33455map
                            )
                        )
                    ),
                    LOPValues(listOf(
                        ), listOf(
                            GeneratedMutableMap.map33455map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/json-res/jsonres03.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                        ),
                                    {
                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                        val graph=DistributedTripleStore.createGraph(graphName)
                                        graph.addData(1L,listOf("<http://example.org/s1>","<http://example.org/p1>","<http://example.org/s2>"))
                                        DistributedTripleStore.commit(1L)
                                        LOPTriple(AOPVariable.calculate("<http://example.org/s1>"),AOPVariable.calculate("<http://example.org/p1>"),AOPVariable.calculate("<http://example.org/s2>"),graphName,false)                                    }()

                    ),
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "eq"
                            ), listOf(
                                GeneratedMutableMap.map35140map,
                                GeneratedMutableMap.map35141map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "eq"
                        ), listOf(
                            GeneratedMutableMap.map35140map,
                            GeneratedMutableMap.map35141map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("eq"),
                                        AOPEQ(AOPVariable("y"), AOPVariable("z")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "eq"
                        ), listOf(
                            GeneratedMutableMap.map35140map,
                            GeneratedMutableMap.map35141map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("eq")
                            ), listOf(
                                GeneratedMutableMap.map35142map,
                                GeneratedMutableMap.map35143map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ), listOf(
                            GeneratedMutableMap.map35142map,
                            GeneratedMutableMap.map35143map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ),
                                    LOPBind(
                                        AOPVariable("eq"),
                                        AOPEQ(AOPVariable("y"), AOPVariable("z")),
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("eq")
                        ), listOf(
                            GeneratedMutableMap.map35142map,
                            GeneratedMutableMap.map35143map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp01.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map35262map,
                                GeneratedMutableMap.map35263map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35262map,
                            GeneratedMutableMap.map35263map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum"
                        ), listOf(
                            GeneratedMutableMap.map35262map,
                            GeneratedMutableMap.map35263map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("z"),
                                AOPVariable("sum")
                            ), listOf(
                                GeneratedMutableMap.map35264map,
                                GeneratedMutableMap.map35265map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35264map,
                            GeneratedMutableMap.map35265map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ),
                                    LOPBind(
                                        AOPVariable("sum"),
                                        AOPAddition(AOPVariable("z"), AOPVariable("y")),
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"foobar\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("z"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum")
                        ), listOf(
                            GeneratedMutableMap.map35264map,
                            GeneratedMutableMap.map35265map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp02.rq */ ,
            /* {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("z"),
                            AOPVariable("sum"),
                            AOPVariable("twice")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "z",
                                "twice",
                                "sum"
                            ), listOf(
                                GeneratedMutableMap.map35367map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "y",
                            "z",
                            "sum",
                            "twice"
                        ), listOf(
                            GeneratedMutableMap.map35368map
                        )
                    )
                )
            }() */ /* resources/sparql11-test-suite/project-expression/projexp03.rq */
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
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
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("y"),
                            AOPVariable("sum")
                        ),
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
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                                    }()

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
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                            ), listOf(
                                GeneratedMutableMap.map35585map,
                                GeneratedMutableMap.map35586map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35585map,
                            GeneratedMutableMap.map35586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35585map,
                            GeneratedMutableMap.map35586map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                            ), listOf(
                                GeneratedMutableMap.map35587map,
                                GeneratedMutableMap.map35588map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35587map,
                            GeneratedMutableMap.map35588map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                                    LOPBind(
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","<http://www.example.org/schema#a>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35587map,
                            GeneratedMutableMap.map35588map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp05.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "l",
                                "dt"
                            ), listOf(
                                GeneratedMutableMap.map35668map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35668map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("m")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        TripleStoreIteratorGlobal(1L,dictionary,graphName,"x","<http://www.example.org/schema#p>","l",false,true,false,EIndexPattern.SPO)
                                                    }()

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "l",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35668map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                            ), listOf(
                                GeneratedMutableMap.map35669map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35669map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ),
                                    LOPBind(
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("m")),
                                                    {
                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                        DistributedTripleStore.commit(1L)
                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("l"),graphName,false)                                                    }()

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("l"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35669map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp06.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ),
                        POPValues(dictionary, listOf(
                                "x",
                                "y",
                                "l",
                                "dt"
                            ), listOf(
                                GeneratedMutableMap.map35765map,
                                GeneratedMutableMap.map35766map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35769map,
                            GeneratedMutableMap.map35770map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ),
                                    POPBind(
                                        dictionary,
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("l")),
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
                                                        true                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "x",
                            "dt"
                        ), listOf(
                            GeneratedMutableMap.map35769map,
                            GeneratedMutableMap.map35770map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ),
                        LOPValues(listOf(
                                AOPVariable("x"),
                                AOPVariable("y"),
                                AOPVariable("l"),
                                AOPVariable("dt")
                            ), listOf(
                                GeneratedMutableMap.map35767map,
                                GeneratedMutableMap.map35768map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35771map,
                            GeneratedMutableMap.map35772map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ),
                                    LOPBind(
                                        AOPVariable("dt"),
                                        AOPBuildInCallDATATYPE(AOPVariable("l")),
                                                    LOPJoin(
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#p>","\"1\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#b>","<http://www.example.org/schema#p>","\"3\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#p>"),AOPVariable("y"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://www.example.org/instance#a>","<http://www.example.org/schema#q>","\"2\"^^<http://www.w3.org/2001/XMLSchema#integer>"))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("x"),AOPVariable.calculate("<http://www.example.org/schema#q>"),AOPVariable("l"),graphName,false)                                                                    }()
,
                                                        true                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("x"),
                            AOPVariable("dt")
                        ), listOf(
                            GeneratedMutableMap.map35771map,
                            GeneratedMutableMap.map35772map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/project-expression/projexp07.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        POPValues(dictionary, listOf(
                                "O"
                            ), listOf(
                                GeneratedMutableMap.map37191map,
                                GeneratedMutableMap.map37192map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37191map,
                            GeneratedMutableMap.map37192map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
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

                    ),
                    POPValues(dictionary, listOf(
                            "O"
                        ), listOf(
                            GeneratedMutableMap.map37191map,
                            GeneratedMutableMap.map37192map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("O")
                        ),
                        LOPValues(listOf(
                                AOPVariable("O")
                            ), listOf(
                                GeneratedMutableMap.map37195map,
                                GeneratedMutableMap.map37196map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37195map,
                            GeneratedMutableMap.map37196map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                    }()

                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("O")
                        ), listOf(
                            GeneratedMutableMap.map37195map,
                            GeneratedMutableMap.map37196map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
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
                    ),
                    POPValues(dictionary, listOf(
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37278map,
                            GeneratedMutableMap.map37279map,
                            GeneratedMutableMap.map37280map,
                            GeneratedMutableMap.map37281map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                                                        false                                                                    )
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37278map,
                            GeneratedMutableMap.map37279map,
                            GeneratedMutableMap.map37280map,
                            GeneratedMutableMap.map37281map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("L")
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37283map,
                            GeneratedMutableMap.map37284map,
                            GeneratedMutableMap.map37285map,
                            GeneratedMutableMap.map37286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                                                                        LOPTriple(AOPVariable("#_37075"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37075"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )
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
                                                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                                                    }()

                                                                                                                    )

                                                                                                    )

                                                                                    )

                                                                    )
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37283map,
                            GeneratedMutableMap.map37284map,
                            GeneratedMutableMap.map37285map,
                            GeneratedMutableMap.map37286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
                        ),
                        POPValues(dictionary, listOf(
                                "P",
                                "F",
                                "L",
                                "FullName"
                            ), listOf(
                                GeneratedMutableMap.map37493map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "FullName"
                        ), listOf(
                            GeneratedMutableMap.map37495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "P",
                            "FullName"
                        ), listOf(
                            GeneratedMutableMap.map37495map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
                        ),
                        LOPValues(listOf(
                                AOPVariable("P"),
                                AOPVariable("F"),
                                AOPVariable("L"),
                                AOPVariable("FullName")
                            ), listOf(
                                GeneratedMutableMap.map37494map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
                        ), listOf(
                            GeneratedMutableMap.map37496map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
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
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                                                                    }()
,
                                                                    {
                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                                        DistributedTripleStore.commit(1L)
                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                                                                    }()
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("P"),
                            AOPVariable("FullName")
                        ), listOf(
                            GeneratedMutableMap.map37496map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
                        POPValues(dictionary, listOf(
                                "s",
                                "p",
                                "o"
                            ), listOf(
                                GeneratedMutableMap.map37501map
                            )
                        )
                    ),
                    POPValues(dictionary, listOf(
                            "s",
                            "p",
                            "o"
                        ), listOf(
                            GeneratedMutableMap.map37501map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                                                        false                                                                                                                    )

                                                                                                    )

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
                            GeneratedMutableMap.map37501map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
                        LOPValues(listOf(
                                AOPVariable("s"),
                                AOPVariable("p"),
                                AOPVariable("o")
                            ), listOf(
                                GeneratedMutableMap.map37502map
                            )
                        )
                    ),
                    LOPValues(listOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ), listOf(
                            GeneratedMutableMap.map37502map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("s"),
                            AOPVariable("p"),
                            AOPVariable("o")
                        ),
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
                                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/firstName>","\"John\""))
                                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/firstName>"),AOPVariable("F"),graphName,false)                                                                                                                                    }()
,
                                                                                                                                    {
                                                                                                                                        val graphName = "graph" + DistributedTripleStore.getGraphNames().size
                                                                                                                                        val graph=DistributedTripleStore.createGraph(graphName)
                                                                                                                                        graph.addData(1L,listOf("<http://p1>","<http://xmlns.com/foaf/0.1/lastName>","\"Doe\""))
                                                                                                                                        DistributedTripleStore.commit(1L)
                                                                                                                                        LOPTriple(AOPVariable("P"),AOPVariable.calculate("<http://xmlns.com/foaf/0.1/lastName>"),AOPVariable("L"),graphName,false)                                                                                                                                    }()
,
                                                                                                                        false                                                                                                                    )

                                                                                                    )

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
                            GeneratedMutableMap.map37502map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq12.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
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
                    ),
                    POPValues(dictionary, listOf(
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37278map,
                            GeneratedMutableMap.map37279map,
                            GeneratedMutableMap.map37280map,
                            GeneratedMutableMap.map37281map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary = ResultSetDictionary()
                MicroTestPN(
                    dictionary,
                    POPProjection(
                        dictionary,
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                                                        false                                                                    )
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
                                                        false                                                    )

                                    )

                    ),
                    POPValues(dictionary, listOf(
                            "L"
                        ), listOf(
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37277map,
                            GeneratedMutableMap.map37278map,
                            GeneratedMutableMap.map37279map,
                            GeneratedMutableMap.map37280map,
                            GeneratedMutableMap.map37281map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("L")
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
                    ),
                    LOPValues(listOf(
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37283map,
                            GeneratedMutableMap.map37284map,
                            GeneratedMutableMap.map37285map,
                            GeneratedMutableMap.map37286map
                        )
                    )
                )
            }() /* resources/sparql11-test-suite/subquery/sq11.rq */ ,
            {
                val dictionary=ResultSetDictionary()
                MicroTestLN(
                    dictionary,
                    LOPProjection(
                        mutableListOf(
                            AOPVariable("L")
                        ),
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
                                                                                        LOPTriple(AOPVariable("#_37526"),AOPVariable.calculate("<http://www.w3.org/2000/01/rdf-schema#label>"),AOPVariable("L"),graphName,false)                                                                                    }()
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
                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.example.orghasItem>"),AOPVariable("#_37526"),graphName,false)                                                                                    }()
,
                                                                        false                                                                    )
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
                                                                                                                                        LOPTriple(AOPVariable("O"),AOPVariable.calculate("<http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"),AOPVariable.calculate("<http://www.example.orgOrder>"),graphName,false)                                                                                                                                    }()

                                                                                                                    )

                                                                                                    )

                                                                                    )

                                                                    )
,
                                                        false                                                    )

                                    )

                    ),
                    LOPValues(listOf(
                            AOPVariable("L")
                        ), listOf(
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37282map,
                            GeneratedMutableMap.map37283map,
                            GeneratedMutableMap.map37284map,
                            GeneratedMutableMap.map37285map,
                            GeneratedMutableMap.map37286map
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
